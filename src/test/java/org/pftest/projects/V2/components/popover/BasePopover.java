package org.pftest.projects.V2.components.popover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.keywords.WebUI;

public class BasePopover {
    protected By locator = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//div[starts-with(@data-portal-id, 'popover')]//div[@class='Polaris-Popover__Content']");

    public BasePopover(String content) {
        this.locator = new ByChained(
                this.locator,
                By.xpath("self::node()[//*[contains(text(), 'Select page template')]]")
        );
    }

    public void verifyVisible() {
        WebUI.waitForElementVisible(locator);
    }

    public void verifyNotVisible() {
        WebUI.verifyElementNotVisible(locator);
    }
}
