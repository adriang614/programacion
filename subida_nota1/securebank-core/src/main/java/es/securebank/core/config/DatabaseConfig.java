package es.securebank.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DatabaseConfig {
    private static HikariDataSource dataSource;

    private DatabaseConfig() {}

    public static DataSource getDataSource() {
        if (dataSource == null) {
            AppConfig config = AppConfig.getInstance();
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(config.get("db.url"));
            hikariConfig.setUsername(config.get("db.user"));
            hikariConfig.setPassword(config.get("db.password"));
            hikariConfig.setDriverClassName(config.get("db.driver"));
            hikariConfig.setMinimumIdle(config.getInt("db.pool.min", 2));
            hikariConfig.setMaximumPoolSize(config.getInt("db.pool.max", 10));
            hikariConfig.setConnectionTimeout(config.getInt("db.pool.timeout.ms", 5000));
            hikariConfig.setPoolName("SecureBankPool");
            dataSource = new HikariDataSource(hikariConfig);
            System.out.println("[DB] Pool de conexiones iniciado correctamente.");
        }
        return dataSource;
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("[DB] Pool de conexiones cerrado.");
        }
    }
}
