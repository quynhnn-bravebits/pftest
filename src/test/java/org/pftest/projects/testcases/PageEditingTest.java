package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.pftest.keywords.WebUI.*;

@Epic("Page Editing")
public class PageEditingTest extends BaseTest {
    @BeforeClass
    public void setup() {

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

    @Test(description = "TC-009: Create a new blog post page from blank")
    public void createNewBlogPostPageFromBlank() {
        saveAndPublishNewPageFromBlank(PageType.BLOG);
    }

    @Test(description = "TC-010: Create a new blog post page from template")
    public void createNewBlogPostPageFromTemplate() {
        saveAndPublishNewPageFromTemplate(PageType.BLOG);
    }

    @Test(description = "TC-011: Create a new regular/password page from blank")
    public void createNewRegularPasswordPageFromBlank() {
        saveAndPublishNewPageFromBlank(PageType.PAGE);
        saveAndPublishNewPageFromBlank(PageType.PASSWORD);
    }

    @Test(description = "TC-012: Create a new regular/password page from template")
    public void createNewRegularPasswordPageFromTemplate() {
        saveAndPublishNewPageFromTemplate(PageType.PAGE);
        saveAndPublishNewPageFromTemplate(PageType.PASSWORD);
    }

    @Test(description = "TC-013: Create a new home page from blank when there is no home page is published")
    public void createNewHomePageFromBlankWhenNoHomePagePublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().verifyHaventPublishedHomepage();
        saveAndPublishNewHomePageFromBlank();
    }

    @Test(description = "TC-014: Create a new home page from blank when there is a home page is published", dependsOnMethods = {"createNewHomePageFromBlankWhenNoHomePagePublished"})
    public void createNewHomePageFromBlankWhenHomepageIsPublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().verifyHavePublishedHomepage();
        saveAndPublishNewHomePageFromBlank();
    }

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

    @Test(groups = "Select Don't remind me again", description = "TC-018:  User select option \"Don't remind me again\" in the modal \"Save page\"")
    public void dontRemindSavePage() {
        saveNewPageFromBlank_selectDontRemindSavePage(PageType.PAGE);
        saveNewPageFromBlank_haveSelectedDontRemindSavePage(PageType.PAGE);
        saveNewPageFromBlank_selectDontRemindSavePage(PageType.PASSWORD);
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

    @Test(groups = {"Select Don't remind me again"}, description = "TC-019: User select option \"Don't remind me again\" in the modal \"Publish page\" for HOME page")
    public void dontRemindPublishHomePage() {
        saveAndPublishNewHomePageFromBlank_selectDontRemindPublishPage();
        saveAndPublishNewHomePageFromBlank_haveSelectedDontRemindPublishPage();
    }

    @Test(groups = {"Select Don't remind me again"}, description = "TC-019: User select option \"Don't remind me again\" in the modal \"Publish page\" for PRODUCT page")
    public void dontRemindPublishProductPage() {
        saveAndPublishNewProductCollectionPageFromBlank_selectDontRemindPublishPage(PageType.PRODUCT);
        saveAndPublishNewProductCollectionPageFromBlank_haveSelectedDontRemindPublishPage(PageType.PRODUCT);
    }

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

    @Test(groups = {"Select Don't remind me again"}, description = "TC-020: User select option \"Don't remind me again\" in the modal \"Enable autosave?\"")
    public void dontRemindEnableAutoSave() {
        saveNewPageFromBlank_selectDontRemindEnableAutoSave();
        saveAndPublishNewPageFromBlank_haveSelectedDontRemindEnableAutoSave(PageType.PAGE);
        saveAndPublishNewPageFromBlank_haveSelectedDontRemindEnableAutoSave(PageType.PASSWORD);
    }

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

    @Test(description = "TC-023: User back from editor to page listing screen by click Back button in the Editor header when page is saved manually")
    public void backFromEditorToPageListingScreen_clickBackButton2() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        String testPageTitle = "Test " + PageType.PAGE.name() + " " + new Date();
        getEditorPage().changePageTitle(testPageTitle);
        getEditorPage().clickSavePageButton();
        getEditorPage().confirmSavePageModal();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().verifyPageIsSaved();
        getEditorPage().closeEnableAutoSaveModal();
        getEditorPage().backToPageListingScreen();
        getPageListingScreen().verifyPageListingLoaded();
    }

//    @Test(description = "TC-032: User show page outline")
//    public void showPageOutline() {
//        getEditorPage().openNewPageEditor(PageType.PAGE);
//        getEditorPage().verifyEditorPageLoaded();
//        getEditorPage().togglePageOutlineAndCheckCanvasSize();
//    }

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

    @Test(description = "TC-062: User go to theme editor when page is unpublished")
    public void goToThemeEditorWhenPageIsUnpublished() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openUnpublishedPage();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().verifyPageIsUnpublished();
        getEditorPage().verifyGoToThemeEditorButtonDisabled();
    }

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

    @Test(description = "TC-067: User open Browser help center")
    public void openBrowserHelpCenter() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().openBrowserHelpCenter();
    }

    @Test(description = "TC-068: User open Live chat box")
    public void openLiveChatBox() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().startLiveChat();
        attachScreenshotPNG();
    }

    @Test(description = "TC-069: User Join PageFly community")
    public void joinPageFlyCommunity() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().joinPageFlyCommunity();
    }

    @Test(description = "TC-070: User Watch video tutorials")
    public void watchVideoTutorials() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().watchVideoTutorials();
    }

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

    @Test(description = "TC-072: User use device has width less than 1024px")
    public void useDeviceHasWidthLessThan1024px() {
        openEditorOnScreenHasWidth(900);
        openEditorOnScreenHasWidth(1023);
    }

    @Test(description = "TC-118: User open live chat")
    public void openLiveChat() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().openLiveChat();
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
