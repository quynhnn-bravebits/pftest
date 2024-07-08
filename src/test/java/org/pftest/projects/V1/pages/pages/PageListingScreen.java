package org.pftest.projects.V1.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.ModalConstants;
import org.pftest.constants.PagesConstants;
import org.pftest.constants.UrlConstants;
import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V1.pages.CommonPage;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/pages
public class PageListingScreen extends CommonPage {
    private final By importButton = By.id("import-btn");
    private final By exportButton = By.id("export-btn");
    private final By createFromTemplateButton = By.xpath("//*/button/span[text()='" + PagesConstants.CREATE_FROM_TEMPLATE_BUTTON + "']");
    private final By createFromBlankButton = By.xpath("//*/button/span[text()='" + PagesConstants.CREATE_FROM_BLANK_BUTTON + "']");

    protected final By dataTable = By.xpath("//*[@id=\"AppFrameMain\"]//*[@class=\"Polaris-IndexTable\"]");
    protected final By searchAndFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Search and filter results']"));
    protected final By addFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Add filter']"));
    protected final By selectAllPagesCheckbox = new ByChained(dataTable, By.xpath("//thead//*[@class='Polaris-Checkbox']"));
    private final By bulkActionsUnpublishButton = new ByChained(dataTable, By.xpath("//button[.//*[text()='Unpublish'] and not(@disabled)]"));
    private final By bulkActionsPublishButton = new ByChained(dataTable, By.xpath("//button[.//*[text()='Publish'] and not(@disabled)]"));
    private final By bulkActionsMoreActionsButton = new ByChained(dataTable, By.xpath("//button[@aria-label='More actions']"));
    private final By bulkActionsDuplicateButton = By.id("duplicate-bulk-action");
    private final By bulkActionsExportButton = By.id("export-bulk-action");
    private final By bulkActionsDeleteButton = By.id("delete-bulk-action");

    public By getPageRowByIndex(int index) {
        return By.xpath(String.format("//tbody//tr[%d]", index));
    }

    public By getPageRowByIndexMobile(int index) {
        return By.xpath("(//*[@class='Polaris-IndexTable']//ul/li[@id]//h6)[" + index + "]");
    }

    public By getPageRowById(String id) {
        return By.xpath(String.format("//tbody//tr[@id='%s']", id));
    }

    public By getPageRowByTitle(String title) {
        return By.xpath(String.format("//tbody//tr[.//h6[contains(text(), '%s')]]", title));
    }

    public By getPublishedPageRowByIndex(int index) {
        String xpath = String.format("(//tbody/tr[//td[@id='pages--table--status']/span[@class='Polaris-Badge Polaris-Badge--toneSuccess']//*[text()='Published']])[%s]", index);
        return By.xpath(xpath);
    }

    public By getUnpublishedPageRowByIndex(int index) {
        String xpath = String.format("(//tbody/tr[//td[@id='pages--table--status']/span[@class='Polaris-Badge']//*[text()='Unpublished']])[%s]", index);
        return By.xpath(xpath);
    }

    @Step("Open the page listing screen")
    public void openPageListingPage() {
        openWebsite(UrlConstants.PF_PAGES_URL);
        waitForElementVisible(By.id("AppFrameMain"));
        getJsExecutor().executeScript("arguments[0].style.paddingLeft='0px';", getWebElement(By.id("AppFrameMain")));
        switchToPageFlyFrame();
    }

    @Step("Wait for the page listing to be loaded")
    public void verifyPageListingLoaded() {
        verifyPageTitle(PagesConstants.PAGE_LISTING_PAGE_TITLE);
        verifyElementVisible(importButton);
        verifyElementVisible(exportButton);
        verifyElementVisible(createFromTemplateButton);
        verifyElementVisible(createFromBlankButton);
        verifyElementVisible(dataTable);
    }

