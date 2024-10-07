package org.pftest.projects.V2.components.common.input;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

// Template method
public abstract class BaseInput {
    protected By selector;

    public BaseInput() {
    }

    public BaseInput(By selector) {
        WebUI.waitForElementVisible(selector);
        this.selector = selector;
    }

    public BaseInput setSelector(By selector) {
        WebUI.waitForElementVisible(selector);
        this.selector = selector;
        return this;
    }

    public By getSelector() {
        return selector;
    }

    public void verifyVisible() {
        WebUI.waitForElementVisible(selector);
    }

    public abstract void setValue(String value);
    public abstract String getValue();
}
