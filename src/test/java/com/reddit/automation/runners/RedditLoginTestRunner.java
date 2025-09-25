package com.reddit.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG test runner for Reddit login tests
 */
@CucumberOptions(
        features = "src/test/resources/features/reddit_login.feature",
        glue = "com.reddit.automation.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/login-html",
                "json:target/cucumber-reports/login-cucumber.json",
                "junit:target/cucumber-reports/login-cucumber.xml"
        },
        tags = "@login",
        monochrome = true,
        dryRun = false
)
public class RedditLoginTestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