    //    ================== Conditions ==================
    @Step("Verify haven't published homepage")
    public void verifyHaventPublishedHomepage() {
        clickElement(By.id("home"));
        filterPageByStatus("Published");
        sleep(3);
        if (isElementVisible(getPublishedPageRowByIndex(1), 5)) {
            selectAllPages();
            unpublishAllSelectedPages();
        }
        verifyElementNotVisible(getPublishedPageRowByIndex(1), 5);
    }

    @Step("Verify have published homepage")
    public void verifyHavePublishedHomepage() {
        clickElement(By.id("home"));
        filterPageByStatus("Published");
        waitForElementVisible(getPublishedPageRowByIndex(1));
    }

    @Step("Verify haven't clicked remind option in Enable Autosave modal")
    public void userHaventClickRemindEnableAutoSave() {
        getJsExecutor().executeScript("window.localStorage.removeItem('no-auto-save');");
    }

    @Step("Verify haven't click remind option in Save page modal")
    public void userHaventClickRemindSavePage() {
        getJsExecutor().executeScript("window.localStorage.removeItem('warning_saved');");
    }


//    ================== Modals ==================
    @Step("Verify 'Editor is unavailable on mobile' modal is shown")
    public void verifyEditorUnavailableOnMobileModal() {
        waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.EDITOR_UNAVAILABLE_ON_MOBILE_MODAL.TITLE);
        By closeButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.EDITOR_UNAVAILABLE_ON_MOBILE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(closeButton);
        verifyElementNotVisible(modal);
    }

    @Step("Confirm 'Delete {0} page?' modal")
    public void confirmDeletePage(String number) {
        waitForElementTextContains(modal, "Delete " + number + " " + (number.equals("01") ? "page" : "pages") + "?");
        By deleteButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Delete']]");
        clickElement(deleteButton);
    }

    @Step("Confirm 'Export page' modal")
    public void confirmExportPage() {
        waitForElementTextContains(modal, "Export page");
        By exportButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Export']]");
        clickElement(exportButton);
    }

    @Step("Confirm 'Export pages' modal with 'All pages' option selected")
    public void confirmExportAllPages() {
        waitForElementTextContains(modal, "Export pages");
        assert verifyElementChecked(By.id("modal_all"), "Option 'All pages' is not selected");
        By exportButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Export']]");
        clickElement(exportButton);
    }

    @Step("Confirm 'Export pages' modal with 'Selected pages' option selected")
    public void confirmExportSelectedPages() {
        waitForElementTextContains(modal, "Export pages");
        assert verifyElementChecked(By.id("modal_selected"), "Option 'Selected pages' is not selected");
        By exportButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Export']]");
        clickElement(exportButton);
    }

    @Step("Select option {0} in 'Export pages' modal and click 'Export' button")
    public void confirmExportPages(String value) {
        waitForElementTextContains(modal, "Export pages");
        if (!verifyElementChecked(By.id(value))) {
            By option = By.xpath("//label[contains(@class, 'Polaris-Choice') and @for='" + value + "']");
            waitForElementClickable(option);
            clickElement(option);
        }
        By exportButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Export']]");
        clickElement(exportButton);
    }

    @Step("Confirm 'Import pages/sections' modal")
    public ArrayList<String> confirmImportPagesSections() {
        waitForElementTextContains(modal, "Import pages/sections");
        ArrayList<String> titles = getValueTableByColumn(modal, 1);

        sleep(1.5);
        By checkbox = new ByChained(modal, By.xpath(".//*[@class='Polaris-Checkbox']"));
        waitForElementClickable(checkbox);
        clickElement(checkbox);

        By importButton = By.xpath("//*[@role='dialog']//button[.//*[text()='Import']]");
        waitForElementClickable(importButton);
        clickElement(importButton);

        return titles;
    }
//    ================== Filter Page ==================

    @Step("Filter page by status {0}")
    public void filterPageByStatus(String status) {
        clickElement(searchAndFilterButton);
        clickElement(addFilterButton);
        By statusOption = By.xpath("//div[@class='Polaris-Popover']//button[@role='menuitem'][.//*[text()='Status']]");
        clickElement(statusOption);
        By publishedOption = By.xpath(String.format("//div[@class='Polaris-Popover']//label[@for='%s-status-filter'][.//*[text()='%s']]", status.toLowerCase(), status));
        clickElement(publishedOption);
        clickElement(addFilterButton);
    }

