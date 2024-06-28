package org.pftest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.projects.pages.CommonPage;
import org.testng.annotations.*;

import static org.pftest.keywords.WebUI.getJsExecutor;

public class BaseTest extends CommonPage {
    @Parameters("BROWSER")
    @BeforeMethod
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    public void resetDontRemind() {
        try {
            getJsExecutor().executeScript("window.localStorage.removeItem('no-auto-save');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_saved');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_home');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_product');");
            getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_collection');");
            System.out.println("Clear local storage");
        } catch (Exception e) {
            System.out.println("Clear local storage failed");
            e.printStackTrace();
        }
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }

}
