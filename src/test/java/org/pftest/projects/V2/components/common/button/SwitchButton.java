package org.pftest.projects.V2.components.common.button;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static org.pftest.keywords.WebUI.clickElement;

public class SwitchButton {
    private By container;

    public SwitchButton() {
    }

    public SwitchButton(By container) {
        this.container = container;
    }

    public SwitchButton setContainer(By container) {
        this.container = container;
        return this;
    }

    /**
     * Click on the switch button has role {role}
     * @param role button role
     */
    public void click(String role) {
        By button = new ByChained(By.id("image-object-fit"), By.xpath("//button[@role='" + role.toUpperCase() + "']"));
        clickElement(button);
    }
}
