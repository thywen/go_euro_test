# Java Selenium Example

This is a small example how I would implement a test framework with java. I am not happy with all parts, but setting this up might take to long for a small excercise because I still have to learn on the fly

### Requirements
- Java is installed

### Setup

- Install maven
```sh
$ brew install maven
```
- download selenium standalone server from http://www.seleniumhq.org/download/

### Run Tests
- start with java -jar selenium-server-standalone-<version>.jar
- start the tests with
```
$ mvn test
```

### Create Reports
```sh
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```

The report can be found in /target/site/surefire-report.html

### Decissions
- I didn't add Selenium to the maven which is possible, but a) it is possible to run a grid and b) it is the recommended way on GitHub
- No dependency injection has been added because this would be very time consuming and for this small tests anyways not necessary
- I decided to use a Test for each way of transportation, because I don't like combined tests
- I decided against a BDD framework, because again timing issues and as mentioned in the chat I am not a big fan of those unless it is part of the company philosophy and ANYONE uses it
- I decided against unit tests because there is nothing besides of views which are not unittestable

### Things to change
- I don't really like the way how I deal with the gathering of the prices, but with the given information and my Java knowledge I couldn't come with a different solution
- Using floats for comparing is not the way to go but again out of lacking java skills I thought for this usecase it might be the best solution
- Add Urls to page object in order to open them without the usage of plain text
- Adding support for several base urls
- Configuration via property file
- less Magic numbers in code
- get rid of the sleep (this one is bugging me but with the current setup I couldn't find a solution without an explicit wait, because it seems to be too quick with the setting the value but I couldn't find changes in the dom where I can pinpoint that the autocomplete is ready now
- I rather dislike also the way of verification, but without api/database acces it is the best I can do

### Technology and approach
* I decided for Java because both java script javascript web driver were a bit more flakey in a first discovery phase. But both should be viable and are updated regulary.
* I use maven because it is a good framework for dependency management
* I used the page object pattern, because it allows the separation between the test and the page related functions
* I use surefire because it allows to create html reports
* I wanted to use a linter but couldn't find a good one on the fly which is compatible with maven


