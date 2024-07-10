package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.pftest.keywords.WebUI.getAttributeElement;
import static org.pftest.keywords.WebUI.waitForElementVisible;

public class SliderInput extends BaseInput {
    public SliderInput() {
        super();
    }

    public SliderInput(By selector) {
        super(selector);
    }

    @Override
    public void setValue(String value) {
        int fontSize = Integer.parseInt(value);

        WebElement slider = waitForElementVisible(selector);
        int currentFontSize = (int) Double.parseDouble(getAttributeElement(selector, "aria-valuenow"));
        if (currentFontSize > fontSize) {
            for (int i = 0; i <= currentFontSize - fontSize; i++) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        else if (currentFontSize < fontSize) {
            for (int i = 0; i < fontSize - currentFontSize; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        }
    }

    @Override
    public String getValue() {
        return getAttributeElement(selector, "value");
    }
}
