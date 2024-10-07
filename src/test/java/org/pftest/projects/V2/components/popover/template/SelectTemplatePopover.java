package org.pftest.projects.V2.components.popover.template;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.common.input.CheckboxInput;
import org.pftest.projects.V2.components.popover.BasePopover;

public class SelectTemplatePopover extends BasePopover {
    private CheckboxInput understandCheckbox = new CheckboxInput("page-template-drawer--confirmation--checkbox");
    private By confirmButton = By.id("page-template-drawer--confirmation--confirm");
    private By cancelButton = By.id("page-template-drawer--confirmation--cancel");

    public SelectTemplatePopover() {
        super("Select page template");
    }

    @Step("Verify Select Page Template popover is visible")
    public void verifyVisible() {
        super.verifyVisible();
        understandCheckbox.verifyVisible();
        WebUI.waitForElementVisible(confirmButton);
    }

    @Step("Verify Select Page Template popover is not hidden")
    public void verifyNotVisible() {
        super.verifyNotVisible();
    }

    @Step("Check 'I understand what I'm doing' checkbox")
    public void checkUnderstandCheckbox() {
        understandCheckbox.setValue("true");
    }

    @Step("Click 'Confirm' button")
    public void clickConfirmButton() {
        WebUI.waitForElementClickable(confirmButton);
        WebUI.clickElement(confirmButton);
    }

    @Step("Click 'Cancel' button")
    public void clickCancelButton() {
        WebUI.waitForElementClickable(cancelButton);
        WebUI.clickElement(cancelButton);
    }

}
