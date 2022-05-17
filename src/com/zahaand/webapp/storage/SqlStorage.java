package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.sql.SqlHelper;
import org.postgresql.util.PSQLException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        sqlHelper.execute("INSERT INTO resumes (uuid, full_name) VALUES (?, ?)", preparedStatement -> {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            try {
                preparedStatement.execute();
            } catch (PSQLException e) {
                if (e.getErrorCode() == 23505) {
                    throw new ExistStorageException("Already exist " + r.getUuid());
                }
            }
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resumes SET full_name = ? WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, r.getFullName());
            preparedStatement.setString(2, r.getUuid());
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException("Not exist " + r.getUuid());
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resumes WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException("Not exist " + uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resumes ORDER BY full_name, uuid", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
                resumes.add(resume);
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resumes", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1);
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resumes WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException("Not exist " + uuid);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resumes", preparedStatement -> null);
    }
}
