package com.zahaand.webapp.util;

import com.zahaand.webapp.exception.ExistStorageException;
import com.zahaand.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

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

    public <T> T executeTransaction(TransactionExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                T result = executor.execute(connection);
                connection.commit();
                return result;
            } catch (PSQLException e) {
                if (e.getErrorCode() == 23505) {
                    throw new ExistStorageException("Already exist ", e);
                }
            }
        } catch (SQLException e) {
            throw new StorageException("Connection error", e);
        }
        return null;
    }

    @FunctionalInterface
    public interface ConnectionFactory {
        Connection getConnection() throws SQLException;
    }

    @FunctionalInterface
    public interface PreparedStatementExecutor<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }

    @FunctionalInterface
    public interface TransactionExecutor<T> {
        T execute(Connection connection) throws SQLException;
    }
}

