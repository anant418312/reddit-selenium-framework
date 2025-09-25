Write-Host "Running Reddit Simple Input Test..." -ForegroundColor Green
mvn test -Dtest=RedditSimpleInputTest -Dbrowser=chrome -Dheadless=false
Read-Host "Press Enter to continue"