//    ================== Page Actions ==================

    @Step("Select all pages in the data table")
    public void selectAllPages() {
        clickElement(selectAllPagesCheckbox);
    }

    @Step("Select page by index {0}")
    public String selectPageByIndex(int index) {
        By row = getPageRowByIndex(index);
        waitForElementVisible(row);
        String id = getAttributeElement(row, "id");
        clickElement(new ByChained(row, By.xpath(".//*[@class='Polaris-Checkbox']")));
        return id;
    }

    @Step("Unpublish all selected pages")
    public void unpublishAllSelectedPages() {
        waitForElementClickable(bulkActionsUnpublishButton);
        clickElement(bulkActionsUnpublishButton);
        int count = getWebElements(By.xpath("//tbody//*[@class='Polaris-Checkbox']//input[@type='checkbox' and @aria-checked='true']"))
                .size();
        if (count > 1) {
            getToast().verifyShowUnpublishingPagesToast();
            getToast().verifyShowUnpublishedPagesToast();
        }
        else {
            getToast().verifyShowUnpublishingPageToast();
            getToast().verifyShowUnpublishedPageToast();
        }
    }

    @Step("Publish all selected pages")
    public void publishAllSelectedPages() {
        waitForElementClickable(bulkActionsPublishButton);
        clickElement(bulkActionsPublishButton);
        getToast().verifyShowPublishingPageToast();
        getToast().verifyShowPublishedPageToast();
    }

    @Step("Duplicate all selected pages")
    public void duplicateAllSelectedPages() {
        waitForElementClickable(bulkActionsMoreActionsButton);
        clickElement(bulkActionsMoreActionsButton);
        waitForElementClickable(bulkActionsDuplicateButton);
        clickElement(bulkActionsDuplicateButton);
        getToast().verifyShowDuplicatingPageToast();
        getToast().verifyShowDuplicatedPageToast();
    }

    @Step("Delete all selected pages")
    public void deleteAllSelectedPages(@Nullable String pageNumber) {
        waitForElementClickable(bulkActionsMoreActionsButton);
        clickElement(bulkActionsMoreActionsButton);
        waitForElementClickable(bulkActionsDeleteButton);
        clickElement(bulkActionsDeleteButton);
        confirmDeletePage(pageNumber == null ? "25" : pageNumber);
        getToast().verifyShowDeletingPageToast();
        getToast().verifyShowDeletedPageToast();
    }

    @Step("Export all selected pages")
    public void exportAllSelectedPages(int pageNumber) {
        waitForElementClickable(bulkActionsMoreActionsButton);
        clickElement(bulkActionsMoreActionsButton);
        waitForElementClickable(bulkActionsExportButton);
        clickElement(bulkActionsExportButton);
        if (pageNumber == 1) {
            confirmExportPage();
            getToast().verifyShowExportedPageToast();
        } else {
            confirmExportSelectedPages();
            getToast().verifyShowExportingPagesToast();
            getToast().verifyShowExportedPagesToast();
        }
        verifyExportedPages();
    }

    @Step("Click on the 'Export' button to export all pages")
    public void exportAllPages() {
        clickElement(exportButton);
        confirmExportAllPages();
        getToast().verifyShowExportingPagesToast();
        getToast().verifyShowExportedPagesToast();
        verifyExportedPages();
    }

    @Step("Verify the exported pages in the download file")
    public void verifyExportedPages() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'export-pages-'yyyy-M-d-H-m");
        String formattedDateTime = now.format(formatter);
        System.out.println("Expected file name: " + formattedDateTime);
        assert verifyDownloadFileContainsName(formattedDateTime, 5);
    }

    @Step("Import a page")
    public ArrayList<String> importPage(String path) {
        clickElement(importButton);
        uploadFileWithLocalForm(By.className("Polaris-DropZone-FileUpload__Action"), path);
        ArrayList<String> titles = confirmImportPagesSections();
        if (titles.size() == 1) {
            getToast().verifyShowImportingPageToast();
            getToast().verifyShowImportedPageToast();
        } else {
            getToast().verifyShowImportingPagesToast();
            getToast().verifyShowImportedPagesToast();
        }

        return titles;
    }

