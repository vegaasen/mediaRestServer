package com.vegaasen.fun.mediaserver.media.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class contains all elements which must be initialized on startup, before the container is executed/started.
 *
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public enum Initializer {

    INSTANCE;

    Connection connection = H2Database.getInstance().getConnection();

    private Initializer() {
        createTables();
    }

    private void createTables() {
        if (verifyConnection()) {

        }
    }

    private boolean verifyConnection() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect the the supplied connection, as it appears to be closed.");
        }
    }

}
