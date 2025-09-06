package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils() {
        // Private constructor to prevent instantiation
    }

    private static final String PROPERTIES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static Properties properties;

    // Unified method: load properties if not loaded, then get the value
    public static String getPropertyValue(String key) {
        try {
            if (properties == null) {
                properties = new Properties();
                Collection<File> propertiesFilesList =
                        FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true);

                for (File propertyFile : propertiesFilesList) {
                    try (FileInputStream fis = new FileInputStream(propertyFile)) {
                        properties.load(fis);
                    } catch (IOException ioe) {
                        LogsUtil.error("Error loading file " + propertyFile.getName() + ": " + ioe.getMessage());
                    }
                }

                // Merge with system properties
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
                LogsUtil.info("Properties loaded successfully from " + PROPERTIES_PATH);
            }

            return properties.getProperty(key, ""); // return empty if key not found
        } catch (Exception e) {
            LogsUtil.error("Failed to get property value: " + e.getMessage());
            return "";
        }
    }
}
