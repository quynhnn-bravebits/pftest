package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;

public class CheckboxInput extends BaseInput {
    public static By baseSelector = By.xpath(".//input[@type='checkbox']");

    public CheckboxInput(By selector) {
        super(selector);
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public String getValue() {
        return "";
    }
}
