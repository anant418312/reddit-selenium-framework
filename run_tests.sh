#!/bin/bash

echo "Running Reddit Selenium Automation Framework Tests"
echo "================================================"

echo ""
echo "1. Running Maven clean install..."
mvn clean install

echo ""
echo "2. Running all tests..."
mvn test

echo ""
echo "3. Running login tests only..."
mvn test -Dtest=RedditLoginTestRunner

echo ""
echo "4. Running smoke tests only..."
mvn test -Dcucumber.filter.tags="@smoke"

echo ""
echo "Test execution completed!"
echo "Check target/cucumber-reports/ for detailed reports."
