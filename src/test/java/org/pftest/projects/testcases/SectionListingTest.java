package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.pftest.base.BaseTest;
import org.testng.annotations.Test;

import static org.pftest.keywords.WebUI.saveAndPublishPageByShortcut;

@Epic("Page Editing")
public class SectionListingTest extends BaseTest {

    @Step("Create a new section from blank and save and publish successfully")
    public void createSectionFromBlankAndSaveAndPublish() {
        getSectionListingScreen().openSectionListingPage();
        getSectionListingScreen().verifySectionListingLoaded();
        getSectionListingScreen().createSectionFromBlank();
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishSectionModal_UntitledTitle();
        getToast().verifyShowPublishingSectionToast();
        getToast().verifyShowPublishedSectionToast();
        getEditorPage().closePublishedSectionModal();
        getEditorPage().closeEnableAutoSaveModal();

        // Verify the section is published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Step("Create a new section from template and save and publish successfully")
    public void createSectionFromTemplateAndSaveAndPublish() {
        getSectionListingScreen().openSectionListingPage();
        getSectionListingScreen().verifySectionListingLoaded();
        getSectionListingScreen().createSectionFromTemplate();
        getEditorPage().verifyEditorPageLoaded();
        saveAndPublishPageByShortcut();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishSectionModal_UntitledTitle();
        getToast().verifyShowPublishingSectionToast();
        getToast().verifyShowPublishedSectionToast();
        getEditorPage().closePublishedSectionModal();
        getEditorPage().closeEnableAutoSaveModal();

        // Verify the section is published
        getEditorPage().verifyPageSavedAndPublished();
    }

    @Test(description = "TC-017: Create a new section from blank")
    public void createSectionFromBlank() {
        createSectionFromBlankAndSaveAndPublish();
    }

    @Test(description = "TC-018: Create a new section from template")
    public void createSectionFromTemplate() {
        createSectionFromTemplateAndSaveAndPublish();
    }
}
