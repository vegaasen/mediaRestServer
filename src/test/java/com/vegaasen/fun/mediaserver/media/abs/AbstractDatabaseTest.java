package com.vegaasen.fun.mediaserver.media.abs;

import com.vegaasen.fun.mediaserver.media.db.H2Database;
import com.vegaasen.fun.mediaserver.media.model.BogusElement;
import org.h2.jdbc.JdbcSQLException;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractDatabaseTest extends AbstractTest {

    protected static final String THE_BOGUS_TABLE_ID = "bogus";
    protected static final String BOGUS_ROW_ID = "9999";
    protected static final String SELECT_ALL_FROM_TABLE_BOGUS = String.format("SELECT * FROM %s;", THE_BOGUS_TABLE_ID);
    protected static final String
            CREATE_TABLE_BOGUS = String.format("CREATE TABLE %s (tmpId INT PRIMARY KEY, name NVARCHAR(35), description NVARCHAR(255));", THE_BOGUS_TABLE_ID),
            DELETE_TABLE_BOGUS = String.format("DROP TABLE IF EXISTS %s CASCADE;", THE_BOGUS_TABLE_ID),
            INSERT_INTO_BOGUS = String.format("INSERT INTO %s VALUES (%s, 'Vegard Aasen', 'HoiHoi Description');", THE_BOGUS_TABLE_ID, BOGUS_ROW_ID);

    protected Connection connection;

    @Before
    public void setUp() throws Exception {
        shouldConnectToH2Database();
        reinitializeBogusTable();
    }

    protected void shouldConnectToH2Database() throws Exception {
        H2Database h2db = getH2Database();
        assertNotNull(h2db);
        connection = H2Database.getInstance().getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }

    protected void shouldCloseDatabaseConnection() throws SQLException {
        if (getConnection() != null && !getConnection().isClosed()) {
            getConnection().close();
        }
        System.out.print("Stopped..");
    }

    protected H2Database getH2Database() {
        H2Database h2Database = H2Database.getInstance();
        assertNotNull(h2Database);
        return h2Database;
    }


    protected void reinitializeBogusTable() throws SQLException {
        try {
            executePreparedStatement(CREATE_TABLE_BOGUS);
        } catch (JdbcSQLException e) {
            executePreparedStatement(DELETE_TABLE_BOGUS);
            executePreparedStatement(CREATE_TABLE_BOGUS);
        }
        executePreparedStatement(INSERT_INTO_BOGUS);
    }

    protected void executePreparedStatement(final String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        assertNotNull(statement);
        statement.execute();
        statement.close();
        assertTrue(statement.isClosed());
    }

    protected Connection getConnection() {
        return connection;
    }

    protected Set<BogusElement> getAllElementsFromResultSet(ResultSet set) throws SQLException {
        if(set!=null) {
            Set<BogusElement> elements = new HashSet<BogusElement>();
            while (set.next()) {
                BogusElement element = new BogusElement();
                element.setTmpId(set.getInt("tmpId"));
                element.setName(set.getString("name"));
                element.setDescription(set.getString("description"));
                elements.add(element);
            }
            return elements;
        }
        return Collections.emptySet();
    }

    @After
    public void tearDown() throws SQLException {
        shouldCloseDatabaseConnection();
    }

}
