package org.pftest.projects.commons;

import org.openqa.selenium.By;

import static org.pftest.keywords.WebUI.*;

public class Badge {
    private final By savedBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Saved\"]");
    private final By unsavedBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneAttention\"]//*[text()=\"Unsaved\"]");
    private final By savingBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Saving...\"]");
    private final By publishedBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Published\"]");
    private final By unpublishedBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Unpublished\"]");
    private final By publishingBadge = By.xpath("//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Publishing...\"]");

    public By getSavedBadge() {
        return savedBadge;
    }

    public By getUnsavedBadge() {
        return unsavedBadge;
    }

    public By getSavingBadge() {
        return savingBadge;
    }

    public By getPublishedBadge() {
        return publishedBadge;
    }

    public By getUnpublishedBadge() {
        return unpublishedBadge;
    }

    public By getPublishingBadge() {
        return publishingBadge;
    }

    public void verifySavedBadge(int timeout) {
         verifyElementVisible(savedBadge, timeout);
    }

    public void verifyUnsavedBadge() {
         verifyElementVisible(unsavedBadge);
    }

    public void verifySavingBadge() {
         verifyElementVisible(savingBadge);
    }

    public void verifyPublishedBadge() {
         verifyElementVisible(publishedBadge);
    }

    public void verifyUnpublishedBadge() {
         verifyElementVisible(unpublishedBadge);
    }

    public void verifyPublishingBadge() {
         verifyElementVisible(publishingBadge);
    }

    public void verifySavedBadge() {
         verifyElementVisible(savedBadge);
    }

    public void verifyUnsavedBadge(int timeout) {
         verifyElementVisible(unsavedBadge, timeout);
    }

    public void verifySavingBadge(int timeout) {
         verifyElementVisible(savingBadge, timeout);
    }

    public void verifyPublishedBadge(int timeout) {
         verifyElementVisible(publishedBadge, timeout);
    }

    public void verifyUnpublishedBadge(int timeout) {
         verifyElementVisible(unpublishedBadge, timeout);
    }

    public void verifyPublishingBadge(int timeout) {
         verifyElementVisible(publishingBadge, timeout);
    }


}
