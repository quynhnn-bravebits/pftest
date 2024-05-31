package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class EditorPageSandbox {

    @Step("Verify selected element has id {id} has css attribute {cssAttribute} with value {cssValue}")
    public void verifySelectedElementHasCssAttribute (String id, String cssAttribute, String cssValue) {
        switchToDragAndDropFrame();
        verifyElementAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute, cssValue);
        switchToPageFlyFrame();
    }

    @Step("Verify selected element has id {id} has removed css attribute {cssAttribute}")
    public void verifySelectedElementHasRemovedCssAttribute (String id, String cssAttribute, String cssValue) {
        switchToDragAndDropFrame();
        verifyElementNotHaveAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute, cssValue);
        switchToPageFlyFrame();
    }
}