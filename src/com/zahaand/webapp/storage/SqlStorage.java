package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.AbstractSection;
import com.zahaand.webapp.model.ContactType;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.model.SectionType;
import com.zahaand.webapp.util.JsonParser;
import com.zahaand.webapp.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume r) {
        sqlHelper.executeTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO resumes (uuid, full_name) " +
                    "VALUES (?, ?)")) {
                preparedStatement.setString(1, r.getUuid());
                preparedStatement.setString(2, r.getFullName());
                preparedStatement.execute();
            }
            addContacts(r, connection);
            addSections(r, connection);
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.executeTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE resumes " +
                    "SET full_name = ? " +
                    "WHERE uuid = ?")) {
                preparedStatement.setString(1, r.getFullName());
                preparedStatement.setString(2, r.getUuid());
                if (preparedStatement.executeUpdate() == 0) {
                    throw new NotExistStorageException("Not exist " + r.getUuid());
                }
            }
            deleteContacts(r, connection);
            deleteSections(r, connection);
            addContacts(r, connection);
            addSections(r, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executeTransaction(connection -> {
            Resume resume;
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM resumes " +
                    "WHERE uuid = ?")) {
                preparedStatement.setString(1, uuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, resultSet.getString("full_name"));
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM contacts " +
                    "WHERE resume_uuid = ?")) {
                preparedStatement.setString(1, uuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addContact(resultSet, resume);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM sections " +
                    "WHERE resume_uuid = ?")) {
                preparedStatement.setString(1, uuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addSection(resultSet, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeTransaction(connection -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM resumes " +
                    "ORDER BY full_name, uuid")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, resultSet.getString("full_name")));
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM contacts")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Resume resume = resumes.get(resultSet.getString("resume_uuid"));
                    addContact(resultSet, resume);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * FROM sections")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Resume resume = resumes.get(resultSet.getString("resume_uuid"));
                    addSection(resultSet, resume);
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("" +
                        "SELECT count(*) " +
                        "FROM resumes",
                preparedStatement -> {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    return resultSet.next() ? resultSet.getInt(1) : 0;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("" +
                        "DELETE FROM resumes " +
                        "WHERE uuid = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    if (preparedStatement.executeUpdate() == 0) {
                        throw new NotExistStorageException("Not exist " + uuid);
                    }
                    return null;
                });
    }

    @Override
    public void clear() {
        sqlHelper.execute("" +
                        "DELETE FROM resumes",
                PreparedStatement::executeUpdate);
    }

    private static void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) {
            resume.setContact(ContactType.valueOf(resultSet.getString("type")), value);
        }
    }

    private static void addContacts(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "INSERT INTO contacts (resume_uuid, type, value) " +
                "VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> contact : r.getContacts().entrySet()) {
                preparedStatement.setString(1, r.getUuid());
                preparedStatement.setString(2, contact.getKey().name());
                preparedStatement.setString(3, contact.getValue());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private static void deleteContacts(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "DELETE FROM contacts " +
                "WHERE resume_uuid = ?")) {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.execute();
        }
    }

    private void addSection(ResultSet resultSet, Resume resume) throws SQLException {
        String content = resultSet.getString("content");
        if (content != null) {
            resume.setSection(SectionType.valueOf(resultSet.getString("type")), JsonParser.read(content, AbstractSection.class));
        }
    }

    private void addSections(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "INSERT INTO sections (resume_uuid, type, content) " +
                "VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> section : r.getSections().entrySet()) {
                preparedStatement.setString(1, r.getUuid());
                preparedStatement.setString(2, section.getKey().name());
                preparedStatement.setString(3, JsonParser.write(section.getValue(), AbstractSection.class));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void deleteSections(Resume r, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "DELETE FROM sections " +
                "WHERE resume_uuid = ?")) {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.execute();
        }
    }
}
