package org.pftest.projects.V2.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.base.BaseTest;
import org.pftest.base.BaseTestV2;
import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.PageStatus;
import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.modal.delete.DeletePageSectionModal;
import org.pftest.projects.V2.components.modal.export.ExportManyModal;
import org.pftest.projects.V2.components.modal.export.ExportModal;

import static org.pftest.keywords.WebUI.*;

public abstract class BaseListingScreen extends BaseTestV2 {
    protected By pageTitle = By.xpath("//h1[@class='Polaris-Header-Title']");
    protected final By actionMenu = By.xpath("//div[@class='Polaris-ActionMenu-Actions__ActionsLayout']");
    protected final By primaryMenu = By.xpath("//div[@class='Polaris-Page-Header__PrimaryActionWrapper']");
    protected final By importButton = new ByChained(actionMenu, By.id("import-btn"));
    protected final By exportButton = new ByChained(actionMenu, By.id("export-btn"));

    protected final By indexFilter = By.xpath("//div[@class='Polaris-IndexFilters']");
    protected final By searchAndFilterButton = new ByChained(indexFilter, By.xpath("//button[@aria-label='Search and filter results']"));
    protected final By addFilterButton = new ByChained(indexFilter, By.xpath("//button[@aria-label='Add filter']"));

    protected final By indexTable = By.xpath("//div[@class='Polaris-IndexTable']");
    protected final By selectAllPagesCheckbox = new ByChained(indexTable, By.xpath(".//thead//*[@class='Polaris-Checkbox']"));
    protected final By bulkActionRoot = new ByChained(indexTable, By.xpath(".//div[@class='Polaris-BulkActions__BulkActionsLayout']"));
    protected final By publishButtonBulkAction = new ByChained(bulkActionRoot, By.xpath(".//button[@aria-label='publish-bulk-action']"));
    protected final By unpublishButtonBulkAction = new ByChained(bulkActionRoot, By.xpath(".//button[@aria-label='unpublish-bulk-action']"));
    protected final By moreActionsButtonBulkAction = new ByChained(indexTable, By.xpath(".//button[@aria-label='More actions' and not(ancestor::div[contains(@class, 'Polaris-BulkActions__BulkActionsMeasurerLayout')])]"));

    protected final By duplicateButtonBulkAction = By.id("duplicate-bulk-action");
    protected final By exportButtonBulkAction = By.id("export-bulk-action");
    protected final By deleteButtonBulkAction = By.id("delete-bulk-action");

    protected final By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");

    public By getRowByIndex(int index) {
        return By.xpath(String.format("//tbody//tr[%d]", index));
    }

    public By getRowById(String id) {
        return By.xpath(String.format("//tbody//tr[@id='%s']", id));
    }

    public By getRowByTitle(String title) {
        return By.xpath(String.format("//tbody//tr[.//h6[contains(text(), '%s')]]", title));
    }

    public By getPublishedRowByIndex(int index) {
        String xpath = String.format("(//tbody/tr[.//td[3]/span[@class='Polaris-Badge Polaris-Badge--toneSuccess']//*[text()='Published']])[%s]", index);
        return By.xpath(xpath);
    }

    public By getUnpublishedRowByIndex(int index) {
        String xpath = String.format("(//tbody/tr[.//td[3]/span[@class='Polaris-Badge Polaris-Badge--toneSuccess']//*[text()='Unpublished']])[%s]", index);
        return By.xpath(xpath);
    }

    public abstract void verifyPageLoaded();

    public void filterBy(String type, String option) {
        if (isElementVisible(searchAndFilterButton, 3)) {
            clickElement(searchAndFilterButton);
        }
        clickElement(addFilterButton);
        By statusOption = By.xpath("//div[@class='Polaris-Popover']//button[@role='menuitem'][.//*[text()='" + type + "']]");
        clickElement(statusOption);
        By publishedOption = By.xpath(String.format("//div[@class='Polaris-Popover']//label[@for='%s-%s-filter'][.//*[text()='%s']]", option.toLowerCase(), type.toLowerCase(), option));
        clickElement(publishedOption);
        clickElement(addFilterButton);
    }

    @Step("Filter page by status {0}")
    public void filterByStatus(PageStatus status) {
        filterBy("Status", status.toString());
    }

    @Step("Filter page by type {0}")
    public void filterByType(String type) {
        filterBy("Type", type);
    }

    @Step("Select all rows in the active table page")
    public void selectAll() {
        clickElement(selectAllPagesCheckbox);
    }

    public String getPageIdInPageListing(Integer index) {
        By row = getRowByIndex(index);
        String id = getAttributeElement(row, "id");
        return id;
    }

    public String getPageTitleInPageListing(Integer index) {
        By row = getRowByIndex(index);
        String title = getTextElement(new ByChained(row, By.xpath(".//h6")));
        return title;
    }

    public int getNumberOfRows() {
        return getWebElements(By.xpath("//tbody//tr")).size();
    }

