package com.vegaasen.fun.mediaserver.media.db;

import com.vegaasen.fun.mediaserver.media.db.abs.AbstractDatabase;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class H2Database extends AbstractDatabase {

    private static final Logger LOG = Logger.getLogger(H2Database.class.getName());
    private static final String H2_CLASS = "org.h2.Driver";

    private static H2Database h2Database;
    private static JdbcDataSource dataSource = new JdbcDataSource();

    static {
        initialize();
    }

    private H2Database() {
    }

    public static H2Database getInstance() {
        if (h2Database == null) {
            h2Database = new H2Database();
        }
        return h2Database;
    }

    @Override
    public Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            if (!connection.isClosed()) {
                return connection;
            }
        } catch (SQLException e) {
            LOG.severe(String.format("Unable to get connection with the database. Error was: %s", e.getMessage()));
        }
        return null;
    }

    @Override
    public String getDatabaseClassName() {
        return H2_CLASS;
    }

    private static void initialize() {
        LOG.info("Initializing database and its connection..");
        dataSource.setURL("jdbc:h2:˜/test");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        LOG.info("Database configured. Verifying connection..");
    }

}
