package com.framework.base;
import java.io.FileInputStream;
import java.util.Properties;
public class ConfigManager {
    private static Properties props = new Properties();
    public static void load(String env) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config/config-" + env + ".properties")) {
            props.load(fis);
        } catch (Exception e) {
            System.err.println("Could not load config for env: " + env + " - " + e.getMessage());
        }
    }
    public static String get(String key) { return props.getProperty(key); }
}
