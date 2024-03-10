Hello!

For the purpose of this assignment, I implemented **Cucumber BDD** (Behaviour driven development) framework.
For the first task, I used **Maven** as build automation tool, **Selenium** to automate the browser, **JUnit** for assertions and **Java** as a programming language.
Test cases are written in resources/feature files, using Gherkin syntax. Each scenario can be marked with different annotaion and can be run separately, depending on the need.

Tests are run from CucumberRunner class. In case we want to run every test case from every feature file, we don't have to specify any tags. 
However, if we want to run specific scenario, we can label it in feature file, above scenario keyword (e.g. @regression). If we provide tag ="@regression" in CucumberRunner class, only tests marked with @regression tag will be ran.

Setting of a WebDriver is done in Driver class. I implemented Singleton design pattern which enables me to have one running instance of the driver.

To run test from command line, we can use following syntax: mvn clean test -D"cucumber.filter.tags=@{nameOfTag}". In case we want switch browser type from command line, we can use following syntax:
mvn test -D"cucumber.filter.tags=@{nameOfTag}" -DBROWSER='firefox'

Creating screenshot after failed scenario is implemented in Hooks class, tearDown method.

Most important test data is stored in configuration.properties file.

For the 2nd task, I used **RestAssured** library to simulate api requests, **jackson** library for serialization and de-serialization, and **hamcrest matchers** for assertions.
Test cases are written in addingUpdatingRemovingUsers.feature file. Similarliy to first task, scenarios can be labeled with different annotations, and can be run from CucumberRunner class.

Also, I used javafaker library to generate random testing data.

