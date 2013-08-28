package com.vegaasen.fun.mediaserver.media.db;

import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class H2DatabaseTest {

    Connection connection;

    @Test
    public void testGetConnection() throws Exception {
        H2Database h2db = H2Database.getInstance();
        assertNotNull(h2db);
        connection = H2Database.getInstance().getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
