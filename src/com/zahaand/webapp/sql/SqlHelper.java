package com.zahaand.webapp.sql;

import com.zahaand.webapp.storage.SqlStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public PreparedStatement doConnection(String sqlCommand) {
        try (Connection connection = (Connection) SqlStorage.connectionFactory;
             PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sqlCommand))) {
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
