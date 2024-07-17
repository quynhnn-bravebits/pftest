package org.pftest.projects.V2.pages;

import org.openqa.selenium.By;
import org.pftest.constants.UrlConstants;
import org.pftest.projects.V2.pages.editor.CommonPageEditor;
import org.pftest.projects.V2.pages.editor.HomePageEditor;
import org.pftest.projects.V2.pages.editor.ProductPageEditor;
import org.pftest.projects.V2.pages.pageListing.PageListing;

import static org.pftest.keywords.WebUI.*;

public class Bridge {
    protected PageListing pageListing;
    protected CommonPageEditor commonPageEditor;
    protected HomePageEditor homePageEditor;
    protected ProductPageEditor productPageEditor;

    public CommonPageEditor getCommonPageEditor() {
        if (commonPageEditor == null) {
            commonPageEditor = new CommonPageEditor();
        }
        return commonPageEditor;
    }

    public HomePageEditor getHomePageEditor() {
        if (homePageEditor == null) {
            homePageEditor = new HomePageEditor();
        }
        return homePageEditor;
    }

    public ProductPageEditor getProductPageEditor() {
        if (productPageEditor == null) {
            productPageEditor = new ProductPageEditor();
        }
        return productPageEditor;
    }

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
