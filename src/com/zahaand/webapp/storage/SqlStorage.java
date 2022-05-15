package com.zahaand.webapp.storage;

import com.zahaand.webapp.exception.NotExistStorageException;
import com.zahaand.webapp.exception.StorageException;
import com.zahaand.webapp.model.Resume;
import com.zahaand.webapp.sql.ConnectionFactory;
import com.zahaand.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public static ConnectionFactory connectionFactory = null;
    SqlHelper sqlHelper = new SqlHelper();

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume r) {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resumes (uuid, full_name) VALUES (?, ?)")) {
//            preparedStatement.setString(1, r.getUuid());
//            preparedStatement.setString(2, r.getFullName());
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        PreparedStatement preparedStatement = sqlHelper.doConnection("UPDATE resumes SET uuid = ?, full_name = ? WHERE uuid = ?");
        preparedStatement.setString(1, r.getUuid());
        preparedStatement.setString(2, r.getFullName());
        preparedStatement.execute();
    }

    @Override
    public void update(Resume r) {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resumes SET uuid = ?, full_name = ? WHERE uuid = ?")) {
//            preparedStatement.setString(1, r.getUuid());
//            preparedStatement.setString(2, r.getFullName());
//            preparedStatement.setString(3, r.getUuid());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        PreparedStatement preparedStatement = sqlHelper.doConnection("UPDATE resumes SET uuid = ?, full_name = ? WHERE uuid = ?");
        preparedStatement.setString(1, r.getUuid());
        preparedStatement.setString(2, r.getFullName());
        preparedStatement.setString(3, r.getUuid());
        preparedStatement.executeUpdate();
    }

    @Override
    public Resume get(String uuid) {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resumes WHERE uuid = ?")) {
//            preparedStatement.setString(1, uuid);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (!resultSet.next()) {
//                throw new NotExistStorageException(uuid);
//            }
//            return new Resume(uuid, resultSet.getString("full_name"));
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        ResultSet resultSet = sqlHelper.doConnection("SELECT * FROM resumes WHERE uuid = ?").executeQuery();
        if (!resultSet.next()) {
            throw new NotExistStorageException(uuid);
        }
        return new Resume(uuid, resultSet.getString("full_name"));
    }

    @Override
    public List<Resume> getAllSorted() {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resumes ORDER BY ?")) {
//            preparedStatement.setString(1, "full_name");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (!resultSet.next()) {
//                throw new StorageException("Storage is empty");
//            }
//            List<Resume> resumes = new ArrayList<>();
//            while (resultSet.next()) {
//                Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
//                resumes.add(resume);
//            }
//            return resumes;
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        ResultSet resultSet = sqlHelper.doConnection("SELECT * FROM resumes ORDER BY ?").executeQuery();
        if (!resultSet.next()) {
            throw new StorageException("Storage is empty");
        }
        List<Resume> resumes = new ArrayList<>();
        while (resultSet.next()) {
            Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
            resumes.add(resume);
        }
        return resumes;
    }

    @Override
    public int size() {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) FROM resumes")) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return resultSet.getInt(1);
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        return sqlHelper.doConnection("SELECT count(*) FROM resumes").executeQuery().getInt(1);
    }

    @Override
    public void delete(String uuid) {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resumes WHERE uuid = ?")) {
//            preparedStatement.setString(1, uuid);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new StorageException("Connection error", e);
//        }
        sqlHelper.doConnection("DELETE FROM resumes WHERE uuid = ?").executeUpdate();
    }

    @Override
    public void clear() {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resumes")) {
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new StorageException("Connection error ", e);
//        }
        sqlHelper.doConnection("DELETE FROM resumes").execute();
    }
}
