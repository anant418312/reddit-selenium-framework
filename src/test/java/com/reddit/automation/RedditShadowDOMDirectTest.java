package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Direct test using Selenium 4 native Shadow DOM support
 */
public class RedditShadowDOMDirectTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditShadowDOMDirectTest.class);
    
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up Reddit Shadow DOM Direct test");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down Reddit Shadow DOM Direct test");
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testDirectShadowDOMInteraction() {
        logger.info("Starting direct Shadow DOM interaction test");
        
        try {
            // Navigate to Reddit login page
            driver.get(baseUrl + "/login");
            logger.info("Navigated to Reddit login page: {}", baseUrl + "/login");
            
            // Wait for page to load
            Thread.sleep(5000);
            
            // Get page title and URL
            String pageTitle = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            logger.info("Page title: {}", pageTitle);
            logger.info("Current URL: {}", currentUrl);
            
            // Method 1: Try Selenium 4 native Shadow DOM support
            try {
                logger.info("🔍 Method 1: Trying Selenium 4 native Shadow DOM support...");
                
                // Find the shadow host element
                WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("shreddit-overlay-display")));
                logger.info("✅ Found shadow host: shreddit-overlay-display");
                
                // Access the shadow root using Selenium 4 method
                SearchContext shadowRoot = shadowHost.getShadowRoot();
                logger.info("✅ Successfully accessed shadow root");
                
                // Find elements within the shadow root
                WebElement signupDrawer = shadowRoot.findElement(By.cssSelector("shreddit-signup-drawer"));
                logger.info("✅ Found shreddit-signup-drawer within shadow root");
                
                // Access second shadow root
                SearchContext shadowRoot2 = signupDrawer.getShadowRoot();
                logger.info("✅ Successfully accessed second shadow root");
                
                // Find shreddit-slotter
                WebElement slotter = shadowRoot2.findElement(By.cssSelector("shreddit-slotter"));
                logger.info("✅ Found shreddit-slotter within second shadow root");
                
                // Access third shadow root
                SearchContext shadowRoot3 = slotter.getShadowRoot();
                logger.info("✅ Successfully accessed third shadow root");
                
                // Find username field
                WebElement usernameField = shadowRoot3.findElement(By.cssSelector("input[name='username'], input[name='email'], input[type='text']"));
                logger.info("✅ Found username field in Shadow DOM!");
                
                // Clear and enter text
                usernameField.clear();
                usernameField.sendKeys("redditUser");
                logger.info("✅ Successfully entered 'redditUser' in username field!");
                
                // Verify the text was entered
                String enteredText = usernameField.getAttribute("value");
                logger.info("✅ Username field value: '{}'", enteredText);
                
                if ("redditUser".equals(enteredText)) {
                    logger.info("🎉 SUCCESS: Text was successfully entered and verified!");
                } else {
                    logger.warn("⚠️ Text verification failed. Expected: 'redditUser', Actual: '{}'", enteredText);
                }
                
            } catch (Exception e) {
                logger.error("❌ Method 1 failed: {}", e.getMessage());
                
                // Method 2: Try JavaScript approach
                try {
                    logger.info("🔍 Method 2: Trying JavaScript approach...");
                    
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    
                    // Try to find and interact with username field using JavaScript
                    String script = "const shadowHost = document.querySelector('shreddit-overlay-display');" +
                        "if (!shadowHost) { return 'Shadow host not found'; }" +
                        "const shadowRoot = shadowHost.shadowRoot;" +
                        "if (!shadowRoot) { return 'Shadow root not accessible'; }" +
                        "const signupDrawer = shadowRoot.querySelector('shreddit-signup-drawer');" +
                        "if (!signupDrawer) { return 'Signup drawer not found'; }" +
                        "const shadowRoot2 = signupDrawer.shadowRoot;" +
                        "if (!shadowRoot2) { return 'Second shadow root not accessible'; }" +
                        "const slotter = shadowRoot2.querySelector('shreddit-slotter');" +
                        "if (!slotter) { return 'Slotter not found'; }" +
                        "const shadowRoot3 = slotter.shadowRoot;" +
                        "if (!shadowRoot3) { return 'Third shadow root not accessible'; }" +
                        "const usernameField = shadowRoot3.querySelector('input[name=\"username\"], input[name=\"email\"], input[type=\"text\"]');" +
                        "if (!usernameField) { return 'Username field not found'; }" +
                        "usernameField.value = 'redditUser';" +
                        "usernameField.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "usernameField.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "return 'Success: ' + usernameField.value;";
                    
                    String result = (String) js.executeScript(script);
                    logger.info("JavaScript result: {}", result);
                    
                    if (result.startsWith("Success:")) {
                        logger.info("🎉 SUCCESS: JavaScript approach worked!");
                    } else {
                        logger.warn("⚠️ JavaScript approach failed: {}", result);
                    }
                    
                } catch (Exception e2) {
                    logger.error("❌ Method 2 also failed: {}", e2.getMessage());
                    
                    // Method 3: Try alternative selectors
                    try {
                        logger.info("🔍 Method 3: Trying alternative selectors...");
                        
                        // Try to find any input field on the page
                        WebElement inputField = driver.findElement(By.cssSelector("input[type='text'], input[type='email'], input[name='username']"));
                        if (inputField != null) {
                            inputField.clear();
                            inputField.sendKeys("redditUser");
                            logger.info("✅ Found and filled input field using alternative selector");
                        }
                        
                    } catch (Exception e3) {
                        logger.error("❌ Method 3 also failed: {}", e3.getMessage());
                    }
                }
            }
            
            // Wait to see the result
            Thread.sleep(3000);
            
        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
