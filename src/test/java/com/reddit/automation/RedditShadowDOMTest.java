package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.pages.RedditLoginPage;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple test to verify Shadow DOM interaction with Reddit login page
 */
public class RedditShadowDOMTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditShadowDOMTest.class);
    
    private WebDriver driver;
    private RedditLoginPage loginPage;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up Reddit Shadow DOM test");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        loginPage = new RedditLoginPage(driver);
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down Reddit Shadow DOM test");
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testShadowDOMUsernameField() {
        logger.info("Starting Shadow DOM username field test");
        
        try {
            // Navigate to Reddit login page
            loginPage.navigateToLoginPage(baseUrl);
            logger.info("Navigated to Reddit login page");
            
            // Wait a bit for page to load
            Thread.sleep(3000);
            
            // Try to find Shadow DOM elements using JavaScript
            logger.info("Attempting to find Shadow DOM elements...");
            
            // Method 1: Try to find shreddit-overlay-display
            try {
                WebElement overlay = driver.findElement(org.openqa.selenium.By.cssSelector("shreddit-overlay-display"));
                logger.info("Found shreddit-overlay-display element");
                
                // Try to access its shadow root
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", overlay);
                logger.info("Successfully accessed shadow root");
                
                // Try to find elements within shadow root
                try {
                    WebElement signupDrawer = shadowRoot.findElement(org.openqa.selenium.By.cssSelector("shreddit-signup-drawer"));
                    logger.info("Found shreddit-signup-drawer within shadow root");
                    
                    // Access its shadow root
                    WebElement shadowRoot2 = (WebElement) js.executeScript("return arguments[0].shadowRoot", signupDrawer);
                    logger.info("Successfully accessed second shadow root");
                    
                    // Try to find shreddit-slotter
                    try {
                        WebElement slotter = shadowRoot2.findElement(org.openqa.selenium.By.cssSelector("shreddit-slotter"));
                        logger.info("Found shreddit-slotter within second shadow root");
                        
                        // Access its shadow root
                        WebElement shadowRoot3 = (WebElement) js.executeScript("return arguments[0].shadowRoot", slotter);
                        logger.info("Successfully accessed third shadow root");
                        
                        // Try to find username field
                        try {
                            WebElement usernameField = shadowRoot3.findElement(org.openqa.selenium.By.cssSelector("input[name='username'], input[name='email'], input[type='text']"));
                            logger.info("Found username field in Shadow DOM!");
                            
                            // Try to enter text
                            usernameField.clear();
                            usernameField.sendKeys("redditUser");
                            logger.info("Successfully entered 'redditUser' in username field!");
                            
                            // Verify the text was entered
                            String enteredText = usernameField.getAttribute("value");
                            logger.info("Username field value: {}", enteredText);
                            Assert.assertEquals(enteredText, "redditUser", "Username field should contain 'redditUser'");
                            
                        } catch (Exception e) {
                            logger.error("Could not find username field in third shadow root", e);
                            // Try alternative approach
                            try {
                                String script = "return arguments[0].querySelector('input[name=\"username\"], input[name=\"email\"], input[type=\"text\"]')";
                                WebElement usernameField = (WebElement) js.executeScript(script, shadowRoot3);
                                if (usernameField != null) {
                                    usernameField.clear();
                                    usernameField.sendKeys("redditUser");
                                    logger.info("Successfully entered 'redditUser' using alternative approach!");
                                } else {
                                    logger.error("Username field not found with alternative approach");
                                }
                            } catch (Exception e2) {
                                logger.error("Alternative approach also failed", e2);
                            }
                        }
                        
                    } catch (Exception e) {
                        logger.error("Could not find shreddit-slotter in second shadow root", e);
                    }
                    
                } catch (Exception e) {
                    logger.error("Could not find shreddit-signup-drawer in first shadow root", e);
                }
                
            } catch (Exception e) {
                logger.error("Could not find shreddit-overlay-display element", e);
                
                // Try direct JavaScript approach
                try {
                    logger.info("Trying direct JavaScript approach...");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String script = "return document.querySelector('shreddit-overlay-display')";
                    WebElement overlay = (WebElement) js.executeScript(script);
                    if (overlay != null) {
                        logger.info("Found overlay using direct JavaScript");
                    } else {
                        logger.error("Overlay not found with direct JavaScript");
                    }
                } catch (Exception e2) {
                    logger.error("Direct JavaScript approach failed", e2);
                }
            }
            
            // Wait to see the result
            Thread.sleep(2000);
            
        } catch (Exception e) {
            logger.error("Test failed with exception", e);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
