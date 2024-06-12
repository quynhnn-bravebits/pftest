package org.pftest.projects.testcases;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.pftest.base.BaseTest;
import org.pftest.enums.ElementType;
import org.pftest.enums.PageType;
import org.pftest.utils.ImageUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

@Epic("Page Editing")
public class PageEditingTest extends BaseTest {
    @BeforeClass
    public void setup() {

    }

    @Step("Save page successfully")
    public void savePageSuccessfully() {
        getEditorPage().clickSavePageButton();
        if (!Objects.equals(getValueFromLocalStorage("warning_saved"), "true")) {
            getEditorPage().confirmSavePageModal();
        }
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Step("Save and Publish page successfully")
    public void saveAndPublishPageSuccessfully() {
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        getToast().verifyShowPublishedPageToast();
        getEditorPage().closeEnableAutoSaveModal();
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new {0} page from blank and save and publish successfully")
    public void saveAndPublishNewPageFromBlank(PageType pageType) {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        // 1. Click on button Create blank page
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        // 3. Add và edit element vào Canvas
        // 3. Badge save: Unsaved
        getEditorPage().dragAndDropHeadingElement();
        // 4. Click on button Save and Publish
        getEditorPage().clickSaveAndPublishPageButton();
        // 4. Badge save: Saving...
        getEditorPage().verifyPageIsSaving();
        // 4. Hiển thị modal:" Your page is ready to publish!"
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        // 4. Hiển thị toast "Publishing page..."
//        getToast().verifyShowPublishingToast();
        // 4. Hiển thị toast "Published"
        getToast().verifyShowPublishedPageToast();
        // 4. Nếu chưa remind modal Auto Save thì sẽ hiển thị modal "Enable autosave?"
        getEditorPage().closeEnableAutoSaveModal();
        // 4. Badge save: Saved
        // 4. Badge publish: Published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new {0} page from template and save and publish successfully")
    public void saveAndPublishNewPageFromTemplate(PageType pageType) {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        // 1. Click on button Create blank page
        getPageListingScreen().createNewPageFromTemplate(pageType);
        waitForPageLoaded();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        // 3. Add và edit element vào Canvas
        // 3. Badge save: Unsaved
        getEditorPage().dragAndDropHeadingElement();
        // 4. Click on button Save and Publish
        getEditorPage().clickSaveAndPublishPageButton();
        // 4. Badge save: Saving...
        getEditorPage().verifyPageIsSaving();
        // 4. Hiển thị modal:" Your page is ready to publish!"
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        // 4. Hiển thị toast "Publishing page..."
//        getToast().verifyShowPublishingToast();
        // 4. Hiển thị toast "Published"
        getToast().verifyShowPublishedPageToast();
        // 4. Nếu chưa remind modal Auto Save thì sẽ hiển thị modal "Enable autosave?"
        getEditorPage().closeEnableAutoSaveModal();
        // 4. Badge save: Saved
        // 4. Badge publish: Published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new home page from blank and save and publish successfully")
    public void saveAndPublishNewHomePageFromBlank() {
        getPageListingScreen().createNewPageFromBlank(PageType.HOME);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.HOME.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        getEditorPage().confirmPublishingHomepageModal();
        getToast().verifyShowPublishedPageToast();
        getEditorPage().closeEnableAutoSaveModal();
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Test(description = "TC-001: Open page editor in the Page Listing screen")
    public void openPageEditorInPageListingScreen() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        String pageId = getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(pageId);
    }

    @Test(description = "TC-002: Open page editor in the Dashboard screen")
    public void openPageEditorInDashboardScreen() {
        getDashboardScreen().openDashboardPage();
        getDashboardScreen().verifyDashboardLoaded();
        String pageId = getDashboardScreen().openRecentPage();
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(pageId);
    }

    @Test(description = "TC-003: Open page editor by clicking Edit button in the Page Setting screen")
    public void openPageEditorByClickingEditButtonInPageListingScreen() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        String pageId = getPageListingScreen().openPageSettingInPageListing();
        getPageSettingScreen().verifyPageSettingScreenLoaded();
        verifyIdInUrl(pageId);
        getPageSettingScreen().clickEditPageButton();
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(pageId);
    }

    @Test(description = "TC-004: Open page editor by Search bar in the Dashboard screen")
    public void openPageEditorBySearchBarInDashboardScreen() {
        getDashboardScreen().openDashboardPage();
        getDashboardScreen().verifyDashboardLoaded();
        getDashboardScreen().searchPageInDashboard();
        getDashboardScreen().openSearchResultPage();
        getEditorPage().verifyEditorPageLoaded();
    }

    @Feature("Save and Publish")
    @Test(description = "TC-009: Create a new blog post page from blank")
    public void createNewBlogPostPageFromBlank() {
        saveAndPublishNewPageFromBlank(PageType.BLOG);
    }

    @Feature("Save and Publish")
    @Test(description = "TC-010: Create a new blog post page from template")
    public void createNewBlogPostPageFromTemplate() {
        saveAndPublishNewPageFromTemplate(PageType.BLOG);
    }

    @Feature("Save and Publish")
    @Test(description = "TC-011: Create a new regular/password page from blank")
    public void createNewRegularPasswordPageFromBlank() {
        saveAndPublishNewPageFromBlank(PageType.PAGE);
        saveAndPublishNewPageFromBlank(PageType.PASSWORD);
    }

    @Feature("Save and Publish")
    @Test(description = "TC-012: Create a new regular/password page from template")
    public void createNewRegularPasswordPageFromTemplate() {
        saveAndPublishNewPageFromTemplate(PageType.PAGE);
        saveAndPublishNewPageFromTemplate(PageType.PASSWORD);
    }

    @Feature("Save and Publish")
    @Test(description = "TC-013: Create a new home page from blank when there is no home page is published")
    public void createNewHomePageFromBlankWhenNoHomePagePublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().verifyHaventPublishedHomepage();
        saveAndPublishNewHomePageFromBlank();
    }

    @Feature("Save and Publish")
    @Test(description = "TC-014: Create a new home page from blank when there is a home page is published", dependsOnMethods = {"createNewHomePageFromBlankWhenNoHomePagePublished"})
    public void createNewHomePageFromBlankWhenHomepageIsPublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().verifyHavePublishedHomepage();
        saveAndPublishNewHomePageFromBlank();
    }

    @Feature("Don't remind me again")
    @Step("Save and Publish new PAGE page from blank and select Don't remind me again in the modal Enable autosave?")
    @Test(groups = "Select Don't remind me again", description = "TC-017: User select option \"Don't remind me again\" in the modal \"Enable autosave?\"")
    public void saveNewPageFromBlank_selectDontRemindEnableAutoSave() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().userHaventClickRemindEnableAutoSave();
        getPageListingScreen().userHaventClickRemindEnableAutoSave();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.PAGE.name() + " " + new Date());
        savePageByShortcut();
        getEditorPage().confirmBeforeSaveModal_TitledTitle();
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().confirmEnableAutoSaveModal();
    }

    @Step("Save and Publish new {0} page from blank and select Don't remind me again in the modal Save page")
    public void saveNewPageFromBlank_selectDontRemindSavePage(PageType pageType) {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
//        resetDontRemind();
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        savePageByShortcut();
        getEditorPage().confirmBeforeSaveModal_TitledTitle();
        getEditorPage().selectDontRemindAndConfirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Step("Save and Publish new {0} page from blank when user have selected Don't remind me again in the modal Save page")
    public void saveNewPageFromBlank_haveSelectedDontRemindSavePage(PageType pageType) {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        savePageByShortcut();
        getEditorPage().confirmBeforeSaveModal_TitledTitle();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Feature("Don't remind me again")
    @Test(groups = "Select Don't remind me again", description = "TC-018:  User select option \"Don't remind me again\" in the modal \"Save page\"")
    public void dontRemindSavePage() {
        saveNewPageFromBlank_selectDontRemindSavePage(PageType.PAGE);
        saveNewPageFromBlank_haveSelectedDontRemindSavePage(PageType.PAGE);
        saveNewPageFromBlank_haveSelectedDontRemindSavePage(PageType.BLOG);
        saveNewPageFromBlank_haveSelectedDontRemindSavePage(PageType.PASSWORD);
    }

    @Step("Save and Publish new HOME page from blank and select Don't remind me again in the modal Publish page")
    public void saveAndPublishNewHomePageFromBlank_selectDontRemindPublishPage() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.HOME);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.HOME.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        getEditorPage().selectDontRemindAndConfirmPublishingHomePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyShowPublishingPageToast();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().verifyPageIsPublished();
        getEditorPage().verifyShowPublishedPageToast();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Step("Save and Publish new HOME page from blank when user have selected Don't remind me again in the modal Publish page")
    public void saveAndPublishNewHomePageFromBlank_haveSelectedDontRemindPublishPage() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.HOME);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.HOME.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyShowPublishingPageToast();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().verifyPageIsPublished();
        getEditorPage().verifyShowPublishedPageToast();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Step("Save and Publish new {0} page from blank and select Don't remind me again in the modal Publish page")
    public void saveAndPublishNewProductCollectionPageFromBlank_selectDontRemindPublishPage(PageType pageType) {
        assert new ArrayList<>(Arrays.asList(PageType.PRODUCT, PageType.COLLECTION)).contains(pageType);

        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishProductCollectionPageModal_TitledTitle();

        if (pageType == PageType.PRODUCT) {
            getEditorPage().selectDontRemindAndConfirmPublishProductModal();
        } else {
            getEditorPage().selectDontRemindAndConfirmPublishCollectionModal();
        }

        getEditorPage().verifyShowPublishingPageToast();
        getEditorPage().verifyShowPublishedPageToast();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().verifyPageIsPublished();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Step("Save and Publish new {0} page from blank when user have selected Don't remind me again in the modal Publish page")
    public void saveAndPublishNewProductCollectionPageFromBlank_haveSelectedDontRemindPublishPage(PageType pageType) {
        assert new ArrayList<>(Arrays.asList(PageType.PRODUCT, PageType.COLLECTION)).contains(pageType);

        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishProductCollectionPageModal_TitledTitle();
        getEditorPage().verifyShowPublishingPageToast();
        getEditorPage().verifyShowPublishedPageToast();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().verifyPageIsPublished();
        getEditorPage().closeEnableAutoSaveModal();
    }

    @Feature("Don't remind me again")
    @Test(groups = {"Select Don't remind me again"}, description = "TC-019: User select option \"Don't remind me again\" in the modal \"Publish page\" for HOME page")
    public void dontRemindPublishHomePage() {
        saveAndPublishNewHomePageFromBlank_selectDontRemindPublishPage();
        saveAndPublishNewHomePageFromBlank_haveSelectedDontRemindPublishPage();
    }

    @Feature("Don't remind me again")
    @Test(groups = {"Select Don't remind me again"}, description = "TC-019: User select option \"Don't remind me again\" in the modal \"Publish page\" for PRODUCT page")
    public void dontRemindPublishProductPage() {
        saveAndPublishNewProductCollectionPageFromBlank_selectDontRemindPublishPage(PageType.PRODUCT);
        saveAndPublishNewProductCollectionPageFromBlank_haveSelectedDontRemindPublishPage(PageType.PRODUCT);
    }

    @Feature("Don't remind me again")
    @Test(groups = {"Select Don't remind me again"}, description = "TC-019: User select option \"Don't remind me again\" in the modal \"Publish page\" for COLLECTION page")
    public void dontRemindPublishCollectionPage() {
        saveAndPublishNewProductCollectionPageFromBlank_selectDontRemindPublishPage(PageType.COLLECTION);
        saveAndPublishNewProductCollectionPageFromBlank_haveSelectedDontRemindPublishPage(PageType.COLLECTION);
    }

    @Step("Save and Publish new {0} page from blank when user have selected Don't remind me again in the modal Enable autosave")
    public void saveAndPublishNewPageFromBlank_haveSelectedDontRemindEnableAutoSave(PageType pageType) {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(pageType);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + pageType.name() + " " + new Date());
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishPageModal_TitledTitle();
        getToast().verifyShowPublishedPageToast();
        getEditorPage().verifyPageSavedAndPublished();
        verifyElementNotVisible(modal);
    }

    @Feature("Don't remind me again")
    @Test(groups = {"Select Don't remind me again"}, description = "TC-020: User select option \"Don't remind me again\" in the modal \"Enable autosave?\"")
    public void dontRemindEnableAutoSave() {
        saveNewPageFromBlank_selectDontRemindEnableAutoSave();
        saveAndPublishNewPageFromBlank_haveSelectedDontRemindEnableAutoSave(PageType.PAGE);
        saveAndPublishNewPageFromBlank_haveSelectedDontRemindEnableAutoSave(PageType.PASSWORD);
    }

    @Feature("Leave page")
    @Test(description = "TC-021: User back from editor to page listing screen by click Back button in the Editor header when page is not saved")
    public void backFromEditorToPageListingScreen_clickBackButton() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.PAGE.name() + " " + new Date());
        getEditorPage().backToPageListingScreen();
        getEditorPage().confirmLeavePageModal();
        getPageListingScreen().verifyPageListingLoaded();
    }

    @Feature("Leave page")
    @Test(description = "TC-022: User back from editor to page listing screen by click Back button in the browser navigator when page is not saved")
    public void backFromEditorToPageListingScreen_navigateBack() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.PAGE.name() + " " + new Date());
        navigateBack();
        waitForPageLoaded();
        switchToPageFlyFrame();
        getPageListingScreen().verifyPageListingLoaded();
    }

    @Feature("Leave page")
    @Test(description = "TC-023: User back from editor to page listing screen by click Back button in the Editor header when page is saved manually")
    public void backFromEditorToPageListingScreen_clickBackButton2() throws IOException {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().hidePageOutline();

        ImageUtils imageCompare = new ImageUtils();
        imageCompare.setImgA(getEditorPage().takeDndCanvasScreenshot("editor-after-save"));
        String id = getParamFromUrl("id");

        String testPageTitle = "Test " + PageType.PAGE.name() + " " + new Date();
        getEditorPage().changePageTitle(testPageTitle);
        getEditorPage().clickSavePageButton();
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();

        getEditorPage().backToPageListingScreen();
        getPageListingScreen().verifyPageListingLoaded();

        getPageListingScreen().openPageInPageListing(id);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().hidePageOutline();

        imageCompare.setImgB(getEditorPage().takeDndCanvasScreenshot("editor-after-reopen"));
        verifyTrue(imageCompare.isImagesEqual());
    }

    @Feature("Leave page")
    @Test(description = "TC-024: User back from editor to page listing screen by click Back button in Editor header when autosave is enabled")
    public void backFromEditorToPageListingScreen_clickBackButton3() throws IOException {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().hidePageOutline();

        ImageUtils imageCompare = new ImageUtils();
        imageCompare.setImgA(getEditorPage().takeDndCanvasScreenshot("editor-after-save"));
        String id = getParamFromUrl("id");

        String testPageTitle = "Test " + PageType.PAGE.name() + " " + new Date();
        getEditorPage().changePageTitle(testPageTitle);
        getEditorPage().toggleEnableAutoSave();
        getEditorPage().confirmBeforeSaveModal_TitledTitle();
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();

        getEditorPage().backToPageListingScreen();
        getPageListingScreen().verifyPageListingLoaded();

        getPageListingScreen().openPageInPageListing(id);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().hidePageOutline();

        imageCompare.setImgB(getEditorPage().takeDndCanvasScreenshot("editor-after-reopen"));
        verifyTrue(imageCompare.isImagesEqual());
    }

