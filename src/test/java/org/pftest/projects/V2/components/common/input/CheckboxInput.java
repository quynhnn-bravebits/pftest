package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.keywords.WebUI;

public class CheckboxInput extends BaseInput {
    private By inputSelector;

    /**
     * @param labelSelector: selector of the *LABEL* element
     */
    public CheckboxInput(By labelSelector) {
        super(labelSelector);
        this.inputSelector = new ByChained(selector, By.tagName("input"));
    }

    /**
     * @param inputId: id of the *INPUT* element
     */
    public CheckboxInput(String inputId) {
        super(By.xpath(String.format("//label[@for='%s']", inputId)));
        this.inputSelector = new ByChained(selector, By.tagName("input"));
    }

    /**
     * Set the value of the checkbox
     * @param value true | false
     */
    @Override
    public void setValue(String value) {
        boolean isChecked = WebUI.verifyElementChecked(inputSelector);
        if (value.equals("true") && !isChecked) {
            WebUI.clickElement(selector);
            WebUI.verifyTrue(WebUI.verifyElementChecked(inputSelector), "Actual: Checkbox is not checked");
        } else if (value.equals("false") && isChecked) {
            WebUI.clickElement(selector);
            WebUI.verifyTrue(!WebUI.verifyElementChecked(inputSelector), "Actual: Checkbox is checked");
        }
    }

    @Override
    public String getValue() {
        return WebUI.verifyElementChecked(inputSelector) ? "true" : "false";
    }
}
