package com.reddit.automation.framework.pages;

import com.reddit.automation.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reddit Login Page Object Model
 */
public class RedditLoginPage extends BasePage {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditLoginPage.class);
    
    // Reddit Shadow DOM Elements - Updated for Reddit's actual structure
    private static final By SHREDDIT_OVERLAY = By.cssSelector("shreddit-overlay-display");
    private static final By SHREDDIT_SIGNUP_DRAWER = By.cssSelector("shreddit-signup-drawer");
    private static final By SHREDDIT_SLOTTER = By.cssSelector("shreddit-slotter");
    private static final By USERNAME_FIELD = By.cssSelector("input[name='username'], input[name='email'], input[type='text']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[type='password']");
    private static final By LOGIN_BUTTON = By.cssSelector("button.login, button[type='submit'], input[type='submit']");
    private static final By REMEMBER_ME_CHECKBOX = By.cssSelector("input[type='checkbox']");
    private static final By ERROR_MESSAGE = By.cssSelector(".error, .alert, .message, [class*='error'], [class*='alert']");
    private static final By FORGOT_PASSWORD_LINK = By.cssSelector("a[href*='forgot'], a[href*='reset']");
    private static final By SIGNUP_LINK = By.cssSelector("a[href*='signup'], a[href*='register']");
    private static final By LOGIN_FORM = By.cssSelector("form");
    
    public RedditLoginPage(WebDriver driver) {
        super(driver);
        logger.info("RedditLoginPage initialized");
    }
    
    /**
     * Navigate through Reddit's nested Shadow DOM structure
     */
    private WebElement navigateShadowDOM() {
        logger.info("Navigating through Reddit's Shadow DOM structure");
        try {
            // Step 1: Find the shreddit-overlay-display element
            WebElement shadowHost1 = waitForElementToBePresent(SHREDDIT_OVERLAY);
            logger.info("Found shreddit-overlay-display element");
            
            // Step 2: Access its shadow root
            WebElement shadowRoot1 = expandShadowElement(shadowHost1);
            logger.info("Accessed first shadow root");
            
            // Step 3: Find shreddit-signup-drawer within the shadow root
            WebElement shadowHost2 = shadowRoot1.findElement(SHREDDIT_SIGNUP_DRAWER);
            logger.info("Found shreddit-signup-drawer element");
            
            // Step 4: Access its shadow root
            WebElement shadowRoot2 = expandShadowElement(shadowHost2);
            logger.info("Accessed second shadow root");
            
            // Step 5: Find shreddit-slotter within the shadow root
            WebElement shadowHost3 = shadowRoot2.findElement(SHREDDIT_SLOTTER);
            logger.info("Found shreddit-slotter element");
            
            // Step 6: Access its shadow root
            WebElement shadowRoot3 = expandShadowElement(shadowHost3);
            logger.info("Accessed third shadow root");
            
            return shadowRoot3;
        } catch (Exception e) {
            logger.error("Failed to navigate Shadow DOM structure", e);
            throw new RuntimeException("Could not access Reddit's Shadow DOM elements", e);
        }
    }
    
    /**
     * Expand shadow element using JavaScript
     */
    private WebElement expandShadowElement(WebElement element) {
        logger.info("Expanding shadow element");
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
        } catch (Exception e) {
            logger.error("Failed to expand shadow element", e);
            throw new RuntimeException("Could not access shadow root", e);
        }
    }
    
    /**
     * Navigate to login page
     */
    public void navigateToLoginPage(String baseUrl) {
        logger.info("Navigating to Reddit login page: {}", baseUrl + "/login");
        navigateToUrl(baseUrl + "/login");
        waitForPageTitle("Reddit");
    }
    
    /**
     * Enter username with Reddit Shadow DOM support
     */
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        try {
            // Navigate through Reddit's Shadow DOM structure
            WebElement shadowRoot = navigateShadowDOM();
            
            // Find username field within the shadow root
            WebElement usernameField = shadowRoot.findElement(USERNAME_FIELD);
            logger.info("Found username field in Shadow DOM");
            
            // Clear and enter username
            usernameField.clear();
            usernameField.sendKeys(username);
            logger.info("Successfully entered username in Shadow DOM");
            
        } catch (Exception e) {
            logger.warn("Shadow DOM approach failed, trying JavaScript approach", e);
            try {
                // Fallback to JavaScript approach
                String script = "document.querySelector('shreddit-overlay-display').shadowRoot.querySelector('shreddit-signup-drawer').shadowRoot.querySelector('shreddit-slotter').shadowRoot.querySelector('input[name=\"username\"], input[name=\"email\"]')";
                sendTextByJavaScript(script, username);
            } catch (Exception e2) {
                logger.error("All approaches failed for username field", e2);
                throw new RuntimeException("Could not find or interact with username field", e2);
            }
        }
    }
    
    /**
     * Enter password with Reddit Shadow DOM support
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        try {
            // Navigate through Reddit's Shadow DOM structure
            WebElement shadowRoot = navigateShadowDOM();
            
            // Find password field within the shadow root
            WebElement passwordField = shadowRoot.findElement(PASSWORD_FIELD);
            logger.info("Found password field in Shadow DOM");
            
            // Clear and enter password
            passwordField.clear();
            passwordField.sendKeys(password);
            logger.info("Successfully entered password in Shadow DOM");
            
        } catch (Exception e) {
            logger.warn("Shadow DOM approach failed, trying JavaScript approach", e);
            try {
                // Fallback to JavaScript approach
                String script = "document.querySelector('shreddit-overlay-display').shadowRoot.querySelector('shreddit-signup-drawer').shadowRoot.querySelector('shreddit-slotter').shadowRoot.querySelector('input[type=\"password\"]')";
                sendTextByJavaScript(script, password);
            } catch (Exception e2) {
                logger.error("All approaches failed for password field", e2);
                throw new RuntimeException("Could not find or interact with password field", e2);
            }
        }
    }
    
    /**
     * Click login button with Reddit Shadow DOM support
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        try {
            // Navigate through Reddit's Shadow DOM structure
            WebElement shadowRoot = navigateShadowDOM();
            
            // Find login button within the shadow root
            WebElement loginButton = shadowRoot.findElement(LOGIN_BUTTON);
            logger.info("Found login button in Shadow DOM");
            
            // Click the login button
            loginButton.click();
            logger.info("Successfully clicked login button in Shadow DOM");
            
        } catch (Exception e) {
            logger.warn("Shadow DOM approach failed, trying JavaScript approach", e);
            try {
                // Fallback to JavaScript approach
                String script = "document.querySelector('shreddit-overlay-display').shadowRoot.querySelector('shreddit-signup-drawer').shadowRoot.querySelector('shreddit-slotter').shadowRoot.querySelector('button.login, button[type=\"submit\"]')";
                clickElementByJavaScript(script);
            } catch (Exception e2) {
                logger.error("All approaches failed for login button", e2);
                throw new RuntimeException("Could not find or interact with login button", e2);
            }
        }
    }
    
    /**
     * Perform login
     */
    public void login(String username, String password) {
        logger.info("Performing login for user: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    /**
     * Check remember me checkbox
     */
    public void checkRememberMe() {
        logger.info("Checking remember me checkbox");
        if (!isElementSelected(REMEMBER_ME_CHECKBOX)) {
            clickElement(REMEMBER_ME_CHECKBOX);
        }
    }
    
    /**
     * Uncheck remember me checkbox
     */
    public void uncheckRememberMe() {
        logger.info("Unchecking remember me checkbox");
        if (isElementSelected(REMEMBER_ME_CHECKBOX)) {
            clickElement(REMEMBER_ME_CHECKBOX);
        }
    }
    
    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        logger.info("Checking if error message is displayed");
        return isElementDisplayed(ERROR_MESSAGE);
    }
    
    /**
     * Get error message text
     */
    public String getErrorMessage() {
        logger.info("Getting error message text");
        if (isErrorMessageDisplayed()) {
            return getElementText(ERROR_MESSAGE);
        }
        return "";
    }
    
    /**
     * Click forgot password link
     */
    public void clickForgotPasswordLink() {
        logger.info("Clicking forgot password link");
        clickElement(FORGOT_PASSWORD_LINK);
    }
    
    /**
     * Click signup link
     */
    public void clickSignupLink() {
        logger.info("Clicking signup link");
        clickElement(SIGNUP_LINK);
    }
    
    /**
     * Check if login form is displayed
     */
    public boolean isLoginFormDisplayed() {
        logger.info("Checking if login form is displayed");
        return isElementDisplayed(LOGIN_FORM);
    }
    
    /**
     * Check if username field is displayed with Reddit Shadow DOM support
     */
    public boolean isUsernameFieldDisplayed() {
        logger.info("Checking if username field is displayed");
        try {
            // Navigate through Reddit's Shadow DOM structure
            WebElement shadowRoot = navigateShadowDOM();
            
            // Find username field within the shadow root
            WebElement usernameField = shadowRoot.findElement(USERNAME_FIELD);
            boolean isDisplayed = usernameField.isDisplayed();
            logger.info("Username field display status: {}", isDisplayed);
            return isDisplayed;
            
        } catch (Exception e) {
            logger.warn("Shadow DOM approach failed, trying JavaScript approach", e);
            try {
                String script = "document.querySelector('shreddit-overlay-display').shadowRoot.querySelector('shreddit-signup-drawer').shadowRoot.querySelector('shreddit-slotter').shadowRoot.querySelector('input[name=\"username\"], input[name=\"email\"]')";
                WebElement element = findElementByJavaScript(script);
                return element != null && element.isDisplayed();
            } catch (Exception e2) {
                logger.error("All approaches failed for username field detection", e2);
                return false;
            }
        }
    }
    
    /**
     * Check if password field is displayed with Reddit Shadow DOM support
     */
    public boolean isPasswordFieldDisplayed() {
        logger.info("Checking if password field is displayed");
        try {
            // Navigate through Reddit's Shadow DOM structure
            WebElement shadowRoot = navigateShadowDOM();
            
            // Find password field within the shadow root
            WebElement passwordField = shadowRoot.findElement(PASSWORD_FIELD);
            boolean isDisplayed = passwordField.isDisplayed();
            logger.info("Password field display status: {}", isDisplayed);
            return isDisplayed;
            
        } catch (Exception e) {
            logger.warn("Shadow DOM approach failed, trying JavaScript approach", e);
            try {
                String script = "document.querySelector('shreddit-overlay-display').shadowRoot.querySelector('shreddit-signup-drawer').shadowRoot.querySelector('shreddit-slotter').shadowRoot.querySelector('input[type=\"password\"]')";
                WebElement element = findElementByJavaScript(script);
                return element != null && element.isDisplayed();
            } catch (Exception e2) {
                logger.error("All approaches failed for password field detection", e2);
                return false;
            }
        }
    }
    
    /**
     * Check if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        logger.info("Checking if login button is displayed");
        return isElementDisplayed(LOGIN_BUTTON);
    }
    
    /**
     * Check if username field is enabled
     */
    public boolean isUsernameFieldEnabled() {
        logger.info("Checking if username field is enabled");
        try {
            WebElement element = waitForElementToBeVisible(USERNAME_FIELD);
            return element.isEnabled();
        } catch (Exception e) {
            logger.warn("Username field not found or not enabled");
            return false;
        }
    }
    
    /**
     * Check if password field is enabled
     */
    public boolean isPasswordFieldEnabled() {
        logger.info("Checking if password field is enabled");
        try {
            WebElement element = waitForElementToBeVisible(PASSWORD_FIELD);
            return element.isEnabled();
        } catch (Exception e) {
            logger.warn("Password field not found or not enabled");
            return false;
        }
    }
    
    /**
     * Clear username field
     */
    public void clearUsernameField() {
        logger.info("Clearing username field");
        WebElement element = waitForElementToBeVisible(USERNAME_FIELD);
        element.clear();
    }
    
    /**
     * Clear password field
     */
    public void clearPasswordField() {
        logger.info("Clearing password field");
        WebElement element = waitForElementToBeVisible(PASSWORD_FIELD);
        element.clear();
    }
    
    /**
     * Get username field value
     */
    public String getUsernameFieldValue() {
        logger.info("Getting username field value");
        WebElement element = waitForElementToBeVisible(USERNAME_FIELD);
        return element.getAttribute("value");
    }
    
    /**
     * Get password field value
     */
    public String getPasswordFieldValue() {
        logger.info("Getting password field value");
        WebElement element = waitForElementToBeVisible(PASSWORD_FIELD);
        return element.getAttribute("value");
    }
    
    /**
     * Check if element is selected (for checkboxes)
     */
    private boolean isElementSelected(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            return element.isSelected();
        } catch (Exception e) {
            logger.warn("Element not found or not selectable: {}", locator);
            return false;
        }
    }
}
