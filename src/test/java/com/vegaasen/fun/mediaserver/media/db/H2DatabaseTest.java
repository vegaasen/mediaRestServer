package com.vegaasen.fun.mediaserver.media.db;

import com.vegaasen.fun.mediaserver.media.abs.AbstractDatabaseTest;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.*;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class H2DatabaseTest extends AbstractDatabaseTest {

    String sql;

    @Test
    public void shouldCreateAndThenDeleteTables() throws SQLException {
        sql = String.format("INSERT INTO %s VALUES (1, 'Vegard Aasen', 'Some kind of description for Vegard Aasen')", THE_BOGUS_TABLE_ID);
        PreparedStatement statement = connection.prepareStatement(sql);
        assertNotNull(statement);
        statement.executeUpdate();
        statement = getConnection().prepareStatement(SELECT_ALL_FROM_TABLE_BOGUS);
        ResultSet resultSet = statement.executeQuery();
        assertNotNull(resultSet);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            assertNotNull(name);
            String description = resultSet.getString("description");
            assertNotNull(description);
        }
        resultSet.close();
        assertTrue(resultSet.isClosed());
    }

}
