@echo off
echo Running Reddit Basic Test...
mvn test -Dtest=RedditBasicTest -Dsurefire.suiteXmlFiles=
pause
