package org.pftest.projects.V2.components.toast;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.keywords.WebUI.switchToPageFlyFrame;

public class Toast {

    private static By selector(String text) {
        return By.xpath("//*[@id='PolarisPortalsContainer']//*[@class='Polaris-Frame-Toast']//*[text()='" + text + "']");
    }

    @Step("Verify show '{0}' toast")
    static public void verifyShowToast(String toast) {
        By toastElement = selector(toast);
        verifyElementVisible(toastElement);
    }

    @Step("Wait for '{0}' toast")
    static public void waitForToast(String toast) {
        By toastElement = selector(toast);
        int timeout = 30;
        waitForElementVisible(toastElement, timeout);
    }

    @Step("Verify show 'Duplicating page...' toast")
    public void verifyShowDuplicatingPageToast() {
        verifyShowToast("Duplicating page...");
    }

    @Step("Verify show 'Page duplicated' toast")
    public void verifyShowDuplicatedPageToast() {
        waitForToast("Page duplicated");
    }

    @Step("Verify show 'Deleting page...' toast")
    public void verifyShowDeletingPageToast() {
        verifyShowToast("Deleting page...");
    }

    @Step("Verify show 'Page exported' toast")
    public void verifyShowExportedPageToast() {
        waitForToast("Page exported");
    }

    @Step("Verify show 'Exporting pages...' toast")
    public void verifyShowExportingPagesToast() {
        verifyShowToast("Exporting pages");
    }

    @Step("Verify show 'Pages exported' toast")
    public void verifyShowExportedPagesToast() {
        waitForToast("Pages exported");
    }

    @Step("Verify show 'Importing page...' toast")
    public void verifyShowImportingPageToast() {
        verifyShowToast("Importing page...");
    }

    @Step("Verify show 'Page imported' toast")
    public void verifyShowImportedPageToast() {
        waitForToast("Page imported");
    }

    @Step("Verify show 'Importing pages...' toast")
    public void verifyShowImportingPagesToast() {
        verifyShowToast("Importing pages...");
    }

    @Step("Verify show 'Pages imported' toast")
    public void verifyShowImportedPagesToast() {
        waitForToast("Pages imported");
    }

    @Step("Verify show 'Page deleted' toast")
    public void verifyShowDeletedPageToast() {
        waitForToast("Page deleted");
    }

    @Step("Verify show 'Publishing page...' toast")
    public void verifyShowPublishingPageToast() {
        verifyShowToast("Publishing page...");
    }

    @Step("Verify show 'Page published' toast")
    public void verifyShowPublishedPageToast() {
        waitForToast("Page published");
    }

    @Step("Verify show 'Unpublishing page...' toast")
    public void verifyShowUnpublishingPageToast() {
        verifyShowToast("Unpublishing page...");
    }

    @Step("Verify show 'Page unpublished' toast")
    public void verifyShowUnpublishedPageToast() {
        waitForToast("Page unpublished");
    }

    @Step("Verify show 'Unpublishing pages...' toast")
    public void verifyShowUnpublishingPagesToast() {
        verifyShowToast("Unpublishing pages...");
    }

    @Step("Verify show 'Pages unpublished' toast")
    public void verifyShowUnpublishedPagesToast() {
        waitForToast("Pages unpublished");
    }

    @Step("Verify show 'Publishing section...' toast")
    public void verifyShowPublishingSectionToast() {
        verifyShowToast("Publishing section...");
    }

    @Step("Verify show 'Section published' toast")
    public void verifyShowPublishedSectionToast() {
        waitForToast("Section published");
    }

    @Step("Verify show 'Auto-save enabled' toast")
    public void verifyShowEnableAutoSaveToast() {
        switchToDefaultContent();
        verifyShowToast("Auto-save enabled");
        WebUI.switchToEditorFrame();
    }
}
