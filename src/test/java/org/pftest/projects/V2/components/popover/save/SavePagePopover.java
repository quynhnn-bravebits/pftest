package org.pftest.projects.V2.components.popover.save;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.ModalConstants;
import org.pftest.projects.V2.components.popover.EditorHeaderPopover;

import static org.pftest.keywords.WebUI.*;

public class SavePagePopover extends EditorHeaderPopover {
    private By saveButton = new ByChained(container, By.xpath(".//button[span[text()='" + ModalConstants.SAVE_MODAL.PRIMARY_BUTTON + "']]"));
    private By dontRemindMeCheckbox = new ByChained(container, By.xpath(".//input[@type='checkbox']"));

    public void waitForVisible() {
        waitForElementVisible(container);
        waitForElementVisible(heading(ModalConstants.SAVE_MODAL.TITLE));
    }

    @Step("Click 'Don't remind me' checkbox")
    public void clickDontRemindMe() {
        waitForElementVisible(dontRemindMeCheckbox);
        clickElement(dontRemindMeCheckbox);
        verifyElementChecked(dontRemindMeCheckbox);
    }

    @Step("Click 'Save' button")
    public void clickSave() {
        waitForElementVisible(saveButton);
        clickElement(saveButton);
        sleep(0.5);
        verifyElementNotVisible(container);
    }
}
