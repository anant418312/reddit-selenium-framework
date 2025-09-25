package com.reddit.automation.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base Page class containing common functionality for all page objects
 */
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected static final int DEFAULT_TIMEOUT = 10;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        logger.info("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        logger.info("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present
     */
    protected WebElement waitForElementToBePresent(By locator) {
        logger.info("Waiting for element to be present: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Click on element with wait
     */
    protected void clickElement(By locator) {
        logger.info("Clicking on element: {}", locator);
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }
    
    /**
     * Send text to element with wait
     */
    protected void sendTextToElement(By locator, String text) {
        logger.info("Sending text '{}' to element: {}", text, locator);
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Get text from element
     */
    protected String getElementText(By locator) {
        logger.info("Getting text from element: {}", locator);
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }
    
    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed: {}", locator);
            return false;
        }
    }
    
    /**
     * Wait for page title to contain text
     */
    protected void waitForPageTitle(String title) {
        logger.info("Waiting for page title to contain: {}", title);
        wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Wait for URL to contain text
     */
    protected void waitForUrlToContain(String urlText) {
        logger.info("Waiting for URL to contain: {}", urlText);
        wait.until(ExpectedConditions.urlContains(urlText));
    }
    
    /**
     * Scroll to element
     */
    protected void scrollToElement(By locator) {
        logger.info("Scrolling to element: {}", locator);
        WebElement element = waitForElementToBePresent(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Get all elements matching locator
     */
    protected List<WebElement> getElements(By locator) {
        logger.info("Getting all elements matching: {}", locator);
        return driver.findElements(locator);
    }
    
    /**
     * Wait for element to disappear
     */
    protected boolean waitForElementToDisappear(By locator) {
        logger.info("Waiting for element to disappear: {}", locator);
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.warn("Element did not disappear: {}", locator);
            return false;
        }
    }
    
    /**
     * Get current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Navigate to URL
     */
    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }
    
    /**
     * Refresh page
     */
    protected void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }
    
    /**
     * Go back
     */
    protected void goBack() {
        logger.info("Going back");
        driver.navigate().back();
    }
    
    /**
     * Go forward
     */
    protected void goForward() {
        logger.info("Going forward");
        driver.navigate().forward();
    }
    
    /**
     * Find element within Shadow DOM
     */
    protected WebElement findElementInShadowDOM(By shadowHostLocator, By elementLocator) {
        logger.info("Finding element in Shadow DOM: {} within {}", elementLocator, shadowHostLocator);
        try {
            WebElement shadowHost = waitForElementToBePresent(shadowHostLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
            return shadowRoot.findElement(elementLocator);
        } catch (Exception e) {
            logger.error("Failed to find element in Shadow DOM", e);
            throw new RuntimeException("Element not found in Shadow DOM", e);
        }
    }
    
    /**
     * Click element within Shadow DOM
     */
    protected void clickElementInShadowDOM(By shadowHostLocator, By elementLocator) {
        logger.info("Clicking element in Shadow DOM: {} within {}", elementLocator, shadowHostLocator);
        WebElement element = findElementInShadowDOM(shadowHostLocator, elementLocator);
        element.click();
    }
    
    /**
     * Send text to element within Shadow DOM
     */
    protected void sendTextToElementInShadowDOM(By shadowHostLocator, By elementLocator, String text) {
        logger.info("Sending text '{}' to element in Shadow DOM: {} within {}", text, elementLocator, shadowHostLocator);
        WebElement element = findElementInShadowDOM(shadowHostLocator, elementLocator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Get text from element within Shadow DOM
     */
    protected String getElementTextFromShadowDOM(By shadowHostLocator, By elementLocator) {
        logger.info("Getting text from element in Shadow DOM: {} within {}", elementLocator, shadowHostLocator);
        WebElement element = findElementInShadowDOM(shadowHostLocator, elementLocator);
        return element.getText();
    }
    
    /**
     * Check if element is displayed within Shadow DOM
     */
    protected boolean isElementDisplayedInShadowDOM(By shadowHostLocator, By elementLocator) {
        try {
            WebElement element = findElementInShadowDOM(shadowHostLocator, elementLocator);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed in Shadow DOM: {} within {}", elementLocator, shadowHostLocator);
            return false;
        }
    }
    
    /**
     * Execute JavaScript to find elements in Shadow DOM
     */
    protected WebElement findElementByJavaScript(String script) {
        logger.info("Finding element using JavaScript: {}", script);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (WebElement) js.executeScript(script);
        } catch (Exception e) {
            logger.error("Failed to find element using JavaScript", e);
            throw new RuntimeException("Element not found using JavaScript", e);
        }
    }
    
    /**
     * Execute JavaScript to click element in Shadow DOM
     */
    protected void clickElementByJavaScript(String script) {
        logger.info("Clicking element using JavaScript: {}", script);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
        } catch (Exception e) {
            logger.error("Failed to click element using JavaScript", e);
            throw new RuntimeException("Failed to click element using JavaScript", e);
        }
    }
    
    /**
     * Execute JavaScript to send text to element in Shadow DOM
     */
    protected void sendTextByJavaScript(String script, String text) {
        logger.info("Sending text '{}' using JavaScript: {}", text, script);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script + ".value = '" + text + "'");
        } catch (Exception e) {
            logger.error("Failed to send text using JavaScript", e);
            throw new RuntimeException("Failed to send text using JavaScript", e);
        }
    }
}
