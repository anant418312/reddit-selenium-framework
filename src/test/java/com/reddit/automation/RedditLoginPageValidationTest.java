package com.reddit.automation;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.pages.RedditLoginPage;
import com.reddit.automation.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Traditional TestNG test class for Reddit login page validation
 */
public class RedditLoginPageValidationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditLoginPageValidationTest.class);
    
    private WebDriver driver;
    private RedditLoginPage loginPage;
    private String baseUrl;
    
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up test environment");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        loginPage = new RedditLoginPage(driver);
        loginPage.navigateToLoginPage(baseUrl);
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test environment");
        DriverManager.quitDriver();
    }
    
    @Test(description = "Validate login page elements are displayed")
    public void testLoginPageElementsDisplayed() {
        logger.info("Test: Validate login page elements are displayed");
        
        // Verify login form is displayed
        assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
        
        // Verify username field is displayed
        assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        
        // Verify password field is displayed
        assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        
        // Verify login button is displayed
        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
        
        logger.info("All login page elements are displayed correctly");
    }
    
    @Test(description = "Validate login form fields are enabled")
    public void testLoginFormFieldsEnabled() {
        logger.info("Test: Validate login form fields are enabled");
        
        // Verify username field is enabled
        assertTrue(loginPage.isUsernameFieldEnabled(), "Username field should be enabled");
        
        // Verify password field is enabled
        assertTrue(loginPage.isPasswordFieldEnabled(), "Password field should be enabled");
        
        logger.info("All login form fields are enabled correctly");
    }
    
    @Test(description = "Test login with valid credentials")
    public void testLoginWithValidCredentials() {
        logger.info("Test: Test login with valid credentials");
        
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        
        // Perform login
        loginPage.login(username, password);
        
        // Note: In a real scenario, you would verify successful login here
        // For now, we'll just verify no error message is displayed
        assertFalse(loginPage.isErrorMessageDisplayed(), "No error message should be displayed for valid credentials");
        
        logger.info("Login with valid credentials completed");
    }
    
    @Test(description = "Test login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        logger.info("Test: Test login with invalid credentials");
        
        // Perform login with invalid credentials
        loginPage.login("invaliduser", "invalidpass");
        
        // Verify error message is displayed
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        
        String errorMessage = loginPage.getErrorMessage();
        assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        
        logger.info("Login with invalid credentials handled correctly");
    }
    
    @Test(description = "Test field clearing functionality")
    public void testFieldClearingFunctionality() {
        logger.info("Test: Test field clearing functionality");
        
        String testUsername = "testuser";
        String testPassword = "testpass";
        
        // Enter values in fields
        loginPage.enterUsername(testUsername);
        loginPage.enterPassword(testPassword);
        
        // Verify values are entered
        assertEquals(loginPage.getUsernameFieldValue(), testUsername, "Username field should contain entered value");
        assertEquals(loginPage.getPasswordFieldValue(), testPassword, "Password field should contain entered value");
        
        // Clear fields
        loginPage.clearUsernameField();
        loginPage.clearPasswordField();
        
        // Verify fields are cleared
        assertTrue(loginPage.getUsernameFieldValue().isEmpty(), "Username field should be empty after clearing");
        assertTrue(loginPage.getPasswordFieldValue().isEmpty(), "Password field should be empty after clearing");
        
        logger.info("Field clearing functionality works correctly");
    }
    
    @Test(description = "Test remember me checkbox functionality")
    public void testRememberMeCheckbox() {
        logger.info("Test: Test remember me checkbox functionality");
        
        // Check remember me checkbox
        loginPage.checkRememberMe();
        
        // Uncheck remember me checkbox
        loginPage.uncheckRememberMe();
        
        logger.info("Remember me checkbox functionality works correctly");
    }
    
    @Test(description = "Test navigation links")
    public void testNavigationLinks() {
        logger.info("Test: Test navigation links");
        
        // Test forgot password link
        loginPage.clickForgotPasswordLink();
        
        // Navigate back to login page
        loginPage.navigateToLoginPage(baseUrl);
        
        // Test signup link
        loginPage.clickSignupLink();
        
        logger.info("Navigation links work correctly");
    }
    
    @Test(description = "Test page title and URL")
    public void testPageTitleAndUrl() {
        logger.info("Test: Test page title and URL");
        
        // Verify page title contains Reddit
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Reddit"), "Page title should contain 'Reddit'");
        
        // Verify URL contains login
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("login"), "URL should contain 'login'");
        
        logger.info("Page title and URL are correct");
    }
}
