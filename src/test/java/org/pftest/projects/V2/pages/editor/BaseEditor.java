package org.pftest.projects.V2.pages.editor;

import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.modal.factory.CommonPageModalFactory;
import org.pftest.projects.V2.components.modal.factory.PageModalFactory;

import static org.pftest.keywords.WebUI.*;

abstract class BaseEditor implements IEditor {
    protected PageHeader pageHeader;
    protected By saveButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button/span[text()='Save']");
    protected By publishButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button/span[text()='Publish']");
    protected By inspector = By.id("drawer-has-sub-inspector");

    public PageHeader getPageHeader() {
        if (pageHeader == null) {
            pageHeader = new PageHeader();
        }
        return pageHeader;
    }

    public void save() {
        switchToDefaultContent();
        waitForElementVisible(saveButton);
        clickElement(saveButton);
        switchToEditorFrame();
        new CommonPageModalFactory().createBeforeSavePageModal().clickPrimaryButton();
        switchToEditorFrame();
        new CommonPageModalFactory().createSavePageModal().clickPrimaryButton();
        switchToDefaultContent();
        Toast.verifyShowSavingPageToast();
        Toast.verifyShowSavedPageToast();
    };

    public abstract void publish();

    public void close() {

    }

    public void changePageTitle(String title) {
        getPageHeader().changePageTitle(title);
    }

    public void openLiveChat() {

    }

    public abstract PageModalFactory getModalFactory();
}
