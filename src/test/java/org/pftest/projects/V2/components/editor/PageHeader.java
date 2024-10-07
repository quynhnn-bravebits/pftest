package org.pftest.projects.V2.components.editor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.DeviceMode;
import org.pftest.keywords.WebUI;

public class PageHeader {
    private static final By pageTitle = By. id("editor-header-bar--page-title");
    private static final By unpublishStatus = new ByChained(By.id("editor-header-bar--status"), By.xpath(".//span[@class='Polaris-Badge']/span[text()='Unpublished']"));
    private static final By publishStatus = new ByChained(By.id("editor-header-bar--status"), By.xpath(".//span[@class='Polaris-Badge Polaris-Badge--toneInfo']/span[text()='Published']"));
    private static final By unpublishButton = By.id("editor-header-bar--unpublish");

    private static final By canvasSettingButton = By.id("canvas-size-setting-activator-btn");
    private static final By canvasWidthInput = By.xpath("//div[@id='canvas-size-setting']//input[@type='number']");
    private static final By fitViewportCheckbox = By.xpath("//div[@id='canvas-size-setting']//input[@type='checkbox']");

    private static final By editorSettingButton = By.id("editor-header-bar--editor-setting--activator");
    private static final By enableThemeStylingCheckbox = By.id("enable-theme-styling-setting-item");
    private static final By viewPageStructureCheckbox = By.id("view-page-structure-setting-item");
    private static final By showCanvasSizeCheckbox = By.id("show-canvas-size-setting-item");

    private static final By undoButton = By.id("pf-undo-btn");
    private static final By redoButton = By.id("pf-redo-btn");
    private static final By previewButton = By.id("editor-header-bar--preview-page-btn");
    private static final By viewLivePageButton = By.id("editor-header-bar--view-live-page-btn");


    static public void changePageTitle(String title) {
        WebUI.clickElement(pageTitle);
        WebUI.clearAndFillTextNotReachableByKeyboard(pageTitle, title);
        WebUI.sendKeys(pageTitle, Keys.ENTER);
    }

    static public void verifyPageEditorTitle(String title) {
        WebUI.verifyElementTextEquals(pageTitle, title);
    }

    static public boolean verifyUnpublishStatus() {
        return WebUI.verifyElementVisible(unpublishStatus);
    }

    static public boolean verifyPublishStatus() {
        return WebUI.verifyElementVisible(publishStatus);
    }

    static public void clickUnpublish() {
        WebUI.clickElement(unpublishButton);
    }

    static public void changeDeviceMode(DeviceMode deviceMode) {
        By deviceModeButton = By.id("editor-header-bar--device-selector--" + deviceMode.getId());
        WebUI.clickElement(deviceModeButton);
    }

    static public void undo() {
        WebUI.waitForElementClickable(undoButton);
        WebUI.sleep(0.1);
        WebUI.clickElement(undoButton);
    }

    static public void verifyUndoButtonDisabled() {
        WebUI.verifyButtonIsDisabled(undoButton);
    }

    static public void redo() {
        WebUI.waitForElementClickable(redoButton);
        WebUI.sleep(0.1);
        WebUI.clickElement(redoButton);
    }

    static public void verifyRedoButtonDisabled() {
        WebUI.verifyButtonIsDisabled(redoButton);
    }

    static public void openCanvasSetting() {
        WebUI.clickElement(canvasSettingButton);
    }

    static public int changeCanvasWidth(int width, DeviceMode deviceMode) {
        // fill value to the input
        WebUI.clickElement(canvasSettingButton);
        WebUI.clickElement(canvasWidthInput);
        WebUI.clearAndFillText(canvasWidthInput, String.valueOf(width));
        WebUI.verifyEquals(WebUI.getAttributeElement(canvasWidthInput, "value"), String.valueOf(width));

        // calculate the acceptable value
        int min = deviceMode.getMinSize(), max = deviceMode.getMaxSize();
        int expected = (width >= min && width <= max) ? width : deviceMode.getDefaultSize();

        String expectedError = width < min ? ("The minimum possible value is: " + min) : width > max ? ("The maximum possible value is: " + max) : null;
        By errorField = By.xpath("//div[@id='canvas-size-setting']//*[contains(@id, 'Error') and contains(text(), '" + expectedError + "')]");
        if (expectedError != null) {
            WebUI.verifyElementVisible(errorField);
        } else {
            WebUI.verifyElementNotVisible(errorField);
        }

        // tab out to trigger the validation
        WebUI.sendKeys(canvasWidthInput, Keys.TAB);
        WebUI.sleep(0.5);
        WebUI.verifyEquals(WebUI.getAttributeElement(canvasWidthInput, "value"), String.valueOf(expected));
        WebUI.verifyElementTextContains(canvasWidthInput, String.valueOf(expected) + "px");
        WebUI.clickElement(canvasSettingButton);

        return expected;
    }

    static public void toggleFitViewport(boolean toggle) {
        WebUI.clickElement(canvasSettingButton);
        if (WebUI.verifyElementChecked(fitViewportCheckbox) != toggle) {
            WebUI.clickElement(fitViewportCheckbox);
            assert WebUI.verifyElementChecked(fitViewportCheckbox) == toggle;
        }
        WebUI.clickElement(canvasSettingButton);
    }

    static public void openEditorSetting() {
        WebUI.clickElement(editorSettingButton);
    }

    static public void toggleShowCanvasSize(boolean toggle) {
        WebUI.clickElement(editorSettingButton);
        if (WebUI.verifyElementChecked(showCanvasSizeCheckbox) != toggle) {
            WebUI.clickElement(showCanvasSizeCheckbox);
            assert WebUI.verifyElementChecked(showCanvasSizeCheckbox) == toggle;
        }
        WebUI.clickElement(editorSettingButton);
    }

    static public void clickPreview() {
        WebUI.clickElement(previewButton);
    }

    static public void clickViewLivePage() {
        WebUI.clickElement(viewLivePageButton);
    }

}
