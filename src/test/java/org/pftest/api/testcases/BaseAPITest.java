package org.pftest.api.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.pftest.constants.UrlConstants;
import org.pftest.driver.DriverManager;
import org.pftest.driver.TargetFactory;
import org.pftest.listeners.GroupOrderInterceptor;
import org.pftest.projects.V2.pages.Bridge;
import org.testng.annotations.*;

import static org.pftest.keywords.WebUI.*;

@Listeners(GroupOrderInterceptor.class)
public class BaseAPITest extends Bridge {
    protected final String baseUrl = "https://rc.pagefly.io/api";
    protected final String shopName = "quynhquynhiee";
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
        openWebsite(UrlConstants.PF_BASE_URL);
        waitForPageLoaded();
        switchToPageFlyFrame();
    }

    @BeforeGroups(groups = "TRASH")
    public static void openTrashPage() {
        openWebsite(UrlConstants.PF_TRASH_URL);
        waitForPageLoaded();
        switchToPageFlyFrame();
    }

    @BeforeGroups(groups = "SECTIONS")
    public static void openSectionListingPage() {
        openWebsite(UrlConstants.PF_SECTIONS_URL);
        waitForPageLoaded();
        switchToPageFlyFrame();
    }

//    @BeforeGroups(groups = "PAGES")
//    public static void openPageListingPage() {
//        openWebsite(UrlConstants.PF_PAGES_URL);
//        switchToPageFlyFrame();
//    }

    @AfterSuite(alwaysRun = true)
    public void closePageFly() {
        // close browser
        DriverManager.quit();
    }
}
