package com.reddit.automation.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * WebDriver Manager class for handling browser initialization and configuration
 */
public class DriverManager {
    
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver initializeDriver(String browserType) {
        WebDriver driver = null;
        
        try {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    driver = initializeChromeDriver();
                    break;
                case "firefox":
                    driver = initializeFirefoxDriver();
                    break;
                case "edge":
                    driver = initializeEdgeDriver();
                    break;
                case "safari":
                    driver = initializeSafariDriver();
                    break;
                default:
                    logger.warn("Unsupported browser type: {}. Defaulting to Chrome.", browserType);
                    driver = initializeChromeDriver();
            }
            
            // Configure driver
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            
            driverThreadLocal.set(driver);
            logger.info("WebDriver initialized successfully for browser: {}", browserType);
            
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for browser: {}", browserType, e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
        
        return driver;
    }
    
    /**
     * Initialize Chrome WebDriver
     */
    private static WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Add common Chrome options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        
        // Add headless option if needed
        String headless = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless");
        }
        
        return new ChromeDriver(options);
    }
    
    /**
     * Initialize Firefox WebDriver
     */
    private static WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        // Add headless option if needed
        String headless = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless");
        }
        
        return new FirefoxDriver(options);
    }
    
    /**
     * Initialize Edge WebDriver
     */
    private static WebDriver initializeEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        // Add headless option if needed
        String headless = System.getProperty("headless", "false");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless");
        }
        
        return new EdgeDriver(options);
    }
    
    /**
     * Initialize Safari WebDriver
     */
    private static WebDriver initializeSafariDriver() {
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }
    
    /**
     * Get current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
    
    /**
     * Quit WebDriver and clean up
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver quit successfully");
            } catch (Exception e) {
                logger.error("Error occurred while quitting WebDriver", e);
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
    
    /**
     * Check if WebDriver is initialized
     */
    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}
