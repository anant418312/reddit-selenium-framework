package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Basic test to verify framework is working
 */
public class RedditBasicTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditBasicTest.class);
    
    private WebDriver driver;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up Reddit Basic test");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down Reddit Basic test");
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testBasicNavigation() {
        logger.info("Starting basic navigation test");
        
        try {
            // Navigate to Reddit
            driver.get(baseUrl);
            logger.info("Navigated to Reddit: {}", baseUrl);
            
            // Get page title
            String pageTitle = driver.getTitle();
            logger.info("Page title: {}", pageTitle);
            
            // Get current URL
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL: {}", currentUrl);
            
            // Wait a bit
            Thread.sleep(2000);
            
            logger.info("✅ Basic test completed successfully!");
            
        } catch (Exception e) {
            logger.error("❌ Basic test failed: {}", e.getMessage(), e);
        }
    }
}
