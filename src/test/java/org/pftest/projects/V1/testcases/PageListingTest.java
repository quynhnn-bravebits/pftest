package org.pftest.projects.V1.testcases;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.pftest.base.BaseTest;
import org.pftest.enums.pagefly.PageType;
import org.pftest.helpers.Helpers;
import org.pftest.keywords.WebUI;
import org.testng.annotations.Test;

import java.util.ArrayList;
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

    @Flaky
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

        addStep(
                "Step 3: Go to Trash screen and re-check deleted page",
                () -> {
                    getTrashScreen().openTrashPage();
                    getTrashScreen().verifyTrashPageLoaded();
                    WebUI.verifyElementVisible(getPageListingScreen().getPageRowById(pageId.get()), "Verify page is in Trash");
                }
        );
    }

    @Feature("Bulk Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B21")
    @Tags({@Tag("Page Listing"), @Tag("Export")})
    @Test(description = "TC-016: User export the selected page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void exportSelectedPageInThePageListingScreen() {
        addStep(
                "Step 0: Open Page Listing page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Select 1st page in the table and Export page",
                () -> {
                    getPageListingScreen().selectPageByIndex(1);
                    getPageListingScreen().exportAllSelectedPages(1);
                }
        );

        addStep(
                "Step 1: Select 2 pages in the table and Export page",
                () -> {
                    getPageListingScreen().selectPageByIndex(2);
                    getPageListingScreen().exportAllSelectedPages(2);
                }
        );

    }

    @Feature("Page Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B20")
    @Tags({@Tag("Page Listing"), @Tag("Export")})
    @Test(description = "TC-017: User export all pages in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void exportAllPagesInThePageListingScreen() {
        addStep(
                "Step 0: Open Page Listing page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Click on Export button to export all pages",
                () -> {
                    getPageListingScreen().exportAllPages();
                }
        );
    }

    @Feature("Page Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B22")
    @Tags({@Tag("Page Listing"), @Tag("Import")})
    @Test(description = "TC-018: User import page in the Page listing screen", suiteName = "Basic UAT - Page Listing")
    public void importPageInThePageListingScreen() {
        AtomicReference<ArrayList<String>> titles = new AtomicReference<>(new ArrayList<>());
        addStep(
                "Step 0: Open Page Listing page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Import .pagefly file from local",
                () -> {
                    titles.set(getPageListingScreen().importPage(Helpers.getCurrentDir() + "src/test/resources/data/pageflyExport/export-pages-my-store--1-regular--2-password.pagefly"));
                }
        );

        addStep(
                "Step 2: Verify imported pages successfully",
                () -> {
                    for (String title : titles.get()) {
                        WebUI.verifyElementVisible(getPageListingScreen().getPageRowByTitle(title), "Verify imported page: " + title);
                    }
                }
        );
    }


    @Feature("Page Actions")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://docs.google.com/spreadsheets/d/1zlhx6KpGVsGgH05ArwLRv1nqI4oSdxj8Gll203FDP1I/edit#gid=154559871&range=B23")
    @Tags({@Tag("Page Listing"), @Tag("Import")})
    @Test(description = "TC-019: User import page exported from the other's store", suiteName = "Basic UAT - Page Listing")
    public void importPageExportedFromOtherStore() {
        AtomicReference<ArrayList<String>> titles = new AtomicReference<>(new ArrayList<>());
        AtomicReference<String> editorContent = new AtomicReference<>();
        AtomicReference<String> livePageContent = new AtomicReference<>();

        addStep(
                "Step 0: Open Page Listing page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                }
        );

        addStep(
                "Step 1: Import .pagefly file from local",
                () -> {
                    titles.set(getPageListingScreen().importPage(Helpers.getCurrentDir() + "src/test/resources/data/pageflyExport/export-pages-nana-store--1-regular.pagefly"));
                }
        );

        addStep(
                "Step 2: Verify imported pages successfully",
                () -> {
                    for (String title : titles.get()) {
                        WebUI.verifyElementVisible(getPageListingScreen().getPageRowByTitle(title), "Verify imported page: " + title);
                    }
                }
        );

        addStep(
                "Step 3: Open imported page",
                () -> {
                    WebUI.clickElement(getPageListingScreen().getPageRowByTitle(titles.get().get(0)));
                    getEditorPage().verifyEditorPageLoaded();
                    getEditorPage().verifyPageIsUnpublished();
                }
        );

        addStep(
                "Step 4: Save & Publish the imported page",
                () -> {
                    getEditorPage().changePageTitle("Updated Imported Page");
                    pageEditingTest.saveAndPublishPageSuccessfully();
                    editorContent.set(getEditorPage().getCanvasHtmlProcessed());
                }
        );

//        addStep(
//                "Step 5: Open live page & verify changes",
//                () -> {
//                    livePageContent.set(getEditorPage().getLivePageSource());
//                }
//        );

//        WebUI.verifyEquals(editorContent.get(), livePageContent.get(), "Live page content is not the same as editor content");
    }
}
