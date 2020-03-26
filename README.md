# Google Civic API Test Scripts using Cucumber and Java


## Prerequisites

- JDK 8
- Maven 3.6.3 or higher

Installed and path set

## Running Locally

```
mvn test
```

## Jenkins Job

Hosted Jenkins Server in an AWS ec2 instance(t2.micro) to demonstrate the tests

Jenkins Job - [teds-api job](http://ec2-35-173-213-215.compute-1.amazonaws.com:8080/job/teds-api)

Also published the reports as part of build and you can view the cucumber test report in Jenkins here - [Cucumber reports](http://ec2-35-173-213-215.compute-1.amazonaws.com:8080/job/teds-api/job/master/5/cucumber-html-reports/overview-features.html)

_Note: The example url above to reports should be replaced with corresponding build number_