//    ================== Open Page ==================

    @Step("Click on the row {0} in the data table in mobile screen")
    public void openPageInPageListingMobile(Integer index) {
        By row = getPageRowByIndexMobile(index);
        clickElement(row);
    }

    @Step("Click on the row {0} in the data table")
    public String openPageInPageListing(Integer index) {
        By row = getPageRowByIndex(index);
        String id = getAttributeElement(row, "id");
        clickElement(row);
        return id;
    }

    @Step("Click on the row 1 in the data table")
    public String openPageInPageListing() {
        By row = getPageRowByIndex(1);
        String id = getAttributeElement(row, "id");
        clickElement(row);
        return id;
    }

    @Step("Click on the row has id {0} in the data table")
    public void openPageInPageListing(String id) {
        By row = getPageRowById(id);
        clickElement(row);
    }

    @Step("Open the first published page in the data table")
    public void openPublishedPage() {
        filterPageByStatus("Published");
        sleep(1);
        By row = getPublishedPageRowByIndex(1);
        clickElement(row);
    }

    @Step("Open the first unpublished page in the data table")
    public void openUnpublishedPage() {
        filterPageByStatus("Unpublished");
        sleep(1);
        By row = getUnpublishedPageRowByIndex(1);
        clickElement(row);
    }

//    ================== Open Page Setting ==================

    @Step("Open page setting of the row {0} in the data table")
    public String openPageSettingInPageListing(Integer index) {
        By row = getPageRowByIndex(index);
        String id = getAttributeElement(row, "id");
        clickElement(By.id("setting-" + id));
        waitForPageLoaded();
        return id;
    }

    @Step("Open page setting of the row 1 in the data table")
    public String openPageSettingInPageListing() {
        By row = getPageRowByIndex(1);
        String id = getAttributeElement(row, "id");
        clickElement(By.id("setting-" + id));
        waitForPageLoaded();
        return id;
    }


//    ================== Create New Page ==================

    @Step("Select a page template")
    public void selectPageTemplate() {
        waitForPageLoaded();
        verifyPageTitle(PagesConstants.SELECT_PAGE_TEMPLATE);
        sleep(1);
        int templatesCount = getWebElements(By.xpath("//div[@class='template-list-modal--template-list--template-card']//button/*[text()='Select']"))
                .size();
        // Generate a random index between 0 and 5
        int randomIndex = new Random().nextInt(1, templatesCount);
        By template = By.xpath("(//div[@class='template-list-modal--template-list--template-card']//button/*[text()='Select'])[" + randomIndex + "]");
        moveToElement(template);
        waitForElementVisible(template);
        hoverOnElement(template);
        clickElement(template);
    }

    /**
     * <p>Clicks the 'Create from template' button on the page.</p>
     * <p>Clicks the template specified by the pageType parameter.</p>
     * <p>Randomly selects a template from the list of templates.</p>
     * <p>Clicks the 'Select' button on the selected template.</p>
     *
     * @param pageType The type of page to create
     */
    @Step("Create a new {0} page from template")
    public void createNewPageFromTemplate(PageType pageType) {
        clickElement(createFromTemplateButton);
        clickElement(By.id(pageType.name().toLowerCase() + "-template"));
        selectPageTemplate();
    }

    /**
     * <p>Clicks the 'Create blank page' button on the page.</p>
     * <p>Clicks the blank page specified by the pageType parameter.</p>
     *
     * @param pageType The type of page to create
     */
    @Step("Create a new {0} page from blank")
    public void createNewPageFromBlank(PageType pageType) {
        clickElement(createFromBlankButton);
        clickElement(By.id(pageType.name().toLowerCase() + "-blank"));
    }


}