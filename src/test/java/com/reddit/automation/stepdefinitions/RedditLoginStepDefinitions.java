package com.reddit.automation.stepdefinitions;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.pages.RedditHomePage;
import com.reddit.automation.framework.pages.RedditLoginPage;
import com.reddit.automation.framework.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Step definitions for Reddit login functionality
 */
public class RedditLoginStepDefinitions {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditLoginStepDefinitions.class);
    
    private WebDriver driver;
    private RedditHomePage homePage;
    private RedditLoginPage loginPage;
    private String baseUrl;
    
    @Given("I am on the Reddit home page")
    public void i_am_on_the_reddit_home_page() {
        logger.info("Step: I am on the Reddit home page");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        homePage = new RedditHomePage(driver);
        homePage.navigateToHomePage(baseUrl);
    }
    
    @Given("I navigate to the Reddit login page")
    public void i_navigate_to_the_reddit_login_page() {
        logger.info("Step: I navigate to the Reddit login page");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        loginPage = new RedditLoginPage(driver);
        loginPage.navigateToLoginPage(baseUrl);
    }
    
    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        logger.info("Step: I click on the login button");
        homePage.clickLoginButton();
    }
    
    @When("I enter username {string}")
    public void i_enter_username(String username) {
        logger.info("Step: I enter username {}", username);
        loginPage.enterUsername(username);
    }
    
    @When("I enter password {string}")
    public void i_enter_password(String password) {
        logger.info("Step: I enter password");
        loginPage.enterPassword(password);
    }
    
    @When("I click the login button")
    public void i_click_the_login_button() {
        logger.info("Step: I click the login button");
        loginPage.clickLoginButton();
    }
    
    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        logger.info("Step: I login with username {} and password", username);
        loginPage.login(username, password);
    }
    
    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        logger.info("Step: I login with valid credentials");
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        loginPage.login(username, password);
    }
    
    @When("I login with invalid credentials")
    public void i_login_with_invalid_credentials() {
        logger.info("Step: I login with invalid credentials");
        loginPage.login("invaliduser", "invalidpass");
    }
    
    @When("I clear the username field")
    public void i_clear_the_username_field() {
        logger.info("Step: I clear the username field");
        loginPage.clearUsernameField();
    }
    
    @When("I clear the password field")
    public void i_clear_the_password_field() {
        logger.info("Step: I clear the password field");
        loginPage.clearPasswordField();
    }
    
    @When("I check the remember me checkbox")
    public void i_check_the_remember_me_checkbox() {
        logger.info("Step: I check the remember me checkbox");
        loginPage.checkRememberMe();
    }
    
    @When("I uncheck the remember me checkbox")
    public void i_uncheck_the_remember_me_checkbox() {
        logger.info("Step: I uncheck the remember me checkbox");
        loginPage.uncheckRememberMe();
    }
    
    @When("I click the forgot password link")
    public void i_click_the_forgot_password_link() {
        logger.info("Step: I click the forgot password link");
        loginPage.clickForgotPasswordLink();
    }
    
    @When("I click the signup link")
    public void i_click_the_signup_link() {
        logger.info("Step: I click the signup link");
        loginPage.clickSignupLink();
    }
    
    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        logger.info("Step: I should be redirected to the login page");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
    }
    
    @Then("I should see the login form")
    public void i_should_see_the_login_form() {
        logger.info("Step: I should see the login form");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
    }
    
    @Then("I should see the username field")
    public void i_should_see_the_username_field() {
        logger.info("Step: I should see the username field");
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be displayed");
    }
    
    @Then("I should see the password field")
    public void i_should_see_the_password_field() {
        logger.info("Step: I should see the password field");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
    }
    
    @Then("I should see the login button")
    public void i_should_see_the_login_button() {
        logger.info("Step: I should see the login button");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
    }
    
    @Then("I should be successfully logged in")
    public void i_should_be_successfully_logged_in() {
        logger.info("Step: I should be successfully logged in");
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
    }
    
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        logger.info("Step: I should see an error message");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }
    
    @Then("I should see an error message containing {string}")
    public void i_should_see_an_error_message_containing(String expectedMessage) {
        logger.info("Step: I should see an error message containing {}", expectedMessage);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
                "Error message should contain: " + expectedMessage + ", but was: " + actualMessage);
    }
    
    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {
        logger.info("Step: I should not be logged in");
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in");
    }
    
    @Then("the username field should be empty")
    public void the_username_field_should_be_empty() {
        logger.info("Step: the username field should be empty");
        String usernameValue = loginPage.getUsernameFieldValue();
        Assert.assertTrue(usernameValue.isEmpty(), "Username field should be empty, but was: " + usernameValue);
    }
    
    @Then("the password field should be empty")
    public void the_password_field_should_be_empty() {
        logger.info("Step: the password field should be empty");
        String passwordValue = loginPage.getPasswordFieldValue();
        Assert.assertTrue(passwordValue.isEmpty(), "Password field should be empty, but was: " + passwordValue);
    }
    
    @Then("the username field should contain {string}")
    public void the_username_field_should_contain(String expectedValue) {
        logger.info("Step: the username field should contain {}", expectedValue);
        String actualValue = loginPage.getUsernameFieldValue();
        Assert.assertEquals(actualValue, expectedValue, "Username field should contain: " + expectedValue);
    }
    
    @Then("the password field should contain {string}")
    public void the_password_field_should_contain(String expectedValue) {
        logger.info("Step: the password field should contain {}", expectedValue);
        String actualValue = loginPage.getPasswordFieldValue();
        Assert.assertEquals(actualValue, expectedValue, "Password field should contain: " + expectedValue);
    }
    
    @Then("the username field should be enabled")
    public void the_username_field_should_be_enabled() {
        logger.info("Step: the username field should be enabled");
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field should be enabled");
    }
    
    @Then("the password field should be enabled")
    public void the_password_field_should_be_enabled() {
        logger.info("Step: the password field should be enabled");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field should be enabled");
    }
    
    @And("I am not logged in")
    public void i_am_not_logged_in() {
        logger.info("Step: I am not logged in");
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in");
    }
    
    @And("I wait for the page to load")
    public void i_wait_for_the_page_to_load() {
        logger.info("Step: I wait for the page to load");
        try {
            Thread.sleep(2000); // Wait for page to load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
