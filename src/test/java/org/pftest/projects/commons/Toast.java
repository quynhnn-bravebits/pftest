package org.pftest.projects.commons;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class Toast {
    private By savingToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Saving page...\"]");
    private By savedToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page saved\"]");
    private By publishingToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Publishing page...\"]");
    private By publishedToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page published\"]");
    private By unpublishingToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Unpublishing page...\"]");
    private By unpublishedToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Page unpublished\"]");

    private By enableAutoSaveToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Auto-save enabled\"]");
    private By disableAutoSaveToast = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*[@class=\"Polaris-Frame-Toast\"]//*[text()=\"Auto-save disabled\"]");

    @Step("Verify show 'Publishing page...' toast")
    public void verifyShowPublishingToast() {
        verifyElementVisible(publishingToast);
    }

    @Step("Verify show 'Page published' toast")
    public void verifyShowPublishedToast() {
        verifyElementVisible(publishedToast);
    }

    @Step("Verify show 'Unpublishing page...' toast")
    public void verifyShowUnpublishingToast() {
        verifyElementVisible(unpublishingToast);
    }

    @Step("Verify show 'Page unpublished' toast")
    public void verifyShowUnpublishedToast() {
        verifyElementVisible(unpublishedToast);
    }

    @Step("Verify show 'Saving page...' toast")
    public void verifyShowSavingToast() {
        verifyElementVisible(savingToast);
    }

    @Step("Verify show 'Page saved' toast")
    public void verifyShowSavedToast() {
        verifyElementVisible(savedToast);
    }

    @Step("Verify show 'Auto-save enabled' toast")
    public void verifyShowEnableAutoSaveToast() {
        switchToDefaultContent();
        verifyElementVisible(enableAutoSaveToast);
        switchToPageFlyFrame();
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
}