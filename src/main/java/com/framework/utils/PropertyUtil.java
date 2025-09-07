package com.framework.utils;
import java.io.FileInputStream; import java.util.Properties;
public class PropertyUtil {
    private static Properties props = new Properties();
    static { try (FileInputStream fis = new FileInputStream("src/main/resources/config/config-SIT.properties")){ props.load(fis);} catch(Exception e){} }
    public static String get(String key){ return props.getProperty(key); }
}
