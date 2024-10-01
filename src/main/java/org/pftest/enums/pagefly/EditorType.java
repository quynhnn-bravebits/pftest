package org.pftest.enums.pagefly;

import org.openqa.selenium.By;

public enum EditorType {
    FLEX() {
        public String getEditorType() {
            return "Gen 2 Editor";
        }
    },
    BASIC() {
        public String getEditorType() {
            return "Legacy Editor";
        }
    };

    public abstract String getEditorType();

    public By getOptionLocator() {
        String xpath = String.format("//div[@class='Polaris-ResourceItem'][.//h3[text()='%s']]", this.getEditorType());
        return By.xpath(xpath);
    }
}
