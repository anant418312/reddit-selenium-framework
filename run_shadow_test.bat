@echo off
echo Running Reddit Shadow DOM Test...
mvn test -Dtest=RedditShadowDOMTest -Dbrowser=chrome -Dheadless=false
pause
