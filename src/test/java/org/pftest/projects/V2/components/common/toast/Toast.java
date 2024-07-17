package org.pftest.projects.V2.components.common.toast;

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
    static public void verifyShowDuplicatingPageToast() {
        verifyShowToast("Duplicating page...");
    }

    @Step("Verify show 'Page duplicated' toast")
    static public void verifyShowDuplicatedPageToast() {
        waitForToast("Page duplicated");
    }

    @Step("Verify show 'Deleting page...' toast")
    static public void verifyShowDeletingPageToast() {
        verifyShowToast("Deleting page...");
    }

    @Step("Verify show 'Page exported' toast")
    static public void verifyShowExportedPageToast() {
        waitForToast("Page exported");
    }

    @Step("Verify show 'Exporting pages...' toast")
    static public void verifyShowExportingPagesToast() {
        verifyShowToast("Exporting pages");
    }

    @Step("Verify show 'Pages exported' toast")
    static public void verifyShowExportedPagesToast() {
        waitForToast("Pages exported");
    }

    @Step("Verify show 'Importing page...' toast")
    static public void verifyShowImportingPageToast() {
        verifyShowToast("Importing page...");
    }

    @Step("Verify show 'Page imported' toast")
    static public void verifyShowImportedPageToast() {
        waitForToast("Page imported");
    }

    @Step("Verify show 'Importing pages...' toast")
    static public void verifyShowImportingPagesToast() {
        verifyShowToast("Importing pages...");
    }

    @Step("Verify show 'Pages imported' toast")
    static public void verifyShowImportedPagesToast() {
        waitForToast("Pages imported");
    }

    @Step("Verify show 'Page deleted' toast")
    static public void verifyShowDeletedPageToast() {
        waitForToast("Page deleted");
    }

    @Step("Verify show 'Saving page...' toast")
    static public void verifyShowSavingPageToast() {
        switchToDefaultContent();
        verifyShowToast("Saving page...");
        switchToEditorFrame();
    }

    @Step("Verify show 'Page saved' toast")
    static public void verifyShowSavedPageToast() {
        switchToDefaultContent();
        waitForToast("Page saved");
        switchToEditorFrame();
    }

    @Step("Verify show 'Publishing page...' toast")
    static public void verifyShowPublishingPageToast() {
        verifyShowToast("Publishing page...");
    }

    @Step("Verify show 'Page published' toast")
    static public void verifyShowPublishedPageToast() {
        waitForToast("Page published");
    }

    @Step("Verify show 'Unpublishing page...' toast")
    static public void verifyShowUnpublishingPageToast() {
        verifyShowToast("Unpublishing page...");
    }

    @Step("Verify show 'Page unpublished' toast")
    static public void verifyShowUnpublishedPageToast() {
        waitForToast("Page unpublished");
    }

    @Step("Verify show 'Unpublishing pages...' toast")
    static public void verifyShowUnpublishingPagesToast() {
        verifyShowToast("Unpublishing pages...");
    }

    @Step("Verify show 'Pages unpublished' toast")
    static public void verifyShowUnpublishedPagesToast() {
        waitForToast("Pages unpublished");
    }

    @Step("Verify show 'Publishing section...' toast")
    static public void verifyShowPublishingSectionToast() {
        verifyShowToast("Publishing section...");
    }

    @Step("Verify show 'Section published' toast")
    static public void verifyShowPublishedSectionToast() {
        waitForToast("Section published");
    }

}
