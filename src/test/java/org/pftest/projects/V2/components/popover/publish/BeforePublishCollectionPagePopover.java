package org.pftest.projects.V2.components.popover.publish;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static org.pftest.keywords.WebUI.clickElement;
import static org.pftest.keywords.WebUI.waitForElementVisible;

public class BeforePublishCollectionPagePopover extends PublishPagePopover {
    private By selectCollectionsButton = new ByChained(container, By.xpath(".//button[span[text()='Select collections']]"));

    public void clickSelectCollections() {
        waitForElementVisible(selectCollectionsButton);
        clickElement(selectCollectionsButton);
    }
}
