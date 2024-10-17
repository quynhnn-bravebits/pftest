package org.pftest.projects.V2.pages.editor;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.driver.DriverManager;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.drawer.*;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.popover.publish.PublishPagePopover;
import org.pftest.projects.V2.components.popover.save.SavePagePopover;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.keywords.WebUI.switchToEditorFrame;
import static org.pftest.utils.CommonUtils.htmlSourceProcessing;

public class BaseEditor implements IEditor {
    protected PageHeader pageHeader;
    protected By saveButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button[./span[text()='Save']]");
    protected By publishButton = By.xpath("//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button[./span[text()='Publish']]");
//    protected By exitButton = By.xpath("(//div[starts-with(@data-portal-id, 'overlay-')]//div[contains(@class, 'Header')]//button[@aria-label='Close overlay'])[last()]");
    protected By exitButton = new ByChained(publishButton, By.xpath("./following-sibling::div//button[@aria-label='Close overlay']"));
    protected By saveButtonInsidePopup = new ByChained(By.id("POPOVER_DEFAULT_ID"), By.xpath(".//button[span[text()='Save']]"));
    protected By inspector = By.id("drawer-has-sub-inspector");
    protected PageOutlineDrawer pageOutlineDrawer = new PageOutlineDrawer();
    protected ElementsDrawer elementsDrawer = new ElementsDrawer();
    protected ThirdPartyDrawer thirdPartyDrawer = new ThirdPartyDrawer();
    protected TemplatesDrawer templatesDrawer = new TemplatesDrawer();
    protected PageSettingsDrawer pageSettingsDrawer = new PageSettingsDrawer();


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

    public void backToPageListingScreen() {
        switchToDefaultContent();
        WebUI.waitForElementClickable(exitButton);
        WebUI.clickElement(exitButton);
    }

    @Step("Wait for the page to be loaded")
    public void verifyPageLoaded() {
        waitForPageLoaded();
        switchToEditorFrame();
        PageHeader.waitForLoaded();
        pageOutlineDrawer.waitForLoaded();
        elementsDrawer.waitForLoaded();
        thirdPartyDrawer.waitForLoaded();
        templatesDrawer.waitForLoaded();
        pageSettingsDrawer.waitForLoaded();

    }

    public String getCanvasHtmlProcessed() {
        switchToDragAndDropFrame();
        String html = DriverManager.getDriver().getPageSource();
        switchToEditorFrame();
        return htmlSourceProcessing(html);
    }

    public void verifyPageIsUnpublished() {
        PageHeader.verifyUnpublishStatus();
    }
}
