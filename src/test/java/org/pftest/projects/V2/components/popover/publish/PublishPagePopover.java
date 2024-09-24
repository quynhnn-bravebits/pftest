package org.pftest.projects.V2.components.popover.publish;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.projects.V2.components.popover.EditorHeaderPopover;

import static org.pftest.keywords.WebUI.*;

public class PublishPagePopover extends EditorHeaderPopover {
    private By pageTitleInput = new ByChained(container, By.id("menubar--save-modal--page-title"));
    private By pageUrlInput = new ByChained(container, By.id("menubar--save-modal--page-url"));
    private By publishButton = new ByChained(container, By.xpath(".//button[span[text()='Publish']]"));

    public void verifyVisible() {
        waitForElementVisible(pageTitleInput);
        waitForElementVisible(pageUrlInput);
    }

    public void verifyNotVisible() {
        verifyElementNotVisible(container);
    }

    @Step("Change page title to '{title}'")
    public void changePageTitle(String title) {
        waitForElementVisible(pageTitleInput);
        clearAndFillText(pageTitleInput, title);
        verifyElementAttributeValue(pageTitleInput, "value", title);
    }

    @Step("Change page URL to '{url}'")
    public void changePageUrl(String url) {
        waitForElementVisible(pageUrlInput);
        clearAndFillText(pageUrlInput, url);
        verifyElementAttributeValue(pageUrlInput, "value", url);
    }

    @Step("Click 'Publish' button")
    public void clickPublish() {
        waitForElementVisible(publishButton);
        clickElement(publishButton);
    }



}
