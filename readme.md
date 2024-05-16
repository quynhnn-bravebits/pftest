# Project Setup and Execution Guide

This is simple guide to run this project. Hope you enjoy :)

## Prerequisites
- Ensure that you have Maven installed on your system. If not, you can download it from [here](https://maven.apache.org/download.cgi) and follow the installation instructions.

## Steps to Run the Project

1. **Install Maven Dependencies**: Open your terminal and navigate to the project directory. Run the following command to install the necessary dependencies:

```bash
mvn clean install
```

2. **Select Allure Results Data Directory**: Open the `src/test/resources/allure.properties` file and set the path for the Allure results data directory.

3. **Run Tests**: Navigate to the `testng.xml` file in your project directory. Right-click on the file and select the `Run` option. Wait for the tests to execute.

4. **View Test Report**: After the tests have run, you can generate the Allure report by running the following command in your terminal:

```bash
allure serve <allure-results-directory-path>
```
Replace `<allure-results-directory-path>` with the path to your Allure results directory. For example, if your Allure results directory is named `allure-results`, the command would be:

```bash
allure serve allure-results
```

This will generate the Allure report and automatically open it in your default web browser.
```

Please replace `<allure-results-directory-path>` with the actual path to your Allure results directory.