//    @Test(description = "TC-032: User show page outline")
//    public void showPageOutline() {
//        getEditorPage().openNewPageEditor(PageType.PAGE);
//        getEditorPage().verifyEditorPageLoaded();
//        getEditorPage().togglePageOutlineAndCheckCanvasSize();
//    }

    @Feature("Editor settings")
    @Test(description = "TC-038: User select option \"Show Canvas size\" and \"Fit viewport\" then close editor then open editor again")
    public void showCanvasSizeAndFitViewport() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().toggleShowCanvasSize();
        getEditorPage().toggleFitViewport();
        // Save page
        getEditorPage().clickSavePageButton();
        getEditorPage().confirmBeforeSaveModal_UntitledTitle(PageType.PAGE + " " + new Date().toString());
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
        String pageId = getParamFromUrl("id");
        System.out.println("Page ID: " + pageId);
        // Close editor
        getEditorPage().backToPageListingScreen();
        getPageListingScreen().verifyPageListingLoaded();
        // Reopen page
        getPageListingScreen().openPageInPageListing(pageId);
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(pageId);

        getEditorPage().verifyShowCanvasSize();
        getEditorPage().verifyFitViewport();
    }

    @Feature("Editor settings")
    @Test(description = "TC-049: User enable autosave when haven't saved page")
    public void enableAutoSaveWhenHaventSavedPage() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().toggleEnableAutoSave();
        getEditorPage().confirmBeforeSaveModal_UntitledTitle(PageType.PAGE + " " + new Date().toString());
        getEditorPage().confirmSavePageModal();
    }

    @Feature("Editor settings")
    @Test(description = "TC-050: User enable autosave when have saved page")
    public void enableAutoSaveWhenHaveSavedPage() {
        // Create a new page and save
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test " + PageType.PAGE.name() + " " + new Date());
        getEditorPage().clickSavePageButton();
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
        // Enable autosave
        getEditorPage().toggleEnableAutoSave();
        getEditorPage().verifyShowEnableAutoSaveToast();
    }

    @Test(description = "TC-057: User unpublished page")
    public void unpublishedPage() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPublishedPage();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().clickUnpublishPageButton();
        getEditorPage().verifyShowUnpublishingPageToast();
        getEditorPage().verifyShowUnpublishedPageToast();
        getEditorPage().verifyPageIsUnpublished();
    }

    @Feature("Editor settings")
    @Test(description = "TC-062: User go to theme editor when page is unpublished")
    public void goToThemeEditorWhenPageIsUnpublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openUnpublishedPage();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().verifyPageIsUnpublished();
        getEditorPage().verifyGoToThemeEditorButtonDisabled();
    }

    @Feature("Editor settings")
    @Test(description = "TC-063: User go to theme editor when page is published")
    public void goToThemeEditorWhenPageIsPublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPublishedPage();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().verifyPageIsPublished();
        getEditorPage().verifyGoToThemeEditorButtonEnabled();
        getEditorPage().goToThemeEditor();
    }

    @Feature("Editor helpers")
    @Test(description = "TC-067: User open Browser help center")
    public void openBrowserHelpCenter() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().openBrowserHelpCenter();
    }

    @Feature("Editor helpers")
    @Test(description = "TC-068: User open Live chat box")
    public void openLiveChatBox() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().startLiveChat();
        attachScreenshotPNG();
    }

    @Feature("Editor helpers")
    @Test(description = "TC-069: User Join PageFly community")
    public void joinPageFlyCommunity() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().joinPageFlyCommunity();
    }

    @Feature("Editor helpers")
    @Test(description = "TC-070: User Watch video tutorials")
    public void watchVideoTutorials() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().watchVideoTutorials();
    }

    @Feature("Screen size")
    @Test(description = "TC-071: User change window width to less than 1024px")
    public void changeWindowWidthToLessThan1024px() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        resizeWindow(1023);
        getEditorPage().verifyOverlayVisible();
    }

    @Step("User open editor on mobile screen has width {0}")
    public void openEditorOnScreenHasWidth(Integer width) {
        getPageListingScreen().openPageListingPage();
        resizeWindow(width);
        try {
            getPageListingScreen().openPageInPageListingMobile(1);
        } catch (Exception e) {
            getPageListingScreen().openPageInPageListing(1);
        }
        getPageListingScreen().verifyEditorUnavailableOnMobileModal();
    }

    @Feature("Screen size")
    @Test(description = "TC-072: User use device has width less than 1024px")
    public void useDeviceHasWidthLessThan1024px() {
        openEditorOnScreenHasWidth(900);
        openEditorOnScreenHasWidth(1023);
    }

    @Feature("Editor helpers")
    @Test(description = "TC-118: User open live chat")
    public void openLiveChat() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().openLiveChat();
    }

    @Feature("Inspector")
    @Test(description = "INS-001: Change heading content color to random color and verify the color value")
    public void changeContentColorToRandomColor() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeContentColor();
        getEditorPage().changeContentColor();
        getEditorPage().changeContentColor();
    }

    @Feature("Inspector")
    @Test(description = "INS-002: Change padding value of heading element and verify the padding value")
    public void changePaddingValueOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changePaddingValue("left", 30);
        getEditorPage().changePaddingValue("right", 40);
        getEditorPage().changePaddingValue("bottom", 50);
        getEditorPage().changePaddingValue("top", 20);
        getEditorPage().changePaddingValue(10);
    }

    @Feature("Inspector")
    @Test(description = "INS-003: Change margin value of heading element and verify the margin value")
    public void changeMarginValueOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeMarginValue("left", 30);
        getEditorPage().changeMarginValue("right", 40);
        getEditorPage().changeMarginValue("bottom", 50);
        getEditorPage().changeMarginValue("top", 20);
        getEditorPage().changeMarginValue(10);
    }

    @Feature("Inspector")
    @Test(description = "INS-004: Change font family of heading element and verify the font family value")
    public void changeFontFamilyOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeFontFamily("Abel");
        getEditorPage().changeFontFamily("Actor");
    }

    @Feature("Inspector")
    @Test(description = "INS-005: Change font size of heading element by slider and verify the font size value")
    public void changeFontSizeOfHeadingElementBySlider() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeFontSize_Slide(10);
        getEditorPage().changeFontSize_Slide(20);
        getEditorPage().changeFontSize_Slide(30);
        getEditorPage().changeFontSize_Slide(40);
        getEditorPage().changeFontSize_Slide(50);
    }

    @Feature("Inspector")
    @Test(description = "INS-005: Change font size of heading element by input and verify the font size value")
    public void changeFontSizeOfHeadingElementByInput() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeFontSize_Input("10");
        getEditorPage().changeFontSize_Input("40");
        getEditorPage().changeFontSize_Input("20");
        getEditorPage().changeFontSize_Input("30");
        getEditorPage().changeFontSize_Input("50");
    }

    @Feature("Inspector")
    @Test(description = "INS-006: Change text alignment of heading element and verify the text alignment value")
    public void changeTextAlignmentOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeTextAlignment("left");
        getEditorPage().changeTextAlignment("center");
        getEditorPage().changeTextAlignment("right");
    }

    @Feature("Inspector")
    @Test(description = "INS-007: Change text decoration of heading element and verify the text decoration value")
    public void changeTextDecorationOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeTextDecoration("underline");
        getEditorPage().changeTextDecoration("line-through");
        getEditorPage().toggleOffTextDecoration("line-through");
    }

    @Feature("Inspector")
    @Test(description = "INS-008: Change text style of heading element to italic and verify the text style value")
    public void changeTextStyleOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeTextStyleItalic("italic");
        getEditorPage().toggleOffTextStyleItalic("italic");
        getEditorPage().changeTextStyleItalic("italic");
        getEditorPage().toggleOffTextStyleItalic("italic");

    }

    @Feature("Inspector")
    @Test(description = "INS-008: Change text style of heading element to bold and verify the text style value")
    public void changeFontWeightOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropParagraphElement();
        getEditorPage().changeTextStyleBold("bold");
        getEditorPage().toggleOffTextStyleBold("bold");
    }

    @Feature("Inspector")
    @Test(description = "INS-009: Change font weight of heading element and verify the font weight value")
    public void changeFontWeightOfHeadingElement2() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeFontWeight("100");
        getEditorPage().changeFontWeight("200");
        getEditorPage().changeFontWeight("300");
        getEditorPage().changeFontWeight("400");
        getEditorPage().changeFontWeight("500");
        getEditorPage().changeFontWeight("600");
        getEditorPage().changeFontWeight("700");
        getEditorPage().changeFontWeight("800");
        getEditorPage().changeFontWeight("900");
    }

    @Feature("Inspector")
    @Test(description = "INS-010: Change line height of paragraph element and verify the line height value")
    public void changeLineHeightOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropParagraphElement();
        getEditorPage().changeLineHeightByInput("10");
        getEditorPage().changeLineHeightBySlider("40");
        getEditorPage().changeLineHeightByInput("20");
        getEditorPage().changeLineHeightByInput("30");
        getEditorPage().changeLineHeightBySlider("50");
    }

    @Feature("Inspector")
    @Test(description = "INS-011: Change letter spacing of heading element and verify the letter spacing value")
    public void changeLetterSpacingOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeLetterSpacingByInput("10");
        getEditorPage().changeLetterSpacingBySlider("40");
        getEditorPage().changeLetterSpacingByInput("-20");
        getEditorPage().changeLetterSpacingByInput("-10");
        getEditorPage().changeLetterSpacingBySlider("10");
    }

    @Feature("Inspector")
    @Test(description = "INS-012: Change text transform of heading element and verify the text transform value")
    public void changeTextTransformOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeTextTransform("uppercase");
        getEditorPage().changeTextTransform("capitalize");
        getEditorPage().toggleOffTextTransform("capitalize");
    }

    @Feature("Inspector")
    @Test(description = "INS-013: Change background color of heading element and verify the background color value")
    public void changeBackgroundColorOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeBackgroundColor();
        getEditorPage().changeBackgroundColor();
        getEditorPage().changeBackgroundColor();
        getEditorPage().changeBackgroundColor("rgba(217, 245, 84, 0.51)");
        getEditorPage().changeBackgroundColor("rgb(255, 128, 232)");
    }

    @Feature("Inspector")
    @Test(description = "INS-014: Change border style of heading element and verify the border style value")
    public void changeBorderStyleOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeBorderStyle("solid");
        getEditorPage().changeBorderStyle("none");
        getEditorPage().changeBorderStyle("dotted");
        getEditorPage().changeBorderStyle("dashed");
        getEditorPage().toggleOffBorderStyle("dashed");
    }

    @Feature("Inspector")
    @Test(description = "INS-018: Change display style of heading element and verify the display style value")
    public void changeDisplayStyleOfHeadingElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PASSWORD);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropHeadingElement();
        getEditorPage().changeBorderStyle("solid");
        getEditorPage().changeDisplayStyle("block", "block");
        getEditorPage().changeDisplayStyle("inline", "inline-block");
        getEditorPage().changeDisplayStyle("inline", "block");
    }

    @Feature("Inspector")
    @Test(description = "INS-019: Change opacity of paragraph element and verify the opacity value")
    public void changeOpacityOfParagraphElement() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropParagraphElement();
        getEditorPage().changeOpacity_Slide(10);
        getEditorPage().changeOpacity_Input("19");
        getEditorPage().changeOpacity_Slide(20);
        getEditorPage().changeOpacity_Input("30");
        getEditorPage().changeOpacity_Input("40");
        getEditorPage().changeOpacity_Slide(100);
        getEditorPage().changeOpacity_Input("101");
    }

    @Feature("Element")
    @Story("Layout element")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://docs.google.com/spreadsheets/d/1HgNIFwDdQ5k2HB1x2pfV_KnBaWvQLi6aGy7W44yXUFs/edit?pli=1#gid=855623679&range=B87")
    @AllureId("TC-084")
    @Test(description = "TC-084: User use Layout element and set Column style")
    public void useLayoutElementAndSetColumnStyle() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().dragAndDropLayoutElement();
        getEditorPageSandbox().selectElement("Column");
        getEditorPage().changeColumnHeightInput("300");
        getEditorPage().addNewItemToList();
        getEditorPage().removeItemFromList();
        getEditorPage().dragAndDropElementToSandbox(By.id("catalog--add-element-btn"), By.id("catalog--catalog-list--heading"), By.xpath("//div[contains(@class, 'pf-selection')]"));
        sleep(3);
        System.out.println(getSelectedElementType());
        System.out.println(ElementType.HEADING.getType());
        verifyTrue(Objects.equals(getSelectedElementType(), ElementType.HEADING.getType()), "Selected element type is not HEADING");
        getEditorPageSandbox().selectElement("Row");
        for (int i = 0; i < 12; i++) {
            getEditorPage().addNewItemToList();
        }
        getEditorPage().changePageTitle("Test " + PageType.PAGE.name() + " " + new Date());
        saveAndPublishPageSuccessfully();
    }


    @AfterMethod
    public void resetDontRemindAfterTest(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        for (String group : groups) {
            if (group.equals("Select Don't remind me again")) {
                resetDontRemind();
                break;
            }
        }
    }

    @AfterClass
    public void teardown() {

    }
}
