package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.pftest.keywords.WebUI.*;

@Epic("Page Editing")
public class PageEditingTest extends BaseTest {
    @BeforeClass
    public void setup() {

    }

    @Test(description = "TC-001: Go to page listing screen, click on page title in the data table, and verify the page title is displayed in the editor")
    public void verifyPageTitleIsDisplayedInEditor() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        String id = getPageListingScreen().openPageInPageListing();
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(id);
    }

    @Test(description = "TC-003: Go to page listing screen, click on Setting button in the row, then click on 'Edit page' button, and verify the page title is displayed in the editor")
    public void verifyPageTitleIsDisplayedInEditor2() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        String id = getPageListingScreen().openPageSettingInPageListing();
        getPageSettingScreen().verifyPageSettingScreenLoaded();
        verifyIdInUrl(id);
        getPageSettingScreen().clickEditPageButton();
        getEditorPage().verifyEditorPageLoaded();
        verifyIdInUrl(id);
    }

    @Step("Save and Publish page successfully")
    public void saveAndPublishPageSuccessfully() {
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishModal_TitledTitle();
        getToast().verifyShowPublishedToast();
        getEditorPage().closeEnableAutoSaveModal();
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new {0} page from blank")
    public void createNewPageFromBlank (PageType pageType) {
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
        getEditorPage().confirmBeforePublishModal_TitledTitle();
        // 4. Hiển thị toast "Publishing page..."
//        getToast().verifyShowPublishingToast();
        // 4. Hiển thị toast "Published"
        getToast().verifyShowPublishedToast();
        // 4. Nếu chưa remind modal Auto Save thì sẽ hiển thị modal "Enable autosave?"
        getEditorPage().closeEnableAutoSaveModal();
        // 4. Badge save: Saved
        // 4. Badge publish: Published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new {0} page from template")
    public void createNewPageFromTemplate (PageType pageType) {
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
        getEditorPage().confirmBeforePublishModal_TitledTitle();
        // 4. Hiển thị toast "Publishing page..."
//        getToast().verifyShowPublishingToast();
        // 4. Hiển thị toast "Published"
        getToast().verifyShowPublishedToast();
        // 4. Nếu chưa remind modal Auto Save thì sẽ hiển thị modal "Enable autosave?"
        getEditorPage().closeEnableAutoSaveModal();
        // 4. Badge save: Saved
        // 4. Badge publish: Published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Test(description = "TC-009: Create a new blog post page from blank")
    public void createNewBlogPostPageFromBlank() {
        createNewPageFromBlank(PageType.BLOG);
    }

    @Test(description = "TC-010: Create a new blog post page from template")
    public void createNewBlogPostPageFromTemplate() {
        createNewPageFromTemplate(PageType.BLOG);
    }

    @Test(description = "TC-011: Create a new regular/password page from blank")
    public void createNewRegularPasswordPageFromBlank() {
        createNewPageFromBlank(PageType.PAGE);
        createNewPageFromBlank(PageType.PASSWORD);
    }

    @Test(description = "TC-012: Create a new regular/password page from template")
    public void createNewRegularPasswordPageFromTemplate() {
        createNewPageFromTemplate(PageType.PAGE);
        createNewPageFromTemplate(PageType.PASSWORD);
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
        getEditorPage().confirmSaveModal();
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
        getEditorPage().confirmSaveModal();
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
        getEditorPage().confirmSaveModal();
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
        getEditorPage().confirmSaveModal();
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
        getEditorPage().verifyShowUnpublishingToast();
        getEditorPage().verifyShowUnpublishedToast();
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

    @AfterClass
    public void teardown() {

    }
}
