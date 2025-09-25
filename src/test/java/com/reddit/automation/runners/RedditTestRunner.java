package com.reddit.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Main TestNG test runner for Reddit automation tests
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.reddit.automation.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        tags = "@reddit or @home or @smoke",
        monochrome = true,
        dryRun = false
)
public class RedditTestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
