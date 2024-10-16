package org.pftest.projects.V2.testcases;


import org.pftest.base.BaseTestV2;
import org.pftest.enums.pagefly.EditorType;
import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.PageStatus;
import org.pftest.enums.pagefly.PageType;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.drawer.DrawerManager;
import org.pftest.projects.V2.pages.editor.BaseEditor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.pftest.keywords.WebUI.switchToEditorFrame;

public class PageListingTest extends BaseTestV2 {

    public void createBlankPageFromPageListing(EditorType editorType, PageType pageType, String pageTitle) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();

        if (editorType == EditorType.FLEX) {
            getPageListing().createFlexLayoutFromBlank(pageType);
        } else {
            getPageListing().createLegacyLayoutFromBlank(pageType);
        }

        switchToEditorFrame();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).open();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).selectTemplate();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).confirmSelectTemplatePopover();

        BaseEditor editor = getPageEditor(pageType);
        editor.changePageTitle(pageTitle+ " - " + pageType +  " - " + new Date().toString());
        editor.save();
        editor.publish();
    }

    private void createBlankPageFlexLayoutFromPageListing(PageType pageType) {
        createBlankPageFromPageListing(EditorType.FLEX, pageType, "TC-010: Blank Page Flex Layout ");
    }

    private void createBlankPageLegacyLayoutFromPageListing(PageType pageType) {
       createBlankPageFromPageListing(EditorType.LEGACY, pageType, "TC-010: Blank Page Legacy Layout");
    }

    @Test(description = "TC-010: User create Blank page (Flex layout) from Page Listing", dataProvider = "pageTypes", dataProviderClass = DataProviderFactory.class)
    public void testCreateBlankPageFlexLayoutFromPageListing(PageType pageType) {
        createBlankPageFlexLayoutFromPageListing(pageType);
    }

    @Test(description = "TC-010: User create Blank page (Legacy layout) from Page Listing", dataProvider = "pageTypes", dataProviderClass = DataProviderFactory.class)
    public void testCreateBlankPageLegacyLayoutFromPageListing(PageType pageType) {
        createBlankPageLegacyLayoutFromPageListing(pageType);
    }

    public void createTemplatePageFromPageListing(EditorType editorType, PageType pageType, String pageTitle) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        if (editorType == EditorType.FLEX) {
            getPageListing().createFlexLayoutFromTemplate(pageType);
        } else {
            getPageListing().createLegacyLayoutFromTemplate(pageType);
        }

        BaseEditor editor = getPageEditor(pageType);
        editor.changePageTitle(pageTitle+ " - " + pageType +  " - " + new Date().toString());
        editor.save();
        editor.publish();
    }

    private void createTemplatePageFlexLayoutFromPageListing(PageType pageType) {
        createTemplatePageFromPageListing(EditorType.FLEX, pageType, "TC-011: Template Page Flex Layout ");
    }

    private void createTemplatePageLegacyLayoutFromPageListing(PageType pageType) {
        createTemplatePageFromPageListing(EditorType.LEGACY, pageType, "TC-011: Template Page Legacy Layout");
    }


    @Test(description = "TC-011: User create Template page (Flex layout) from Page Listing", dataProvider = "pageTypes", dataProviderClass = DataProviderFactory.class)
    public void testCreateTemplatePageFlexLayoutFromPageListing(PageType pageType) {
        createTemplatePageFlexLayoutFromPageListing(pageType);
    }

    @Test(description = "TC-011: User create Template page (Legacy layout) from Page Listing", dataProvider = "pageTypes", dataProviderClass = DataProviderFactory.class)
    public void testCreateTemplatePageLegacyLayoutFromPageListing(PageType pageType) {
        createTemplatePageLegacyLayoutFromPageListing(pageType);
    }

    @Test(description = "TC-012: User publish page in the Page listing screen")
    public void publishPageInThePageListingScreen() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().filterByStatus(PageStatus.UNPUBLISHED);
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.waitForElementVisible(getPageListing().getRowByIndex(1));
        getPageListing().selectRowByIndex(1);
        getPageListing().publishAllSelectedPages();
    }

    @Test(description = "TC-013: User unpublish page in the Page listing screen")
    public void unpublishPageInThePageListingScreen() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().filterByStatus(PageStatus.PUBLISHED);
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.waitForElementVisible(getPageListing().getRowByIndex(1));
        getPageListing().selectRowByIndex(1);
        getPageListing().unpublishAllSelectedPages();
    }

    @Test(description = "TC-015: User delete pages that are created in PageFly")
    public void deletePageInThePageListingScreen() {

        openPageListingPage();
        getPageListing().verifyPageLoaded();
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.waitForElementVisible(getPageListing().getRowByIndex(1));
        getPageListing().selectRowByIndex(1);
        getPageListing().deleteAllSelectedPages(1);
    }

}
