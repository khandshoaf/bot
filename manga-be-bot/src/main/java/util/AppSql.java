package util;

import org.jsoup.internal.StringUtil;
import storage.AppStorage;
import storage.model.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppSql {
    private Connection connection;
    private AppLogger appLogger;
    private AppStorage appStorage;

    public AppSql() {
        appLogger = AppLogger.getInstance();
        appStorage = AppStorage.getInstance();
    }

    public void connect(Callback callback) {
        try {
            Class.forName(AppData.Database.MySQL.driver);
            Database database = appStorage.config.database;
            if (database == null) return;
            String connectString = String.format(
                    AppData.Database.MySQL.url,
                    database.domain + ":" + database.port,
                    database.schema,
                    database.user,
                    database.pass
            );
            connection = DriverManager.getConnection(connectString);
            callback.execute(connection);
        } catch (Exception e) {
            appLogger.warning(String.format("database execute fail : %s", e.getMessage()));
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                appLogger.warning(String.format("database close connection fail : %s", e.getMessage()));
            }
        }
    }

    void clear(String table) {
        if (StringUtil.isBlank(table)) {
            appLogger.info("null table name");
        }
        appLogger.info(String.format("clear all data table[ %s ]", table));
        connect(connection -> {
            Statement statement = null;
            try {
                String sql = String.format("DELETE FROM %s", table);
                statement = connection.createStatement();
                statement.execute(sql);
            } catch (Exception e) {
                appLogger.warning(String.format("clear all data table[ %s ] has error[ %s ]", table, e.getMessage()));
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException se) {
                    appLogger.warning(String.format("clear all data table[ %s ] has error[ %s ]", table, se.getMessage()));
                }
            }
        });
    }

    public interface Callback {
        void execute(Connection connection);
    }
}