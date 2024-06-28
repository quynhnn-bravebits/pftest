package org.pftest.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.pftest.driver.DriverManager;
import org.pftest.utils.LogUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
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

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //Allure report screenshot file and log
        LogUtils.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        LogUtils.error(iTestResult.getThrowable());

        attachScreenshotPNG();
    }
}
