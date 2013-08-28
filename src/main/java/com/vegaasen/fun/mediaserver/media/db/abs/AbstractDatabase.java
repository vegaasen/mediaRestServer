package com.vegaasen.fun.mediaserver.media.db.abs;

import java.sql.Connection;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractDatabase {

    public abstract Connection getConnection();

    public abstract String getDatabaseClassName();

}
