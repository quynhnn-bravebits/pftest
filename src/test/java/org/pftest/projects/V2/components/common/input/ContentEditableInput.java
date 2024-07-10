package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

public class ContentEditableInput extends BaseInput{
    public ContentEditableInput() {
        super();
    }

    public ContentEditableInput(By selector) {
        super(selector);
    }

    @Override
    public void setValue(String value) {
        WebUI.clickElement(selector);
        WebUI.clearAndFillTextNotReachableByKeyboard(selector, value);
    }

    @Override
    public String getValue() {
        return WebUI.getTextElement(selector);
    }
}
