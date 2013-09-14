package com.vegaasen.fun.mediaserver.media.utils;

import com.google.common.base.Strings;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class DatabaseUtils {

    private static final String ERROR_PREPSTMT_NULL = "Unable to complete request. PreparedStatement seems to be null.";

    private DatabaseUtils() {
    }

    public static ResultSet executeQuery(final Connection connection, final String query)
            throws SQLException, ConnectException {
        PreparedStatement statement = getPreparedStatement(connection, query);
        if (statement != null) {
            return statement.executeQuery();
        }
        throw new IllegalArgumentException(ERROR_PREPSTMT_NULL);
    }

    public static int executeUpdate(final Connection connection, final String query) throws SQLException, ConnectException {
        PreparedStatement statement = getPreparedStatement(connection, query);
        if (statement != null) {
            return statement.executeUpdate();
        }
        throw new IllegalArgumentException(ERROR_PREPSTMT_NULL);
    }

    public static boolean execute(final Connection connection, final String query) throws SQLException, ConnectException {
        PreparedStatement statement = getPreparedStatement(connection, query);
        if (statement != null) {
            boolean result = statement.execute();
            return (result || statement.getUpdateCount()>0);
        }
        throw new IllegalArgumentException(ERROR_PREPSTMT_NULL);
    }

    public static PreparedStatement extractPreparedStatement(final Connection connection, final String query)
            throws SQLException, ConnectException {
        return getPreparedStatement(connection, query);
    }

    public static boolean isConnectionClosed(Connection connection) throws SQLException {
        return connection.isClosed();
    }

    private static PreparedStatement getPreparedStatement(Connection connection, String query)
            throws SQLException, ConnectException {
        if (Strings.isNullOrEmpty(query) || connection == null) {
            if (isConnectionClosed(connection)) {
                throw new ConnectException("Connection has been closed.");
            }
            throw new IllegalArgumentException("Query cannot be empty or null.");
        }
        return connection.prepareStatement(query);
    }

    private static void closePreparedStatement(final PreparedStatement statement) throws SQLException {
        if (statement != null) {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
    }

}
