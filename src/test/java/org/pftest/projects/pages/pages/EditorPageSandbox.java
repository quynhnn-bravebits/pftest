package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class EditorPageSandbox {

    @Step("Verify selected element has id {id} has css attribute {cssAttribute} with value {cssValue}")
    public void verifySelectedElementHasCssAttribute (String id, String cssAttribute, String cssValue) {
        switchToDragAndDropFrame();
        verifyElementAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute, cssValue);
        switchToPageFlyFrame();
    }

}