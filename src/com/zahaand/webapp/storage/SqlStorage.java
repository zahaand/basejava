package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume r) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resumes (uuid, full_name) VALUES (?, ?)")) {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public void update(Resume r) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resumes SET uuid = ?, full_name = ? WHERE uuid = ?")) {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            preparedStatement.setString(3, r.getUuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resumes WHERE uuid = ?")) {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resumes ORDER BY ?")) {
            preparedStatement.setString(1, "full_name");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new StorageException("Storage is empty");
            }
            List<Resume> resumes = new ArrayList<>();
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
                resumes.add(resume);
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public int size() {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM resumes");
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resumes WHERE uuid = ?")) {
            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @Override
    public void clear() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resumes")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new StorageException("Connection error ", e);
        }
    }
}
