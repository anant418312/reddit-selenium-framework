package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple test to explore Reddit's Shadow DOM structure
 */
public class RedditShadowDOMExplorerTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditShadowDOMExplorerTest.class);
    
    private WebDriver driver;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up Reddit Shadow DOM Explorer test");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down Reddit Shadow DOM Explorer test");
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void exploreRedditShadowDOM() {
        logger.info("Starting Reddit Shadow DOM exploration");
        
        try {
            // Navigate to Reddit login page
            driver.get(baseUrl + "/login");
            logger.info("Navigated to Reddit login page: {}", baseUrl + "/login");
            
            // Wait for page to load
            Thread.sleep(5000);
            
            // Get page title
            String pageTitle = driver.getTitle();
            logger.info("Page title: {}", pageTitle);
            
            // Get current URL
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL: {}", currentUrl);
            
            // Try to find all custom elements on the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Check if shreddit-overlay-display exists
            try {
                String script1 = "return document.querySelector('shreddit-overlay-display')";
                WebElement overlay = (WebElement) js.executeScript(script1);
                if (overlay != null) {
                    logger.info("✅ Found shreddit-overlay-display element");
                    
                    // Try to access its shadow root
                    try {
                        String script2 = "return arguments[0].shadowRoot";
                        WebElement shadowRoot = (WebElement) js.executeScript(script2, overlay);
                        if (shadowRoot != null) {
                            logger.info("✅ Successfully accessed shadow root of shreddit-overlay-display");
                            
                            // Try to find shreddit-signup-drawer
                            try {
                                String script3 = "return arguments[0].querySelector('shreddit-signup-drawer')";
                                WebElement signupDrawer = (WebElement) js.executeScript(script3, shadowRoot);
                                if (signupDrawer != null) {
                                    logger.info("✅ Found shreddit-signup-drawer within shadow root");
                                    
                                    // Try to access its shadow root
                                    try {
                                        String script4 = "return arguments[0].shadowRoot";
                                        WebElement shadowRoot2 = (WebElement) js.executeScript(script4, signupDrawer);
                                        if (shadowRoot2 != null) {
                                            logger.info("✅ Successfully accessed second shadow root");
                                            
                                            // Try to find shreddit-slotter
                                            try {
                                                String script5 = "return arguments[0].querySelector('shreddit-slotter')";
                                                WebElement slotter = (WebElement) js.executeScript(script5, shadowRoot2);
                                                if (slotter != null) {
                                                    logger.info("✅ Found shreddit-slotter within second shadow root");
                                                    
                                                    // Try to access its shadow root
                                                    try {
                                                        String script6 = "return arguments[0].shadowRoot";
                                                        WebElement shadowRoot3 = (WebElement) js.executeScript(script6, slotter);
                                                        if (shadowRoot3 != null) {
                                                            logger.info("✅ Successfully accessed third shadow root");
                                                            
                                                            // Try to find input fields
                                                            try {
                                                                String script7 = "return arguments[0].querySelectorAll('input')";
                                                                Object inputs = js.executeScript(script7, shadowRoot3);
                                                                logger.info("✅ Found input elements in third shadow root: {}", inputs);
                                                                
                                                                // Try to find username field specifically
                                                                try {
                                                                    String script8 = "return arguments[0].querySelector('input[name=\"username\"], input[name=\"email\"], input[type=\"text\"]')";
                                                                    WebElement usernameField = (WebElement) js.executeScript(script8, shadowRoot3);
                                                                    if (usernameField != null) {
                                                                        logger.info("✅ Found username field in Shadow DOM!");
                                                                        
                                                                        // Try to enter text
                                                                        try {
                                                                            String script9 = "arguments[0].value = 'redditUser'";
                                                                            js.executeScript(script9, usernameField);
                                                                            logger.info("✅ Successfully entered 'redditUser' in username field!");
                                                                            
                                                                            // Verify the text was entered
                                                                            String script10 = "return arguments[0].value";
                                                                            String enteredText = (String) js.executeScript(script10, usernameField);
                                                                            logger.info("✅ Username field value: {}", enteredText);
                                                                            
                                                                        } catch (Exception e) {
                                                                            logger.error("❌ Failed to enter text in username field", e);
                                                                        }
                                                                    } else {
                                                                        logger.warn("⚠️ Username field not found in third shadow root");
                                                                    }
                                                                } catch (Exception e) {
                                                                    logger.error("❌ Failed to find username field", e);
                                                                }
                                                                
                                                            } catch (Exception e) {
                                                                logger.error("❌ Failed to find input elements", e);
                                                            }
                                                            
                                                        } else {
                                                            logger.warn("⚠️ Could not access third shadow root");
                                                        }
                                                    } catch (Exception e) {
                                                        logger.error("❌ Failed to access third shadow root", e);
                                                    }
                                                    
                                                } else {
                                                    logger.warn("⚠️ shreddit-slotter not found in second shadow root");
                                                }
                                            } catch (Exception e) {
                                                logger.error("❌ Failed to find shreddit-slotter", e);
                                            }
                                            
                                        } else {
                                            logger.warn("⚠️ Could not access second shadow root");
                                        }
                                    } catch (Exception e) {
                                        logger.error("❌ Failed to access second shadow root", e);
                                    }
                                    
                                } else {
                                    logger.warn("⚠️ shreddit-signup-drawer not found in first shadow root");
                                }
                            } catch (Exception e) {
                                logger.error("❌ Failed to find shreddit-signup-drawer", e);
                            }
                            
                        } else {
                            logger.warn("⚠️ Could not access shadow root of shreddit-overlay-display");
                        }
                    } catch (Exception e) {
                        logger.error("❌ Failed to access shadow root", e);
                    }
                    
                } else {
                    logger.warn("⚠️ shreddit-overlay-display not found on page");
                }
            } catch (Exception e) {
                logger.error("❌ Failed to find shreddit-overlay-display", e);
            }
            
            // Wait to see the result
            Thread.sleep(3000);
            
        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
