package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Simple test to find any input field on Reddit login page
 */
public class RedditSimpleInputTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditSimpleInputTest.class);
    
    private WebDriver driver;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up Reddit Simple Input test");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down Reddit Simple Input test");
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testFindInputFields() {
        logger.info("Starting simple input field test");
        
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
            
            // Try to find all input fields on the page
            logger.info("🔍 Looking for all input fields on the page...");
            
            List<WebElement> allInputs = driver.findElements(By.cssSelector("input"));
            logger.info("Found {} input elements on the page", allInputs.size());
            
            for (int i = 0; i < allInputs.size(); i++) {
                WebElement input = allInputs.get(i);
                try {
                    String type = input.getAttribute("type");
                    String name = input.getAttribute("name");
                    String id = input.getAttribute("id");
                    String placeholder = input.getAttribute("placeholder");
                    boolean isDisplayed = input.isDisplayed();
                    boolean isEnabled = input.isEnabled();
                    
                    logger.info("Input {}: type='{}', name='{}', id='{}', placeholder='{}', displayed={}, enabled={}", 
                        i, type, name, id, placeholder, isDisplayed, isEnabled);
                    
                    // Try to enter text in this input
                    if (isDisplayed && isEnabled && (type == null || !type.equals("hidden"))) {
                        try {
                            input.clear();
                            input.sendKeys("redditUser");
                            String value = input.getAttribute("value");
                            logger.info("✅ Successfully entered text in input {}: '{}'", i, value);
                            
                            if ("redditUser".equals(value)) {
                                logger.info("🎉 SUCCESS: Found working input field at index {}!", i);
                                break;
                            }
                        } catch (Exception e) {
                            logger.warn("⚠️ Could not enter text in input {}: {}", i, e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    logger.warn("⚠️ Could not get attributes for input {}: {}", i, e.getMessage());
                }
            }
            
            // Try JavaScript approach to find all inputs
            logger.info("🔍 Trying JavaScript approach to find all inputs...");
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "const inputs = document.querySelectorAll('input');" +
                "const results = [];" +
                "for (let i = 0; i < inputs.length; i++) {" +
                "    const input = inputs[i];" +
                "    results.push({" +
                "        index: i," +
                "        type: input.type," +
                "        name: input.name," +
                "        id: input.id," +
                "        placeholder: input.placeholder," +
                "        displayed: input.offsetParent !== null," +
                "        enabled: !input.disabled" +
                "    });" +
                "}" +
                "return results;";
            
            Object result = js.executeScript(script);
            logger.info("JavaScript found inputs: {}", result);
            
            // Try to find inputs in shadow DOM
            logger.info("🔍 Trying to find inputs in shadow DOM...");
            
            String shadowScript = "const shadowHosts = document.querySelectorAll('*');" +
                "const shadowInputs = [];" +
                "for (let host of shadowHosts) {" +
                "    if (host.shadowRoot) {" +
                "        const inputs = host.shadowRoot.querySelectorAll('input');" +
                "        for (let input of inputs) {" +
                "            shadowInputs.push({" +
                "                host: host.tagName," +
                "                type: input.type," +
                "                name: input.name," +
                "                id: input.id," +
                "                placeholder: input.placeholder" +
                "            });" +
                "        }" +
                "    }" +
                "}" +
                "return shadowInputs;";
            
            Object shadowResult = js.executeScript(shadowScript);
            logger.info("JavaScript found shadow inputs: {}", shadowResult);
            
            // Wait to see the result
            Thread.sleep(3000);
            
        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
