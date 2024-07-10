package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;

public class BaseDrawer implements IDrawer {
    protected By activator;
    protected By container;

    public BaseDrawer setActivator(By activator) {
        this.activator = activator;
        return this;
    }

    public BaseDrawer setContainer(By container) {
        this.container = container;
        return this;
    }



}
