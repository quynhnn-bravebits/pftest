package org.pftest.projects.V2.components.popover.publish;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.PageType;

import static org.pftest.keywords.WebUI.clickElement;
import static org.pftest.keywords.WebUI.waitForElementVisible;

public class BeforePublishPagePopover extends PublishPagePopover {
    private By selectButton;

    public BeforePublishPagePopover(PageType pageType) {
        super();
        String buttonText = pageType == PageType.COLLECTION ? "Select collections" : pageType == PageType.PRODUCT ? "Select products" : null;
        this.selectButton = new ByChained(container, By.xpath(".//button[span[text()='" + buttonText + "']]"));
    }

    public void clickSelectProducts() {
        waitForElementVisible(selectButton);
        clickElement(selectButton);
    }
}
