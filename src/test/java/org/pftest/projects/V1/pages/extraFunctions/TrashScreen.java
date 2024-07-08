package org.pftest.projects.V1.pages.extraFunctions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.constants.UrlConstants;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V1.pages.pages.PageListingScreen;

import static org.pftest.keywords.WebUI.verifyElementVisible;


// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/rc-pagefly-1/extra-functions/trash
public class TrashScreen extends PageListingScreen {
    private final By emptyTrashButton = By.id("page-empty-trash-btn");
    private final By bulkActionsRestoreButton = new ByChained(dataTable, By.xpath("//button[.//*[text()='Restore'] and not(@disabled)]"));
    private final By bulkActionsDeleteForeverButton = new ByChained(dataTable, By.xpath("//button[.//*[text()='Delete forever'] and not(@disabled)]"));


    @Step("Open Trash page")
    public void openTrashPage() {
        WebUI.openWebsite(UrlConstants.PF_TRASH_URL);
        WebUI.switchToPageFlyFrame();
    }

    @Step("Verify Trash page loaded")
    public void verifyTrashPageLoaded() {
        verifyPageTitle(PagesConstants.TRASH_PAGE_TITLE);
        verifyElementVisible(emptyTrashButton);
        verifyElementVisible(dataTable);

    }
}