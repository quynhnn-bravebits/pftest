package org.pftest.projects.V2.testcases;


import org.pftest.base.BaseTestV2;
import org.pftest.keywords.WebUI;
import org.testng.annotations.Test;

public class PageListingTest extends BaseTestV2 {

    @Test
    public void test() {
        // Test code here
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().openPageInPageListing(1);
        WebUI.sleep(1);
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
    }
}
