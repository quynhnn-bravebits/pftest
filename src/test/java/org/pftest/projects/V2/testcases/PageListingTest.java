package org.pftest.projects.V2.testcases;


import org.pftest.base.BaseTestV2;
import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.components.drawer.DrawerManager;
import org.pftest.projects.V2.pages.editor.BaseEditor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.pftest.keywords.WebUI.switchToEditorFrame;

public class PageListingTest extends BaseTestV2 {

    private void createBlankPageFlexLayoutFromPageListing(PageType pageType) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFlexLayoutFromBlank(pageType);

        switchToEditorFrame();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).open();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).selectTemplate();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).confirmSelectTemplatePopover();

        BaseEditor editor = getPageEditor(pageType);
        editor.changePageTitle(pageType + " - " + new Date().toString());
        editor.save();
        editor.publish();
    }

    @Test(description = "TC-010: User create Blank page (Flex layout) from Page Listing", dataProvider = "pageTypes", dataProviderClass = DataProviderFactory.class)
    public void testCreateBlankPageFlexLayoutFromPageListing2(PageType pageType) {
        createBlankPageFlexLayoutFromPageListing(pageType);
    }

    @Test(description = "TC-010: User create Blank page (Flex layout) from Page Listing")
    public void testCreateBlankPageFlexLayoutFromPageListing() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFlexLayoutFromBlank(PageType.PAGE);

        switchToEditorFrame();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).open();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).selectTemplate();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).confirmSelectTemplatePopover();

        getBasePageEditor().changePageTitle("TC-010: Blank Page Flex Layout");
        getBasePageEditor().save();
        getBasePageEditor().publish();
    }

    @Test(description = "TC-010: User create Blank page (Legacy layout) from Page Listing")
    public void testCreateBlankPageLegacyLayoutFromPageListing() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createLegacyLayoutFromBlank(PageType.PAGE);

        switchToEditorFrame();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).open();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).selectTemplate();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).confirmSelectTemplatePopover();

        getBasePageEditor().changePageTitle("TC-011: Blank Page Legacy Layout");
        getBasePageEditor().save();
        getBasePageEditor().publish();
    }

    @Test(description = "TC-011: User create Template page (Flex layout) from Page Listing")
    public void testCreateTemplatePageFlexLayoutFromPageListing() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFlexLayoutFromTemplate(PageType.PAGE);

        getBasePageEditor().changePageTitle("TC-011: Template Page Flex Layout");
        getBasePageEditor().save();
        getBasePageEditor().publish();
    }

    @Test(description = "TC-011: User create Template page (Legacy layout) from Page Listing")
    public void testCreateTemplatePageLegacyLayoutFromPageListing() {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createLegacyLayoutFromTemplate(PageType.PAGE);

        getBasePageEditor().changePageTitle("TC-012: Template Page Legacy Layout");
        getBasePageEditor().save();
        getBasePageEditor().publish();
    }

}
