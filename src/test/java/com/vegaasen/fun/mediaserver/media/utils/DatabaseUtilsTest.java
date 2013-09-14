package com.vegaasen.fun.mediaserver.media.utils;

import com.vegaasen.fun.mediaserver.media.abs.AbstractDatabaseTest;
import com.vegaasen.fun.mediaserver.media.model.BogusElement;
import org.junit.Test;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class DatabaseUtilsTest extends AbstractDatabaseTest {

    @Test
    public void testExecuteQuery() throws Exception {
        final String query = String.format("SELECT * FROM %s WHERE tmpId = %s", THE_BOGUS_TABLE_ID, BOGUS_ROW_ID);
        ResultSet set = DatabaseUtils.executeQuery(getConnection(), query);
        assertNotNull(set);
        Set<BogusElement> elementSet = getAllElementsFromResultSet(set);
        assertFalse(elementSet.isEmpty());
        assertTrue(elementSet.size() == 1);
        for (BogusElement ele : elementSet) {
            assertTrue(ele.getTmpId() == Integer.parseInt(BOGUS_ROW_ID));
        }
    }

    @Test
    public void testExecuteUpdate() throws Exception {
        final String query = String.format("UPDATE %s SET name='veggis' WHERE tmpId = %s", THE_BOGUS_TABLE_ID, BOGUS_ROW_ID);
        final int result = DatabaseUtils.executeUpdate(getConnection(), query);
        assertNotNull(result);
        assertFalse(result == 0);
        assertTrue(result == 1);
    }

    @Test(expected = SQLException.class)
    public void shouldNotExecuteUpdateOnTable() throws SQLException, ConnectException {
        final String query = "UPDATE tt SET nop='nop'";
        DatabaseUtils.executeUpdate(getConnection(), query);
    }

    @Test
    public void testExecute() throws Exception {
        final String query = String.format("SELECT * FROM %s WHERE tmpId = %s", THE_BOGUS_TABLE_ID, BOGUS_ROW_ID);
        final boolean result = DatabaseUtils.execute(getConnection(), query);
        assertNotNull(result);
        assertTrue(result);
    }

    @Test(expected = SQLException.class)
    public void shouldNotFindAnyResultsThrowsException() throws SQLException, ConnectException {
        final String query = "SELECT * FROM nothing";
        DatabaseUtils.execute(getConnection(), query);
    }

    @Test
    public void shouldNotFindAnyResultsNothingFound() throws SQLException, ConnectException {
        final String query = String.format("SELECT * FROM %s WHERE tmpId = %s", THE_BOGUS_TABLE_ID, 1290910);
        final boolean result = DatabaseUtils.execute(getConnection(), query);
        assertNotNull(result);
    }

    @Test
    public void testExtractPreparedStatement() throws Exception {

    }

}
