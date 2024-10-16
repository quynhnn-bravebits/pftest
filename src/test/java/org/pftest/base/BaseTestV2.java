package org.pftest.base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.projects.V2.pages.Bridge;
import org.pftest.report.AllureManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class BaseTestV2 extends Bridge {

    @Parameters("BROWSER")
    @BeforeTest
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    @Step("{description}")
    public void addStep(String description, Runnable step) {
        step.run();
        AllureManager.takeScreenshotStep();
    }
}
