package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.ContactType;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    SqlHelper sqlHelper;

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
            try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE contacts " +
                    "SET type = ?, value = ? " +
                    "WHERE resume_uuid = ?")) {
                for (Map.Entry<ContactType, String> contact : r.getContacts().entrySet()) {
                    preparedStatement.setString(1, contact.getKey().name());
                    preparedStatement.setString(2, contact.getValue());
                    preparedStatement.setString(3, r.getUuid());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "SELECT * FROM resumes " +
                        "LEFT JOIN contacts " +
                        "ON resumes.uuid = contacts.resume_uuid " +
                        "WHERE uuid = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) {
                        throw new NotExistStorageException("Not exist " + uuid);
                    }
                    Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    String contact = resultSet.getString("value");
                    if (contact != null) {
                        do {
                            resume.addContact(ContactType.valueOf(resultSet.getString("type")), contact);
                        } while (resultSet.next());
                    }
                    return resume;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                        "SELECT * FROM resumes " +
                        "LEFT JOIN contacts " +
                        "ON resumes.uuid = contacts.resume_uuid " +
                        "ORDER BY full_name, uuid",
                preparedStatement -> {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    Map<String, Resume> resumes = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        String uuid = resultSet.getString("uuid");
                        Resume resume = resumes.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, resultSet.getString("full_name"));
                            resumes.put(uuid, resume);
                        }
                        resume.addContact(ContactType.valueOf(resultSet.getString("type")), resultSet.getString("value"));
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
}
