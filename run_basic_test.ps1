Write-Host "Running Reddit Basic Test..." -ForegroundColor Green
mvn test -Dtest=RedditBasicTest -Dsurefire.suiteXmlFiles=
Read-Host "Press Enter to continue"
