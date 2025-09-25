# Reddit Selenium Automation Framework

A scalable Selenium WebDriver Java framework based on Page Object Model (POM) and Behavior-Driven Development (BDD) for testing Reddit pages.

## Framework Features

- **Page Object Model (POM)**: Organized page classes for better maintainability
- **BDD with Cucumber**: Feature files and step definitions for readable test scenarios
- **Multi-browser Support**: Chrome, Firefox, Edge, and Safari
- **WebDriverManager**: Automatic driver management
- **TestNG Integration**: Parallel test execution and reporting
- **Extent Reports**: Detailed test reporting
- **Configuration Management**: Centralized configuration
- **Logging**: Comprehensive logging with SLF4J and Logback

## Project Structure

```
reddit-selenium-framework/
├── src/
│   ├── main/java/com/reddit/automation/framework/
│   │   ├── base/
│   │   │   ├── BasePage.java
│   │   │   └── DriverManager.java
│   │   ├── pages/
│   │   │   ├── RedditHomePage.java
│   │   │   ├── RedditLoginPage.java
│   │   │   └── RedditSignupPage.java
│   │   └── utils/
│   │       └── ConfigReader.java
│   └── test/
│       ├── java/com/reddit/automation/
│       │   ├── stepdefinitions/
│       │   │   ├── RedditLoginStepDefinitions.java
│       │   │   └── RedditHomeStepDefinitions.java
│       │   ├── runners/
│       │   │   ├── RedditTestRunner.java
│       │   │   └── RedditLoginTestRunner.java
│       │   └── RedditLoginPageValidationTest.java
│       └── resources/
│           ├── features/
│           │   ├── reddit_login.feature
│           │   └── reddit_home.feature
│           └── config.properties
├── pom.xml
├── testng.xml
└── README.md
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome, Firefox, Edge, or Safari browser installed

## Setup Instructions

1. **Navigate to the project directory**:
   ```bash
   cd "C:\Projects\Cursor projects\reddit-selenium-framework"
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

3. **Configure browser settings** in `src/test/resources/config.properties`:
   ```properties
   browser=chrome
   headless=false
   base.url=https://www.reddit.com
   ```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suites
```bash
# Run login tests only
mvn test -Dtest=RedditLoginTestRunner

# Run home page tests only
mvn test -Dtest=RedditTestRunner
```

### Run with TestNG
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run with Specific Tags
```bash
# Run smoke tests only
mvn test -Dcucumber.filter.tags="@smoke"

# Run login tests only
mvn test -Dcucumber.filter.tags="@login"
```

### Run in Headless Mode
```bash
mvn test -Dheadless=true
```

### Run with Different Browser
```bash
mvn test -Dbrowser=firefox
```

## Test Execution Examples

### 1. BDD Tests (Cucumber)
The framework includes comprehensive BDD tests for Reddit login and home page functionality:

- **Login Page Tests**: Validate login form elements, error handling, and user interactions
- **Home Page Tests**: Verify page elements, navigation, and search functionality

### 2. Traditional TestNG Tests
Sample test class `RedditLoginPageValidationTest` demonstrates:
- Login page element validation
- Invalid credential handling
- Field clearing functionality

## Configuration Options

### Browser Configuration
```properties
# Supported browsers: chrome, firefox, edge, safari
browser=chrome
headless=false
```

### Timeout Configuration
```properties
timeout=10
page.load.timeout=30
implicit.wait=10
```

### URL Configuration
```properties
base.url=https://www.reddit.com
```

## Framework Components

### 1. Base Classes
- **BasePage**: Common functionality for all page objects
- **DriverManager**: WebDriver initialization and management

### 2. Page Objects
- **RedditHomePage**: Home page interactions
- **RedditLoginPage**: Login page interactions
- **RedditSignupPage**: Signup page interactions

### 3. Step Definitions
- **RedditLoginStepDefinitions**: Login-related BDD steps
- **RedditHomeStepDefinitions**: Home page BDD steps

### 4. Test Runners
- **RedditTestRunner**: Main test runner
- **RedditLoginTestRunner**: Login-specific test runner

## Adding New Tests

### 1. Create New Page Object
```java
public class NewPage extends BasePage {
    // Page elements and methods
}
```

### 2. Create Step Definitions
```java
public class NewStepDefinitions {
    // BDD step definitions
}
```

### 3. Create Feature File
```gherkin
Feature: New Feature
  Scenario: Test scenario
    Given some condition
    When some action
    Then some result
```

## Best Practices

1. **Page Object Model**: Keep page objects focused on single pages
2. **Wait Strategies**: Use explicit waits for better reliability
3. **Configuration**: Use properties files for environment-specific settings
4. **Logging**: Add comprehensive logging for debugging
5. **Error Handling**: Implement proper exception handling
6. **Test Data**: Use external data sources for test data
7. **Parallel Execution**: Configure parallel execution for faster test runs

## Troubleshooting

### Common Issues

1. **WebDriver Issues**: Ensure browser drivers are properly installed
2. **Element Not Found**: Check if locators are correct and elements are loaded
3. **Timeout Issues**: Adjust timeout values in configuration
4. **Browser Compatibility**: Test with different browsers

### Debug Mode
Run tests with debug logging:
```bash
mvn test -Dlogging.level.com.reddit.automation=DEBUG
```

## Reports

Test reports are generated in:
- **Cucumber Reports**: `target/cucumber-reports/`
- **TestNG Reports**: `target/surefire-reports/`
- **Extent Reports**: `target/reports/`

## Contributing

1. Follow the existing code structure
2. Add proper logging and documentation
3. Write comprehensive tests
4. Follow naming conventions
4. Update README for new features

## License

This project is licensed under the MIT License.
