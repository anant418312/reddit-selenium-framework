@echo off
echo Running Reddit Shadow DOM Explorer Test...
mvn test -Dtest=RedditShadowDOMExplorerTest -Dbrowser=chrome -Dheadless=false
pause
