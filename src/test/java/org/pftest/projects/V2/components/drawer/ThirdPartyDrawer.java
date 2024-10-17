package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;

public class ThirdPartyDrawer extends BaseDrawer {
    public ThirdPartyDrawer() {
        super(By.id("third-party-drawer-button"), By.id("third-party-drawer"));
    }
}
