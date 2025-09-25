package com.reddit.automation.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Reader utility class for managing test configuration
 */
public class ConfigReader {
    
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Load properties from configuration file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            logger.info("Configuration loaded successfully from: {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration file: {}", CONFIG_FILE_PATH, e);
            // Set default values
            setDefaultProperties();
        }
    }
    
    /**
     * Set default properties if config file is not found
     */
    private static void setDefaultProperties() {
        logger.info("Setting default configuration properties");
        properties.setProperty("browser", "chrome");
        properties.setProperty("headless", "false");
        properties.setProperty("base.url", "https://www.reddit.com");
        properties.setProperty("timeout", "10");
        properties.setProperty("page.load.timeout", "30");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("username", "testuser");
        properties.setProperty("password", "testpass");
        properties.setProperty("email", "test@example.com");
    }
    
    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: {}", key);
            return "";
        }
        return value;
    }
    
    /**
     * Get property value with default
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? value : defaultValue;
    }
    
    /**
     * Get browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadless() {
        return "true".equalsIgnoreCase(getProperty("headless", "false"));
    }
    
    /**
     * Get base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.reddit.com");
    }
    
    /**
     * Get timeout value
     */
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout", "10"));
    }
    
    /**
     * Get page load timeout
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }
    
    /**
     * Get implicit wait timeout
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }
    
    /**
     * Get test username
     */
    public static String getUsername() {
        return getProperty("username", "testuser");
    }
    
    /**
     * Get test password
     */
    public static String getPassword() {
        return getProperty("password", "testpass");
    }
    
    /**
     * Get test email
     */
    public static String getEmail() {
        return getProperty("email", "test@example.com");
    }
    
    /**
     * Get all properties
     */
    public static Properties getAllProperties() {
        return new Properties(properties);
    }
    
    /**
     * Set property value
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        logger.info("Property set: {} = {}", key, value);
    }
    
    /**
     * Reload properties from file
     */
    public static void reloadProperties() {
        loadProperties();
        logger.info("Properties reloaded");
    }
}
