package org.pftest.projects.commons;

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
        verifyElementVisible(unpublishingPageToast);
    }

    @Step("Verify show 'Page unpublished' toast")
    public void verifyShowUnpublishedPageToast() {
        verifyElementVisible(unpublishedPageToast, 30);
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