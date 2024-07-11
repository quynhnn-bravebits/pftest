package org.pftest.projects.V2.pages.pageListing;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.projects.V2.pages.BaseListingScreen;

import static org.pftest.keywords.WebUI.verifyElementVisible;

public class PageListing extends BaseListingScreen {

    private final By createFromTemplateButton = new ByChained(actionMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_TEMPLATE_BUTTON + "']"));
    private final By createFromBlankButton = new ByChained(primaryMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_BLANK_BUTTON + "']"));

    @Override
    @Step("Verify page listing page is loaded")
    public void verifyPageLoaded() {
        verifyPageTitle(PagesConstants.PAGE_LISTING_PAGE_TITLE);
        verifyElementVisible(importButton);
        verifyElementVisible(exportButton);
        verifyElementVisible(createFromTemplateButton);
        verifyElementVisible(createFromBlankButton);
        verifyElementVisible(indexTable);
    }
}
