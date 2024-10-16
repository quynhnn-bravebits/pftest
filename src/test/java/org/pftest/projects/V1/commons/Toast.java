package org.pftest.projects.V1.commons;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class Toast {
    private By savingPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Saving page...\"]");
    private By savedPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page saved\"]");
    private By publishingPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Publishing page...\"]");
    private By publishedPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page published\"]");
    private By unpublishingPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Unpublishing page...\"]");
    private By unpublishedPageToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page unpublished\"]");

    private By publishingSectionToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Publishing section...\"]");
    private By publishedSectionToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Section published\"]");

    private By enableAutoSaveToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Auto-save enabled\"]");
    private By disableAutoSaveToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Auto-save disabled\"]");

    @Step("Verify show 'Duplicating page...' toast")
    public void verifyShowDuplicatingPageToast() {
        verifyShowToast("Duplicating page...");
    }

    @Step("Verify show 'Page duplicated' toast")
    public void verifyShowDuplicatedPageToast() {
        waitForToast("Page duplicated", 30);
    }

    @Step("Verify show 'Page exported' toast")
    public void verifyShowExportedPageToast() {
        waitForToast("Page exported", 30);
    }

    @Step("Verify show 'Exporting pages...' toast")
    public void verifyShowExportingPagesToast() {
        verifyShowToast("Exporting pages");
    }

    @Step("Verify show 'Pages exported' toast")
    public void verifyShowExportedPagesToast() {
        waitForToast("Pages exported", 30);
    }

    @Step("Verify show 'Importing page...' toast")
    public void verifyShowImportingPageToast() {
        verifyShowToast("Importing page...");
    }

    @Step("Verify show 'Page imported' toast")
    public void verifyShowImportedPageToast() {
        waitForToast("Page imported", 30);
    }

    @Step("Verify show 'Importing pages...' toast")
    public void verifyShowImportingPagesToast() {
        verifyShowToast("Importing pages...");
    }

    @Step("Verify show 'Pages imported' toast")
    public void verifyShowImportedPagesToast() {
        waitForToast("Pages imported", 30);
    }

    @Step("Verify show 'Deleting page...' toast")
    public void verifyShowDeletingPageToast() {
        verifyShowToast("Deleting page...");
    }

    @Step("Verify show 'Page deleted' toast")
    public void verifyShowDeletedPageToast() {
        waitForToast("Page deleted", 30);
    }

    @Step("Verify show 'Deleting pages...' toast")
    public void verifyShowDeletingPagesToast() {
        verifyShowToast("Deleting pages...");
    }

    @Step("Verify show 'Pages deleted' toast")
    public void verifyShowDeletedPagesToast() {
        waitForToast("Pages deleted", 30);
    }

    @Step("Verify show 'Publishing page...' toast")
    public void verifyShowPublishingPageToast() {
        verifyElementVisible(publishingPageToast);
    }

    @Step("Verify show 'Page published' toast")
    public void verifyShowPublishedPageToast() {
        verifyElementVisible(publishedPageToast, 30);
    }

    @Step("Verify show 'Unpublishing page...' toast")
    public void verifyShowUnpublishingPageToast() {
        verifyShowToast("Unpublishing page...");
    }

    @Step("Verify show 'Page unpublished' toast")
    public void verifyShowUnpublishedPageToast() {
        waitForToast("Page unpublished", 30);
    }

    @Step("Verify show 'Publishing pages...' toast")
    public void verifyShowPublishingPagesToast() {
        verifyShowToast("Publishing pages...");
    }

    @Step("Verify show 'Pages published' toast")
    public void verifyShowPublishedPagesToast() {
        waitForToast("Pages published", 30);
    }

    @Step("Verify show 'Unpublishing pages...' toast")
    public void verifyShowUnpublishingPagesToast() {
        verifyShowToast("Unpublishing pages...");
    }

    @Step("Verify show 'Pages unpublished' toast")
    public void verifyShowUnpublishedPagesToast() {
        waitForToast("Pages unpublished", 30);
    }

    @Step("Verify show 'Saving page...' toast")
    public void verifyShowSavingPageToast() {
        verifyElementVisible(savingPageToast);
    }

    @Step("Verify show 'Page saved' toast")
    public void verifyShowSavedPageToast() {
        verifyElementVisible(savedPageToast, 30);
    }

    @Step("Verify show 'Publishing section...' toast")
    public void verifyShowPublishingSectionToast() {
        verifyElementVisible(publishingSectionToast);
    }

    @Step("Verify show 'Section published' toast")
    public void verifyShowPublishedSectionToast() {
        verifyElementVisible(publishedSectionToast, 30);
    }

    @Step("Verify show 'Publishing sections...' toast")
    public void verifyShowPublishingSectionsToast() {
        verifyShowToast("Publishing sections...");
    }

    @Step("Verify show 'Sections published' toast")
    public void verifyShowPublishedSectionsToast() {
        waitForToast("Sections published", 30);
    }

    @Step("Verify show 'Deleting sections...' toast")
    public void verifyShowDeletingSectionsToast() {
        verifyShowToast("Deleting sections...");
    }

    @Step("Verify show 'Sections deleted' toast")
    public void verifyShowDeletedSectionsToast() {
        waitForToast("Sections deleted", 30);
    }

    @Step("Verify show 'Auto-save enabled' toast")
    public void verifyShowEnableAutoSaveToast() {
        switchToDefaultContent();
        verifyElementVisible(enableAutoSaveToast);
        switchToPageFlyFrame();
    }

    @Step("Verify show 'Restored version' toast")
    public void verifyShowRestoredVersionToast() {
        waitForToast("Restored version", 30);
    }

    @Step("Verify show 'Auto-save disabled' toast")
    public void verifyShowDisableAutoSaveToast() {
        switchToDefaultContent();
        verifyElementVisible(disableAutoSaveToast);
        switchToPageFlyFrame();
    }

    @Step("Verify show '{0}' toast")
    static public void verifyShowToast(String toast) {
        By toastElement = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"" + toast + "\"]");
        verifyElementVisible(toastElement);
    }

    @Step("Wait for '{0}' toast")
    static public void waitForToast(String toast, int timeout) {
        By toastElement = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"" + toast + "\"]");
        waitForElementVisible(toastElement, timeout);
    }
}