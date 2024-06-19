package org.pftest.projects.testcases;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.pftest.keywords.WebUI;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;


@Epic("Page Listing")
public class PageListingTest extends BaseTest {
    PageEditingTest pageEditingTest = new PageEditingTest();

    @Feature("Save and Publish")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B15")
    @Tags({@Tag("Page Listing"), @Tag("Page Editing"), @Tag("Product"), @Tag("Template")})
    @Test(description = "TC-011: User create new template PRODUCT page from the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void createNewTemplateProductPage() {
        pageEditingTest.saveAndPublishNewProductCollectionPageFromTemplate(PageType.PRODUCT);
    }

    @Feature("Bulk Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B16")
    @Tags({@Tag("Page Listing"), @Tag("Publish")})
    @Test(description = "TC-012: User publish page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void publishPageInThePageListingScreen() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().filterPageByStatus("Unpublished");
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.waitForElementVisible(getPageListingScreen().getUnpublishedPageRowByIndex(1), 5);
        getPageListingScreen().selectPageByIndex(1);
        getPageListingScreen().publishAllSelectedPages();
    }

    @Feature("Bulk Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B17")
    @Tags({@Tag("Page Listing"), @Tag("Unpublish")})
    @Test(description = "TC-013: User unpublish page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void unpublishPageInThePageListingScreen() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().filterPageByStatus("Published");
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        WebUI.waitForElementVisible(getPageListingScreen().getPublishedPageRowByIndex(1), 5);
        getPageListingScreen().selectPageByIndex(1);
        getPageListingScreen().unpublishAllSelectedPages();
    }

    @Feature("Bulk Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B18")
    @Tags({@Tag("Page Listing"), @Tag("Duplicate")})
    @Test(description = "User duplicate page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void duplicatePageInThePageListingScreen() {
        AtomicReference<String> originHTML = new AtomicReference<>();
        AtomicReference<String> duplicatedHTML = new AtomicReference<>();

        addStep(
                "Step 0: Verify content of selected page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                    getPageListingScreen().openPageInPageListing(1);
                    getEditorPage().verifyEditorPageLoaded();
                    originHTML.set(getEditorPage().getCanvasHtmlProcessed());
                    getEditorPage().backToPageListingScreen();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Duplicate selected page",
                () -> {
                    getPageListingScreen().selectPageByIndex(1);
                    getPageListingScreen().duplicateAllSelectedPages();
                    WebUI.sleep(3);
                }
        );

        addStep(
                "Step 2: Verify duplicated page",
                () -> {
                    getPageListingScreen().openPageInPageListing(1);
                    getEditorPage().verifyEditorPageLoaded();
                    duplicatedHTML.set(getEditorPage().getCanvasHtmlProcessed());
                    getEditorPage().verifyPageIsUnpublished();
                }
        );

        WebUI.verifyEquals(originHTML.get(), duplicatedHTML.get(), "Verify duplicated page is not the same as the original page");
    }

    @Feature("Bulk Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B19")
    @Tags({@Tag("Page Listing"), @Tag("Delete")})
    @Test(description = "TC-015: User delete page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void deletePageInThePageListingScreen() {
        AtomicReference<String> pageId = new AtomicReference<>();
        addStep(
                "Step 0: Open Page Listing page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Delete page",
                () -> {
                    pageId.set(getPageListingScreen().selectPageByIndex(1));
                    getPageListingScreen().deleteAllSelectedPages("01");
                    WebUI.sleep(1);
                }
        );

        addStep(
                "Step 2: Verify page is deleted",
                () -> {
                    WebUI.verifyElementNotPresent(getPageListingScreen().getPageRowById(pageId.get()), "Verify page is deleted");
                }
        );
    }
}
