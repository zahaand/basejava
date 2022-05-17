package com.zahaand.webapp.sql;

import com.zahaand.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sqlCommand, PreparedStatementExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            return executor.execute(preparedStatement);
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
    }

    @FunctionalInterface
    public interface ConnectionFactory {
        Connection getConnection() throws SQLException;
    }

    @FunctionalInterface
    public interface PreparedStatementExecutor<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }
}
