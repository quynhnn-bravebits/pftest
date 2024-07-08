package org.pftest.projects.V2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class BaseListingScreen {
    protected By pageTitle = By.xpath("//h1[@class='Polaris-Header-Title']");
    protected final By actionMenu = By.xpath("//div[@class='Polaris-ActionMenu-Actions__ActionsLayout']");
    private final By importButton = new ByChained(actionMenu, By.id("import-btn"));
    private final By exportButton = new ByChained(actionMenu, By.id("export-btn"));

    protected final By indexFilter = By.xpath("//div[@class='Polaris-IndexFilters']");
    protected final By searchAndFilterButton = new ByChained(indexFilter, By.xpath("//button[@aria-label='Search and filter results']"));
    protected final By addFilterButton = new ByChained(indexFilter, By.xpath("//button[@aria-label='Add filter']"));

    protected final By indexTable = By.xpath("//div[@class='Polaris-IndexTable']//table");
    protected final By selectAllPagesCheckbox = new ByChained(indexTable, By.xpath(".//thead//*[@class='Polaris-Checkbox']"));
    private final By bulkActionRoot = new ByChained(indexTable, By.xpath(".//div[@class='Polaris-BulkActions__BulkActionsLayout']"));
    protected final By publishButtonBulkAction = new ByChained(bulkActionRoot, By.xpath(".//button[@aria-label='publish-bulk-action']"));
    protected final By unpublishButtonBulkAction = new ByChained(bulkActionRoot, By.xpath(".//button[@aria-label='unpublish-bulk-action']"));
    protected final By moreActionsButtonBulkAction = new ByChained(bulkActionRoot, By.xpath(".//button[@aria-label='More actions']"));
    protected final By duplicateButtonBulkAction = By.id("duplicate-bulk-action");
    protected final By exportButtonBulkAction = By.id("export-bulk-action");
    protected final By deleteButtonBulkAction = By.id("delete-bulk-action");

    private By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");

}
