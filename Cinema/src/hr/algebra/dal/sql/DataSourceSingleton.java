package hr.algebra.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Leo
 */
public final class DataSourceSingleton {

    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME = "Cinema";
    private static final String USER = "sa";
    private static final String PASSWORD = "SQL";

    public DataSourceSingleton() {
    }

    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        SQLServerDataSource sqlServerDataSource = new SQLServerDataSource();
        sqlServerDataSource.setServerName(SERVER_NAME);
        sqlServerDataSource.setDatabaseName(DATABASE_NAME);
        sqlServerDataSource.setUser(USER);
        sqlServerDataSource.setPassword(PASSWORD);
        return sqlServerDataSource;
    }
}
