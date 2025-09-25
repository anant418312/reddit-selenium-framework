package com.reddit.automation.stepdefinitions;

import com.reddit.automation.framework.base.DriverManager;
import com.reddit.automation.framework.pages.RedditHomePage;
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
 * Step definitions for Reddit home page functionality
 */
public class RedditHomeStepDefinitions {
    
    private static final Logger logger = LoggerFactory.getLogger(RedditHomeStepDefinitions.class);
    
    private WebDriver driver;
    private RedditHomePage homePage;
    private String baseUrl;
    
    @Given("I am on the Reddit home page")
    public void i_am_on_the_reddit_home_page() {
        logger.info("Step: I am on the Reddit home page");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        homePage = new RedditHomePage(driver);
        homePage.navigateToHomePage(baseUrl);
    }
    
    @Given("I am logged in to Reddit")
    public void i_am_logged_in_to_reddit() {
        logger.info("Step: I am logged in to Reddit");
        baseUrl = ConfigReader.getBaseUrl();
        driver = DriverManager.initializeDriver(ConfigReader.getBrowser());
        homePage = new RedditHomePage(driver);
        homePage.navigateToHomePage(baseUrl);
        // Note: In a real scenario, you would perform actual login here
        // For now, we'll assume the user is already logged in
    }
    
    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        logger.info("Step: I search for {}", searchTerm);
        homePage.searchFor(searchTerm);
    }
    
    @When("I click on the signup button")
    public void i_click_on_the_signup_button() {
        logger.info("Step: I click on the signup button");
        homePage.clickSignupButton();
    }
    
    @When("I logout from Reddit")
    public void i_logout_from_reddit() {
        logger.info("Step: I logout from Reddit");
        homePage.logout();
    }
    
    @When("I sort posts by Hot")
    public void i_sort_posts_by_hot() {
        logger.info("Step: I sort posts by Hot");
        homePage.sortByHot();
    }
    
    @When("I sort posts by New")
    public void i_sort_posts_by_new() {
        logger.info("Step: I sort posts by New");
        homePage.sortByNew();
    }
    
    @When("I sort posts by Top")
    public void i_sort_posts_by_top() {
        logger.info("Step: I sort posts by Top");
        homePage.sortByTop();
    }
    
    @Then("I should see the Reddit home page")
    public void i_should_see_the_reddit_home_page() {
        logger.info("Step: I should see the Reddit home page");
        Assert.assertTrue(homePage.getPageTitle().contains("Reddit"), "Page title should contain 'Reddit'");
    }
    
    @Then("I should see the search box")
    public void i_should_see_the_search_box() {
        logger.info("Step: I should see the search box");
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Search box should be displayed");
    }
    
    @Then("I should see the signup button")
    public void i_should_see_the_signup_button() {
        logger.info("Step: I should see the signup button");
        Assert.assertTrue(homePage.isSignupButtonDisplayed(), "Signup button should be displayed");
    }
    
    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        logger.info("Step: I should be logged in");
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
    }
    
    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {
        logger.info("Step: I should not be logged in");
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in");
    }
    
    @Then("I should see posts on the page")
    public void i_should_see_posts_on_the_page() {
        logger.info("Step: I should see posts on the page");
        int postCount = homePage.getNumberOfPosts();
        Assert.assertTrue(postCount > 0, "Should see posts on the page, but found: " + postCount);
    }
    
    @Then("I should see at least {int} posts")
    public void i_should_see_at_least_posts(Integer minPosts) {
        logger.info("Step: I should see at least {} posts", minPosts);
        int postCount = homePage.getNumberOfPosts();
        Assert.assertTrue(postCount >= minPosts, 
                "Should see at least " + minPosts + " posts, but found: " + postCount);
    }
    
    @Then("I should see subreddit links")
    public void i_should_see_subreddit_links() {
        logger.info("Step: I should see subreddit links");
        int linkCount = homePage.getNumberOfSubredditLinks();
        Assert.assertTrue(linkCount > 0, "Should see subreddit links, but found: " + linkCount);
    }
    
    @Then("I should see at least {int} subreddit links")
    public void i_should_see_at_least_subreddit_links(Integer minLinks) {
        logger.info("Step: I should see at least {} subreddit links", minLinks);
        int linkCount = homePage.getNumberOfSubredditLinks();
        Assert.assertTrue(linkCount >= minLinks, 
                "Should see at least " + minLinks + " subreddit links, but found: " + linkCount);
    }
    
    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        logger.info("Step: the page title should contain {}", expectedTitle);
        String actualTitle = homePage.getPageTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), 
                "Page title should contain '" + expectedTitle + "', but was: " + actualTitle);
    }
    
    @Then("the current URL should contain {string}")
    public void the_current_url_should_contain(String expectedUrl) {
        logger.info("Step: the current URL should contain {}", expectedUrl);
        String actualUrl = homePage.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl), 
                "URL should contain '" + expectedUrl + "', but was: " + actualUrl);
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
    
    @And("I am not logged in")
    public void i_am_not_logged_in() {
        logger.info("Step: I am not logged in");
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in");
    }
}