    @Step("Select row checkbox by index {0}")
    public String selectRowByIndex(int index) {
        By row = getRowByIndex(index);
        String id = getAttributeElement(row, "id");
        clickElement(new ByChained(row, By.xpath(".//*[@class='Polaris-Checkbox']")));
        verifyElementChecked(By.id("Select-" + id));
        return id;
    }

    @Step("Click on the row {0} in the data table")
    public String openPageInPageListing(Integer index) {
        By row = getRowByIndex(index);
        String id = getAttributeElement(row, "id");
        clickElement(row);
        return id;
    }

    @Step("Click on the row has id {0} in the data table")
    public void openPageInPageListing(String id) {
        By row = getRowById(id);
        clickElement(row);
    }

    @Step("Open the first published page in the data table")
    public void openPublishedPage() {
        filterByStatus(PageStatus.PUBLISHED);
        sleep(1);
        By row = getPublishedRowByIndex(1);
        clickElement(row);
    }

    @Step("Open the first unpublished page in the data table")
    public void openUnpublishedPage() {
        filterByStatus(PageStatus.UNPUBLISHED);
        sleep(1);
        By row = getUnpublishedRowByIndex(1);
        clickElement(row);
    }

    public void publishAllSelectedPages(ListingType listingType) {
        waitForElementClickable(publishButtonBulkAction);
        clickElement(publishButtonBulkAction);

        switchToDefaultContent();
        if (listingType == ListingType.PAGE) {
            Toast.verifyShowPublishingPagesToast();
            Toast.verifyShowPublishedPagesToast();
        } else {
            Toast.verifyShowPublishingSectionsToast();
            Toast.verifyShowPublishedSectionsToast();
        }
        switchToPageFlyFrame();
    }

    public void duplicateAllSelectedPages(ListingType listingType) {
        waitForElementVisible(moreActionsButtonBulkAction);
        waitForElementClickable(moreActionsButtonBulkAction);
        clickElement(moreActionsButtonBulkAction);

        waitForElementClickable(duplicateButtonBulkAction);
        clickElement(duplicateButtonBulkAction);

        switchToDefaultContent();
        if (listingType == ListingType.PAGE) {
            Toast.verifyShowDuplicatingPagesToast();
            Toast.verifyShowDuplicatedPagesToast();
        } else {
            Toast.verifyShowDuplicatingSectionsToast();
            Toast.verifyShowDuplicatedSectionsToast();
        }
        switchToPageFlyFrame();
    }

    public void confirmDeletePage(ListingType listingType, int number) {
        DeletePageSectionModal deletePageSectionModal = new DeletePageSectionModal(listingType, number);
        deletePageSectionModal.verifyVisible();
        deletePageSectionModal.clickPrimaryButton();
    }

    public void deleteAllSelectedPages(ListingType listingType, int pageNumber) {
        waitForElementVisible(moreActionsButtonBulkAction);
        waitForElementClickable(moreActionsButtonBulkAction);
        clickElement(moreActionsButtonBulkAction);

        waitForElementClickable(deleteButtonBulkAction);
        clickElement(deleteButtonBulkAction);
        confirmDeletePage(listingType, pageNumber);

        switchToDefaultContent();
        if (listingType == ListingType.PAGE) {
            Toast.verifyShowDeletingPagesToast();
            Toast.verifyShowDeletedPagesToast();
        } else {
            Toast.verifyShowDeletingSectionsToast();
            Toast.verifyShowDeletedSectionsToast();
        }
        switchToPageFlyFrame();
    }

    @Step("Confirm 'Export page' modal")
    public void confirmExportPage() {
        ExportModal exportModal = new ExportModal(ListingType.PAGE);
        exportModal.verifyVisible();
        exportModal.clickPrimaryButton();
    }

    public void exportAllSelectedPages(int pageNumber) {
        waitForElementClickable(moreActionsButtonBulkAction);
        clickElement(moreActionsButtonBulkAction);
        waitForElementClickable(exportButtonBulkAction);
        clickElement(exportButtonBulkAction);

        if (pageNumber == 1) {
            ExportModal exportModal = new ExportModal(ListingType.PAGE);
            exportModal.verifyVisible();
            exportModal.clickPrimaryButton();
        } else {
            ExportManyModal exportModal = new ExportManyModal(ListingType.PAGE);
            exportModal.verifyVisible();
            exportModal.verifySelectSelectedPages();
            exportModal.clickPrimaryButton();
        }

        switchToDefaultContent();
        Toast.verifyShowExportedPagesToast();
        ExportModal.verifyDownloadedExportedFile();
        switchToPageFlyFrame();
    }

    public void exportAllPages() {
        clickElement(exportButton);
        ExportManyModal exportModal = new ExportManyModal(ListingType.PAGE);
        exportModal.verifyVisible();
        exportModal.verifySelectAllPages();
        exportModal.clickPrimaryButton();

        switchToDefaultContent();
        Toast.verifyShowExportingPagesToast();
        Toast.verifyShowExportedPagesToast();
        ExportModal.verifyDownloadedExportedFile();
        switchToPageFlyFrame();
    }

}
