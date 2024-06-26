package org.pftest.base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.projects.pages.CommonPage;
import org.testng.ITestResult;
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

    @AfterMethod
    public void attachScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            attachScreenshotPNG();
        }
    }


    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    public void resetDontRemind() {
        getJsExecutor().executeScript("window.localStorage.removeItem('no-auto-save');");
        getJsExecutor().executeScript("window.localStorage.removeItem('warning_saved');");
        getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_home');");
        getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_product');");
        getJsExecutor().executeScript("window.localStorage.removeItem('warning_publish_collection');");
        System.out.println("Clear local storage");
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public byte[] attachScreenshotPNG() {
        try {
            WebDriver driver = DriverManager.getDriver();
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            return screenshotBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
