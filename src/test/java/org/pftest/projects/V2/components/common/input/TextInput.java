package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

public class TextInput extends BaseInput{

    public TextInput() {
        super();
    }

    public TextInput(By selector) {
        super(selector);
    }

    @Override
    public void setValue(String value) {
        WebUI.clearAndFillText(selector, value);
    }

    @Override
    public String getValue() {
        return WebUI.getAttributeElement(selector, "value");
    }
}
