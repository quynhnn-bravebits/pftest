package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;

public class TemplatesDrawer extends BaseDrawer {
    private final String type;

    public TemplatesDrawer(String type) {
        this.type = type;
        this.container = By.id("page-template-drawer");
        if (type.equalsIgnoreCase("page")) {
            this.title = By.xpath("//h3[text()='Page templates']");
        } else if (type.equalsIgnoreCase("section")) {
            this.title = By.xpath("//h3[text()='Section templates']");
        }
    }

}
