package org.pftest.projects.V2.components.popover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class EditorHeaderPopover {
    protected By container = By.id("POPOVER_DEFAULT_ID");
    protected By heading (String text) {
        return new ByChained(container, By.xpath(".//p[contains(@class, 'Polaris-Text--heading') and text()='" + text + "']"));
    }
}
