package org.pftest.projects.V2.pages;

import org.openqa.selenium.By;
import org.pftest.constants.UrlConstants;
import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.pages.editor.*;
import org.pftest.projects.V2.pages.extraFunctions.TrashScreen;
import org.pftest.projects.V2.pages.listing.PageListing;

import static org.pftest.keywords.WebUI.*;

public class Bridge {
    private PageListing pageListing;
    private BaseEditor basePageEditor;
    private ProductPageEditor productPageEditor;
    private CollectionPageEditor collectionPageEditor;
    private BaseEditor pageEditor;
    private TrashScreen trashScreen;

    public BaseEditor getBasePageEditor() {
        if (basePageEditor == null) {
            basePageEditor = new BaseEditor();
        }
        return basePageEditor;
    }

    public ProductPageEditor getProductPageEditor() {
        if (productPageEditor == null) {
            productPageEditor = new ProductPageEditor();
        }
        return productPageEditor;
    }

    public CollectionPageEditor getCollectionPageEditor() {
        if (collectionPageEditor == null) {
            collectionPageEditor = new CollectionPageEditor();
        }
        return collectionPageEditor;
    }

    public BaseEditor getPageEditor(PageType pageType) {
        if (pageType == PageType.PRODUCT) {
            pageEditor = new ProductPageEditor();
        }
        else if (pageType == PageType.COLLECTION) {
            pageEditor = new CollectionPageEditor();
        }
        else if (pageType == PageType.BLOG) {
            pageEditor = new BlogPageEditor();
        }
        else if (pageType == PageType.HOME) {
            pageEditor = new HomePageEditor();
        }
        else {
            pageEditor = new BaseEditor();
        }

        return pageEditor;
    }

    public PageListing getPageListing() {
        if (pageListing == null) {
            pageListing = new PageListing();
        }
        return pageListing;
    }

    public TrashScreen getTrashScreen() {
        if (trashScreen == null) {
            trashScreen = new TrashScreen();
        }
        return trashScreen;
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

    public static void openTrashPage() {
        openWebsite(UrlConstants.PF_TRASH_URL);
        waitForElementVisible(By.id("AppFrameMain"));
        removeAppNavigatorPadding();
        switchToPageFlyFrame();
    }

}
