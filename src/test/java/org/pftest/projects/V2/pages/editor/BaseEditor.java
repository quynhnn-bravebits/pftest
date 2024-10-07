package org.pftest.projects.V2.pages.editor;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.popover.publish.PublishPagePopover;
import org.pftest.projects.V2.components.popover.save.SavePagePopover;

import static org.pftest.keywords.WebUI.*;

public class BaseEditor implements IEditor {
    protected PageHeader pageHeader;
    protected By saveButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button/span[text()='Save']");
    protected By publishButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button/span[text()='Publish']");
    protected By saveButtonInsidePopup = new ByChained(By.id("POPOVER_DEFAULT_ID"), By.xpath(".//button[span[text()='Save']]"));
    protected By inspector = By.id("drawer-has-sub-inspector");

    public PageHeader getPageHeader() {
        if (pageHeader == null) {
            pageHeader = new PageHeader();
        }
        return pageHeader;
    }

    public void clickSavePagePopup() {
        waitForElementVisible(saveButtonInsidePopup);
        clickElement(saveButtonInsidePopup);
    }

    public void save() {
        switchToDefaultContent();
        waitForElementVisible(saveButton);
        clickElement(saveButton);
        switchToEditorFrame();
        new SavePagePopover().clickSave();
        switchToDefaultContent();
        Toast.verifyShowSavingPageToast();
        Toast.verifyShowSavedPageToast();
    };

    public void publish() {
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        new PublishPagePopover().clickPublish();
        switchToDefaultContent();
        Toast.verifyShowPublishingPageToast();
        Toast.verifyShowPublishedPageToast();
        switchToEditorFrame();
        PageHeader.verifyPublishStatus();
    }

    public void close() {

    }

    public void changePageTitle(String title) {
        getPageHeader().changePageTitle(title);
    }

    public void openLiveChat() {

    }

}
