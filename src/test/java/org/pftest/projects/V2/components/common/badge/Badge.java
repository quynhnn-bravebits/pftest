package org.pftest.projects.V2.components.common.badge;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

public class Badge {
    private String text;

    public Badge(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public By selector() {
        return By.xpath("//span[contains(@class, 'Polaris-Badge')]//[text()='" + this.text + "']");
    }

    public By badgeSelector() {
        return By.xpath("//span[@class='Polaris-Badge']//[text()='" + this.text + "']");
    }

    public By infoBadgeSelector() {
        return By.xpath("//span[@class='Polaris-Badge Polaris-Badge--toneInfo']//[text()='" + this.text + "']");
    }

    public void verifyPublished() {
        this.text = "Published";
        WebUI.verifyElementVisible(infoBadgeSelector());
    }

    public void verifyUnpublished() {
        this.text = "Unpublished";
        WebUI.verifyElementVisible(badgeSelector(), 30);
    }

    public void waitForPublished() {
        this.text = "Published";
        WebUI.waitForElementVisible(infoBadgeSelector(), 30);
    }
}
