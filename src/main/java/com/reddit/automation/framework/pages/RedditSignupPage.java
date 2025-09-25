package com.reddit.automation.framework.pages;

import com.reddit.automation.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reddit Signup Page Object Model
 */
public class RedditSignupPage extends BasePage {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditSignupPage.class);
    
    // Page Elements
    private static final By USERNAME_FIELD = By.xpath("//input[@name='username']");
    private static final By EMAIL_FIELD = By.xpath("//input[@name='email']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private static final By CONFIRM_PASSWORD_FIELD = By.xpath("//input[@name='confirmPassword']");
    private static final By SIGNUP_BUTTON = By.xpath("//button[@type='submit' and contains(text(), 'Sign Up')]");
    private static final By TERMS_CHECKBOX = By.xpath("//input[@type='checkbox' and @name='terms']");
    private static final By PRIVACY_CHECKBOX = By.xpath("//input[@type='checkbox' and @name='privacy']");
    private static final By ERROR_MESSAGE = By.xpath("//div[contains(@class, 'error') or contains(@class, 'alert')]");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class, 'success') or contains(@class, 'message')]");
    private static final By LOGIN_LINK = By.xpath("//a[contains(text(), 'Log In') or contains(text(), 'Sign In')]");
    private static final By SIGNUP_FORM = By.xpath("//form[contains(@class, 'signup') or contains(@class, 'register')]");
    
    public RedditSignupPage(WebDriver driver) {
        super(driver);
        logger.info("RedditSignupPage initialized");
    }
    
    /**
     * Navigate to signup page
     */
    public void navigateToSignupPage(String baseUrl) {
        logger.info("Navigating to Reddit signup page: {}", baseUrl + "/register");
        navigateToUrl(baseUrl + "/register");
        waitForPageTitle("Reddit");
    }
    
    /**
     * Enter username
     */
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        sendTextToElement(USERNAME_FIELD, username);
    }
    
    /**
     * Enter email
     */
    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        sendTextToElement(EMAIL_FIELD, email);
    }
    
    /**
     * Enter password
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        sendTextToElement(PASSWORD_FIELD, password);
    }
    
    /**
     * Enter confirm password
     */
    public void enterConfirmPassword(String password) {
        logger.info("Entering confirm password");
        sendTextToElement(CONFIRM_PASSWORD_FIELD, password);
    }
    
    /**
     * Click signup button
     */
    public void clickSignupButton() {
        logger.info("Clicking signup button");
        clickElement(SIGNUP_BUTTON);
    }
    
    /**
     * Perform signup
     */
    public void signup(String username, String email, String password) {
        logger.info("Performing signup for user: {}", username);
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickSignupButton();
    }
    
    /**
     * Check terms checkbox
     */
    public void checkTerms() {
        logger.info("Checking terms checkbox");
        if (!isElementSelected(TERMS_CHECKBOX)) {
            clickElement(TERMS_CHECKBOX);
        }
    }
    
    /**
     * Check privacy checkbox
     */
    public void checkPrivacy() {
        logger.info("Checking privacy checkbox");
        if (!isElementSelected(PRIVACY_CHECKBOX)) {
            clickElement(PRIVACY_CHECKBOX);
        }
    }
    
    /**
     * Uncheck terms checkbox
     */
    public void uncheckTerms() {
        logger.info("Unchecking terms checkbox");
        if (isElementSelected(TERMS_CHECKBOX)) {
            clickElement(TERMS_CHECKBOX);
        }
    }
    
    /**
     * Uncheck privacy checkbox
     */
    public void uncheckPrivacy() {
        logger.info("Unchecking privacy checkbox");
        if (isElementSelected(PRIVACY_CHECKBOX)) {
            clickElement(PRIVACY_CHECKBOX);
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
     * Check if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        logger.info("Checking if success message is displayed");
        return isElementDisplayed(SUCCESS_MESSAGE);
    }
    
    /**
     * Get success message text
     */
    public String getSuccessMessage() {
        logger.info("Getting success message text");
        if (isSuccessMessageDisplayed()) {
            return getElementText(SUCCESS_MESSAGE);
        }
        return "";
    }
    
    /**
     * Click login link
     */
    public void clickLoginLink() {
        logger.info("Clicking login link");
        clickElement(LOGIN_LINK);
    }
    
    /**
     * Check if signup form is displayed
     */
    public boolean isSignupFormDisplayed() {
        logger.info("Checking if signup form is displayed");
        return isElementDisplayed(SIGNUP_FORM);
    }
    
    /**
     * Check if username field is displayed
     */
    public boolean isUsernameFieldDisplayed() {
        logger.info("Checking if username field is displayed");
        return isElementDisplayed(USERNAME_FIELD);
    }
    
    /**
     * Check if email field is displayed
     */
    public boolean isEmailFieldDisplayed() {
        logger.info("Checking if email field is displayed");
        return isElementDisplayed(EMAIL_FIELD);
    }
    
    /**
     * Check if password field is displayed
     */
    public boolean isPasswordFieldDisplayed() {
        logger.info("Checking if password field is displayed");
        return isElementDisplayed(PASSWORD_FIELD);
    }
    
    /**
     * Check if confirm password field is displayed
     */
    public boolean isConfirmPasswordFieldDisplayed() {
        logger.info("Checking if confirm password field is displayed");
        return isElementDisplayed(CONFIRM_PASSWORD_FIELD);
    }
    
    /**
     * Check if signup button is displayed
     */
    public boolean isSignupButtonDisplayed() {
        logger.info("Checking if signup button is displayed");
        return isElementDisplayed(SIGNUP_BUTTON);
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
     * Check if email field is enabled
     */
    public boolean isEmailFieldEnabled() {
        logger.info("Checking if email field is enabled");
        try {
            WebElement element = waitForElementToBeVisible(EMAIL_FIELD);
            return element.isEnabled();
        } catch (Exception e) {
            logger.warn("Email field not found or not enabled");
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
     * Clear email field
     */
    public void clearEmailField() {
        logger.info("Clearing email field");
        WebElement element = waitForElementToBeVisible(EMAIL_FIELD);
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
     * Clear confirm password field
     */
    public void clearConfirmPasswordField() {
        logger.info("Clearing confirm password field");
        WebElement element = waitForElementToBeVisible(CONFIRM_PASSWORD_FIELD);
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
     * Get email field value
     */
    public String getEmailFieldValue() {
        logger.info("Getting email field value");
        WebElement element = waitForElementToBeVisible(EMAIL_FIELD);
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
     * Get confirm password field value
     */
    public String getConfirmPasswordFieldValue() {
        logger.info("Getting confirm password field value");
        WebElement element = waitForElementToBeVisible(CONFIRM_PASSWORD_FIELD);
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
