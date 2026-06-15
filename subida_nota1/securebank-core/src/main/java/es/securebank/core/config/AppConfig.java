package es.securebank.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties props = new Properties();
    private static AppConfig instance;

    private AppConfig() {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (is != null) props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar application.properties", e);
        }
    }

    public static AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public String get(String key) { return props.getProperty(key); }
    public String get(String key, String defaultValue) { return props.getProperty(key, defaultValue); }
    public int getInt(String key, int defaultValue) {
        String val = props.getProperty(key);
        return val != null ? Integer.parseInt(val) : defaultValue;
    }
    public boolean getBoolean(String key, boolean defaultValue) {
        String val = props.getProperty(key);
        return val != null ? Boolean.parseBoolean(val) : defaultValue;
    }
    public double getDouble(String key, double defaultValue) {
        String val = props.getProperty(key);
        return val != null ? Double.parseDouble(val) : defaultValue;
    }
}
