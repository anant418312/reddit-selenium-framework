package com.reddit.automation.framework.pages;

import com.reddit.automation.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Reddit Home Page Object Model
 */
public class RedditHomePage extends BasePage {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditHomePage.class);
    
    // Page Elements - Using CSS selectors for better reliability
    private static final By LOGIN_BUTTON = By.cssSelector("a[href*='login'], a:contains('Log In'), a:contains('Sign In')");
    private static final By SIGNUP_BUTTON = By.cssSelector("a[href*='signup'], a[href*='register'], a:contains('Sign Up'), a:contains('Register')");
    private static final By SEARCH_BOX = By.cssSelector("input[placeholder*='Search'], input[type='search'], input[placeholder*='reddit']");
    private static final By SEARCH_BUTTON = By.cssSelector("button[type='submit'], button:contains('Search')");
    private static final By USER_MENU = By.cssSelector("[data-testid='user-dropdown'], .user-menu, .profile-menu");
    private static final By LOGOUT_BUTTON = By.cssSelector("button:contains('Log Out'), a:contains('Log Out')");
    private static final By POSTS_CONTAINER = By.cssSelector("[data-testid='post-container'], .post, .thing");
    private static final By SUBREDDIT_LINKS = By.cssSelector("a[href*='/r/']");
    private static final By SORT_DROPDOWN = By.cssSelector("[data-testid='sort-select'], select");
    private static final By HOT_SORT = By.cssSelector("option[value='hot']");
    private static final By NEW_SORT = By.cssSelector("option[value='new']");
    private static final By TOP_SORT = By.cssSelector("option[value='top']");
    
    public RedditHomePage(WebDriver driver) {
        super(driver);
        logger.info("RedditHomePage initialized");
    }
    
    /**
     * Navigate to Reddit home page
     */
    public void navigateToHomePage(String baseUrl) {
        logger.info("Navigating to Reddit home page: {}", baseUrl);
        navigateToUrl(baseUrl);
        waitForPageTitle("Reddit");
    }
    
    /**
     * Click on login button
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        clickElement(LOGIN_BUTTON);
    }
    
    /**
     * Click on signup button
     */
    public void clickSignupButton() {
        logger.info("Clicking signup button");
        clickElement(SIGNUP_BUTTON);
    }
    
    /**
     * Search for a term with Shadow DOM support
     */
    public void searchFor(String searchTerm) {
        logger.info("Searching for: {}", searchTerm);
        try {
            // Try standard approach first
            sendTextToElement(SEARCH_BOX, searchTerm);
            clickElement(SEARCH_BUTTON);
        } catch (Exception e) {
            logger.warn("Standard search approach failed, trying Shadow DOM approach", e);
            try {
                // Try JavaScript approach for Shadow DOM
                String searchScript = "document.querySelector('input[placeholder*=\"Search\"], input[type=\"search\"], input[placeholder*=\"reddit\"]')";
                sendTextByJavaScript(searchScript, searchTerm);
                
                String buttonScript = "document.querySelector('button[type=\"submit\"], input[type=\"submit\"]')";
                clickElementByJavaScript(buttonScript);
            } catch (Exception e2) {
                logger.warn("JavaScript approach failed, trying alternative selectors", e2);
                // Try alternative selectors
                By altSearchBox = By.cssSelector("input[type='text'], input[type='search']");
                By altSearchButton = By.cssSelector("button, input[type='submit']");
                sendTextToElement(altSearchBox, searchTerm);
                clickElement(altSearchButton);
            }
        }
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isUserLoggedIn() {
        logger.info("Checking if user is logged in");
        try {
            return isElementDisplayed(USER_MENU);
        } catch (Exception e) {
            logger.warn("User menu not found, user not logged in");
            return false;
        }
    }
    
    /**
     * Logout user
     */
    public void logout() {
        logger.info("Logging out user");
        if (isUserLoggedIn()) {
            clickElement(USER_MENU);
            clickElement(LOGOUT_BUTTON);
        }
    }
    
    /**
     * Get number of posts displayed
     */
    public int getNumberOfPosts() {
        logger.info("Getting number of posts");
        List<WebElement> posts = getElements(POSTS_CONTAINER);
        return posts.size();
    }
    
    /**
     * Get number of subreddit links
     */
    public int getNumberOfSubredditLinks() {
        logger.info("Getting number of subreddit links");
        List<WebElement> links = getElements(SUBREDDIT_LINKS);
        return links.size();
    }
    
    /**
     * Sort posts by Hot
     */
    public void sortByHot() {
        logger.info("Sorting posts by Hot");
        clickElement(SORT_DROPDOWN);
        clickElement(HOT_SORT);
    }
    
    /**
     * Sort posts by New
     */
    public void sortByNew() {
        logger.info("Sorting posts by New");
        clickElement(SORT_DROPDOWN);
        clickElement(NEW_SORT);
    }
    
    /**
     * Sort posts by Top
     */
    public void sortByTop() {
        logger.info("Sorting posts by Top");
        clickElement(SORT_DROPDOWN);
        clickElement(TOP_SORT);
    }
    
    /**
     * Check if search box is displayed
     */
    public boolean isSearchBoxDisplayed() {
        logger.info("Checking if search box is displayed");
        return isElementDisplayed(SEARCH_BOX);
    }
    
    /**
     * Check if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        logger.info("Checking if login button is displayed");
        return isElementDisplayed(LOGIN_BUTTON);
    }
    
    /**
     * Check if signup button is displayed
     */
    public boolean isSignupButtonDisplayed() {
        logger.info("Checking if signup button is displayed");
        return isElementDisplayed(SIGNUP_BUTTON);
    }
    
    /**
     * Get page title
     */
    public String getPageTitle() {
        return getPageTitle();
    }
    
    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return getCurrentUrl();
    }
}
