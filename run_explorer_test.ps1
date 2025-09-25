Write-Host "Running Reddit Shadow DOM Explorer Test..." -ForegroundColor Green
mvn test -Dtest=RedditShadowDOMExplorerTest -Dbrowser=chrome -Dheadless=false
Read-Host "Press Enter to continue"
