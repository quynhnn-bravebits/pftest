package org.pftest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.projects.V2.pages.Bridge;
import org.testng.annotations.*;

import static org.pftest.keywords.WebUI.getJsExecutor;

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

    public void resetCheckedDontRemind() {
        try {
            // Don't remind warning Save modal
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_saved');");
            // Don't remind warning Publish page
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_home');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_product');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_collection');");

        } catch (Exception e) {
            System.out.println("Clear variables from localstorage failed");
            e.printStackTrace();
            }
    }

}
