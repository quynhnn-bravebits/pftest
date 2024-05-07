package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.enums.PageType;
import org.pftest.projects.CommonPage;

import java.util.Random;

import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/pages
public class PageListingScreen extends CommonPage {
    private By importButton = By.id("import-btn");
    private By exportButton = By.id("export-btn");
    private By createFromTemplateButton = By.xpath("//*/button/span[text()='" + PagesConstants.CREATE_FROM_TEMPLATE_BUTTON + "']");
    private By createFromBlankButton = By.xpath("//*/button/span[text()='" + PagesConstants.CREATE_FROM_BLANK_BUTTON + "']");

    private By dataTable = By.xpath("//*[@id=\"AppFrameMain\"]//*[@class=\"Polaris-IndexTable\"]");
    private By searchAndFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Search and filter results']"));
    private By addFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Add filter']"));

    public By getPageRowByIndex(int index) {
        return By.xpath(String.format("//tbody//tr[%d]", index));
    }
    public By getPageRowById(String id) {return By.xpath(String.format("//tbody//tr[@id='%s']", id));}
    public By getPublishedPageRowByIndex(int index) {
        String xpath = String.format("(//tbody/tr[//td[@id='pages--table--status']/span[@class='Polaris-Badge Polaris-Badge--toneSuccess']//*[text()='Published']])[%s]", index);
        return By.xpath(xpath);
    }

    public void openPageListingPage() {
        openWebsite("https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/pages");
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

//    ================== Filter Page ==================

    @Step("Filter page by status {0}")
    public void filterPageByStatus(String status) {
        clickElement(searchAndFilterButton);
        clickElement(addFilterButton);
        By statusOption = By.xpath("//div[@class='Polaris-Popover']//button[@role='menuitem'][.//*[text()='Status']]");
        clickElement(statusOption);
        By publishedOption = By.xpath(String.format("//div[@class='Polaris-Popover']//label[@for='%s-status-filter'][.//*[text()='%s']]", status.toLowerCase(), status));
        clickElement(publishedOption);
    }

//    ================== Open Page ==================

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
        By row = getPublishedPageRowByIndex(1);
        clickElement(row);
    }

    @Step("Open the first unpublished page in the data table")
    public void openUnpublishedPage() {
        filterPageByStatus("Unpublished");
        By row = getPublishedPageRowByIndex(1);
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
        waitForPageLoaded();
        verifyPageTitle(PagesConstants.SELECT_PAGE_TEMPLATE);
        // Generate a random index between 0 and 5
        int randomIndex = new Random().nextInt(1, 5);
        By template = By.xpath("(//div[@class='template-list-modal--template-list--template-card']//button/*[text()='Select'])[" + randomIndex + "]");
        moveToElement(template);
        clickElement(template);
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