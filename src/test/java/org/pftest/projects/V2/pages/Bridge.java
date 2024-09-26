package org.pftest.projects.V2.pages;

import org.openqa.selenium.By;
import org.pftest.constants.UrlConstants;
import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.pages.editor.CommonPageEditor;
import org.pftest.projects.V2.pages.editor.ProductPageEditor;
import org.pftest.projects.V2.pages.listing.PageListing;

import static org.pftest.keywords.WebUI.*;

public class Bridge {
    protected PageListing pageListing;
    protected CommonPageEditor commonPageEditor;
    protected ProductPageEditor productPageEditor;

    public CommonPageEditor getCommonPageEditor() {
        if (commonPageEditor == null) {
            commonPageEditor = new CommonPageEditor();
        }
        return commonPageEditor;
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

    public static void openEditor() {
        openWebsite(UrlConstants.PF_EDITOR_URL(PageType.PAGE, "fd7d8306-f286-4d11-9e4d-0e357b319e72"));

    }

    public static void openTrashPage() {
        openWebsite(UrlConstants.PF_TRASH_URL);
        waitForElementVisible(By.id("AppFrameMain"));
        removeAppNavigatorPadding();
        switchToPageFlyFrame();
    }

}
