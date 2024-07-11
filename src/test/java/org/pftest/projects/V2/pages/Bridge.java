package org.pftest.projects.V2.pages;

import org.openqa.selenium.By;
import org.pftest.constants.UrlConstants;
import org.pftest.projects.V2.pages.pageListing.PageListing;

import static org.pftest.keywords.WebUI.*;

public class Bridge {
    protected PageListing pageListing;


    public PageListing getPageListing() {
        if (pageListing == null) {
            pageListing = new PageListing();
        }
        return pageListing;
    }


    /**
     * Remove the padding of the AppNavigator to make the page listing page full width
     * <br><br>
     * note: This padding value make the Actions class work incorrectly
     */
    private static void removeAppNavigatorPadding() {
        getJsExecutor().executeScript("arguments[0].style.paddingLeft='0px';", getWebElement(By.id("AppFrameMain")));
    }

    public static void openPageListingPage() {
        openWebsite(UrlConstants.PF_PAGES_URL);
        waitForElementVisible(By.id("AppFrameMain"));
        removeAppNavigatorPadding();
        switchToPageFlyFrame();
    }


}
