@echo off
echo Running Reddit Simple Input Test...
mvn test -Dtest=RedditSimpleInputTest -Dbrowser=chrome -Dheadless=false
pause
