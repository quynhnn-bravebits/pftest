package org.pftest.projects.V2.pages.editor;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.modal.factory.PageModalFactory;

abstract class BaseEditor implements IEditor {
    protected By inspector = By.id("drawer-has-sub-inspector");

    public abstract void save();

    public abstract void publish();

    public void close() {

    }

    public void changePageTitle(String title) {

    }

    public void openLiveChat() {

    }

    public abstract PageModalFactory getModalFactory();
}
