package com.reddit.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG test runner for Reddit home page tests
 */
@CucumberOptions(
        features = "src/test/resources/features/reddit_home.feature",
        glue = "com.reddit.automation.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/home-html",
                "json:target/cucumber-reports/home-cucumber.json",
                "junit:target/cucumber-reports/home-cucumber.xml"
        },
        tags = "@home",
        monochrome = true,
        dryRun = false
)
public class RedditHomeTestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
