package org.pftest.projects.V2.pages.extraFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.projects.V2.pages.BaseListingScreen;

import static org.pftest.keywords.WebUI.verifyElementVisible;
import static org.pftest.keywords.WebUI.verifyPageTitle;

public class TrashScreen extends BaseListingScreen {
    private final By emptyTrashButton = By.xpath("//button[@id='page-empty-trash-btn'][not(ancestor::div[@class='Polaris-ActionMenu-Actions__ActionsLayoutMeasurer'])]");
    private final By bulkActionsRestoreButton = new ByChained(indexTable, By.xpath("//button[.//*[text()='Restore'] and not(@disabled)]"));
    private final By bulkActionsDeleteForeverButton = new ByChained(indexTable, By.xpath("//button[.//*[text()='Delete forever'] and not(@disabled)]"));

    @Override
    public void verifyPageLoaded() {
        verifyPageTitle(PagesConstants.TRASH_PAGE_TITLE);
        verifyElementVisible(emptyTrashButton);
        verifyElementVisible(indexTable);
    }
}
