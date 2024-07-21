package org.pftest.api.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.projects.V2.pages.Bridge;
import org.testng.annotations.*;

import static org.pftest.keywords.WebUI.openWebsite;
import static org.pftest.keywords.WebUI.switchToPageFlyFrame;

public class BaseAPITest extends Bridge {
    protected final String baseUrl = "https://apps.pagefly.io/api";
    protected final String shop = "quynhquynhiee.myshopify.com";

    @Parameters("BROWSER")
    @BeforeSuite
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }
    @BeforeSuite
    public void openPageFly() {
        openWebsite("https://admin.shopify.com/store/quynhquynhiee/apps/pagefly");
        switchToPageFlyFrame();
    }

    @AfterSuite(alwaysRun = true)
    public void closePageFly() {
        // close browser
        DriverManager.quit();
    }
}
