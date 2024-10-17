package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static org.pftest.keywords.WebUI.*;

public class BaseDrawer implements IDrawer {
    protected By activator;
    protected By container;
    protected By title;

    public BaseDrawer() {
    }

    public BaseDrawer(By activator, By container) {
        this.activator = activator;
        this.container = container;
    }

    public BaseDrawer setActivator(By activator) {
        this.activator = activator;
        return this;
    }

    public BaseDrawer setContainer(By container) {
        this.container = container;
        this.title = new ByChained(container, By.xpath(".//h3"));
        return this;
    }


    @Override
    public void open() {
        waitForElementVisible(activator);
        sleep(0.5);
        clickElement(activator);
        waitForElementVisible(container);
    }

    @Override
     public void waitForLoaded() {
        waitForElementVisible(activator);
        waitForElementClickable(activator);
    }
}
