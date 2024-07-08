package org.pftest.projects.V2.pages.editor;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

abstract class BaseEditor implements IEditor {
    protected By inspector = By.id("drawer-has-sub-inspector");


    public abstract void save();

    public abstract void publish();

    public void close() {

    }


}
