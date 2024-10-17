package org.pftest.projects.V2.components.common.toast;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.pftest.keywords.WebUI.*;

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

    @Step("Verify show 'Page assignment updated' toast")
    static public void verifyShowPageAssignmentToast() {
        switchToDefaultContent();
        verifyShowToast("Page assignment updated");
        switchToEditorFrame();
    }

    @Step("Verify show 'Duplicating pages...' toast")
    static public void verifyShowDuplicatingPagesToast() {
        verifyShowToast("Duplicating pages...");
    }

    @Step("Verify show 'Pages duplicated' toast")
    static public void verifyShowDuplicatedPagesToast() {
        waitForToast("Pages duplicated");
    }


    @Step("Verify show 'Duplicating sections...' toast")
    static public void verifyShowDuplicatingSectionsToast() {
        verifyShowToast("Duplicating sections...");
    }

    @Step("Verify show 'Sections duplicated' toast")
    static public void verifyShowDuplicatedSectionsToast() {
        waitForToast("Sections duplicated");
    }

    @Step("Verify show 'Deleting page...' toast")
    static public void verifyShowDeletingPageToast() {
        verifyShowToast("Deleting page...");
    }

    @Step("Verify show 'Page deleted' toast")
    static public void verifyShowDeletedPageToast() {
        waitForToast("Page deleted");
    }

    @Step("Verify show 'Deleting pages...' toast")
    static public void verifyShowDeletingPagesToast() {
        verifyShowToast("Deleting pages...");
    }

    @Step("Verify show 'Pages deleted' toast")
    static public void verifyShowDeletedPagesToast() {
        waitForToast("Pages deleted");
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

    @Step("Verify show 'Publishing pages...' toast")
    static public void verifyShowPublishingPagesToast() {
        verifyShowToast("Publishing pages...");
    }

    @Step("Verify show 'Pages published' toast")
    static public void verifyShowPublishedPagesToast() {
        waitForToast("Pages published");
    }

    @Step("Verify show 'Publishing section...' toast")
    static public void verifyShowPublishingSectionToast() {
        verifyShowToast("Publishing section...");
    }

    @Step("Verify show 'Section published' toast")
    static public void verifyShowPublishedSectionToast() {
        waitForToast("Section published");
    }

    @Step("Verify show 'Publishing sections...' toast")
    static public void verifyShowPublishingSectionsToast() {
        verifyShowToast("Publishing sections...");
    }

    @Step("Verify show 'Sections published' toast")
    static public void verifyShowPublishedSectionsToast() {
        waitForToast("Sections published");
    }

    @Step("Verify show 'Deleting sections...' toast")
    static public void verifyShowDeletingSectionsToast() {
        verifyShowToast("Deleting sections...");
    }

    @Step("Verify show 'Sections deleted' toast")
    static public void verifyShowDeletedSectionsToast() {
        waitForToast("Sections deleted");
    }

}
