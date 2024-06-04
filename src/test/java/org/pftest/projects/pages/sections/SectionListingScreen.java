package org.pftest.projects.pages.sections;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.constants.UrlConstants;
import org.pftest.projects.pages.CommonPage;

import java.util.Random;

import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/sections
public class SectionListingScreen extends CommonPage {
    private By importButton = By.id("pages--import-btn");
    private By exportButton = By.id("pages--export-btn");
    private By createFromBlankButton = By.id("pages--create-blank-section-btn");
    private By moreActionsButton = By.xpath("//*/button/span[text()='More actions']");
    private By createFromTemplateButton = By.id("pages--create-from-template-btn");
    private By emptyState = By.id("empty-state");


    private By dataTable = By.xpath("//*[@id=\"AppFrameMain\"]//*[@class=\"Polaris-IndexTable\"]");
    private By searchAndFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Search and filter results']"));
    private By addFilterButton = new ByChained(dataTable, By.xpath("//button[@aria-label='Add filter']"));

    public By getPageRowByIndex(int index) {
        return By.xpath(String.format("//tbody//tr[%d]", index));
    }

    public void openSectionListingPage() {
        openWebsite(UrlConstants.PF_SECTIONS_URL);
        switchToPageFlyFrame();
    }

    @Step("Wait for the section listing to be loaded")
    public void verifySectionListingLoaded() {
        verifyElementVisible(importButton);
        verifyElementVisible(exportButton);
        verifyElementVisible(createFromBlankButton);
        verifyElementVisible(moreActionsButton);
        verifyElementVisible(dataTable);
    }

//    ================== Create New Section ==================

    @Step("Create a new section from blank")
    public void createSectionFromBlank() {
        clickElement(createFromBlankButton);
    }

    @Step("Create a new section from template")
    public void createSectionFromTemplate() {
        clickElement(moreActionsButton);
        clickElement(createFromTemplateButton);
        waitForPageLoaded();

        verifyPageTitle(PagesConstants.SELECT_SECTION_TEMPLATE);
        sleep(1);
        // Generate a random index between 0 and 5
        int randomIndex = new Random().nextInt(1, 5);
        By template = By.xpath("(//div[@class='template-card__action']//button/*[text()='Select'])[" + randomIndex + "]");
        moveToElement(template);
        hoverOnElement(template);
        clickElement(template);

    }

}