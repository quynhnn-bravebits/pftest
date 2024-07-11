package org.pftest.projects.V2.components.drawer;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.RichTextOptionTagName;
import org.pftest.projects.V2.components.common.button.SwitchButton;
import org.pftest.projects.V2.components.common.input.ContentEditableInput;
import org.pftest.projects.V2.components.common.input.SliderInput;
import org.pftest.projects.V2.components.common.input.TextInput;

import javax.annotation.Nullable;
import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

public class PageInspector extends BaseDrawer {
    private static final By generalButton = By.id("general");
    private static final By stylingButton = By.id("styling");

    /*
     * Random click inside element
     *
     * @param by selector of the element
     *
     */
    public static void randomClickColorPicker() {
        By colorPicker = By.cssSelector(".Polaris-ColorPicker__ColorLayer");
        By hueSlider = By.cssSelector(".Polaris-ColorPicker__HuePicker .Polaris-ColorPicker__Slidable");
        By alphaSlider = By.cssSelector(".Polaris-ColorPicker__AlphaPicker .Polaris-ColorPicker__Slidable");
        randomClickInsideElement(colorPicker);
        randomClickInsideElement(alphaSlider);
        randomClickInsideElement(hueSlider);
    }

    public static String getRichTextHTMLContent () {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        return getAttributeElement(textContentInput, "innerHTML").replaceAll("</?(second-sel|first-sel)>", "").replaceAll("<br>$", "");
    }

    public String getIconPosition() {
        openGeneralTab();
        By iconPositionSwitch = By.id("icon-position");
        return getAttributeElement(new ByChained(iconPositionSwitch, By.xpath("//button[contains(@class, 'Polaris-Button--variantSecondary')]")), "role");
    }

    public String getIconVerticalAlignment() {
        openGeneralTab();
        By iconVerticalAlignmentSwitch = By.id("icon-vertical-alignment");
        return getAttributeElement(new ByChained(iconVerticalAlignmentSwitch, By.xpath("//button[contains(@class, 'Polaris-Button--variantSecondary')]")), "role");
    }

    public String openColorPickerAndPickColor(By activatorInput) {
        clickElement(activatorInput);
        waitForElementVisible(By. id("inspector--content-color--color-picker-box"));
        randomClickColorPicker();
        clickElement(By.id("inspector--content-color--input-Prefix"));
        return getAttributeElement(activatorInput, "value");
    }


    // ================== Inspector Header ==================

    @Step("Open Styling tab")
    public static void openStylingTab() {
        if (!Objects.equals(getAttributeElement(stylingButton, "aria-selected"), "true")) {
            waitForElementClickable(stylingButton);
            sleep(0.1);
            clickElement(stylingButton);
            verifyElementAttributeValue(stylingButton, "aria-selected", "true");
        }
    }

    @Step("Open General tab")
    public static void openGeneralTab() {
        if (!Objects.equals(getAttributeElement(generalButton, "aria-selected"), "true")) {
            waitForElementClickable(generalButton);
            sleep(0.1);
            clickElement(generalButton);
            verifyElementAttributeValue(generalButton, "aria-selected", "true");
        }
    }

    // ================== General ==================

    public static void changeTextContent(String content) {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        new ContentEditableInput().setSelector(textContentInput).setValue(content);
        sleep(0.3);
    }

    public static void pasteClipboardTextContent() {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        verifyElementVisible(textContentInput);
        clearText(textContentInput);
        moveCursorToEndOfContent(textContentInput);
        pasteStyleByShortcut();
        sleep(0.3);
    }

    public void selectAndAdjustTextContent(String text, RichTextOptionTagName... options) {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        highlightTextInElementByJs(textContentInput, text);
        for (RichTextOptionTagName option : options) {
            clickElement(By.id("pf_text_editor_" + option.getTagName()));
            verifySelectedTextWrappedInTag(option.getHtmlTag());
            sleep(2);
        }
    }

    public void addNewItemToList() {
        openGeneralTab();
        By addNewItemButton = By.id("sortable-list--add-new-item-btn");
        waitForElementClickable(addNewItemButton);
        clickElement(addNewItemButton);
    }

    public void removeItemFromList() {
        openGeneralTab();
        By deleteButton = By.xpath("//*[@id='CONTENT']//*[starts-with(@id, 'sortable-item-')]//*[contains(@class, 'delete-item-btn')]");
        clickElement(deleteButton);
    }

    public void changeColumnsPerLineByInput(String columnsPerLine) {
        openGeneralTab();
        TextInput columnsPerLineInput = new TextInput(By.id("inspector--base--columns-per-line--input"));
        columnsPerLineInput.setValue(columnsPerLine);
    }

    public void changeShowIcon(String showIcon) {
        openGeneralTab();
        By showIconButton = new ByChained(By.id("show-icon"), By.xpath("//button[@role='" + showIcon.toUpperCase() + "']"));
        clickElement(showIconButton);
    }

    public void changeIconPosition(String position) {
        openGeneralTab();
        By iconPositionButton = By.xpath("//*[@id='icon-position']//button[@role='" + position.toUpperCase() + "']");
        sleep(0.5);
        clickElement(iconPositionButton);
    }

    public void changeIconVerticalAlignment(String alignment) {
        openGeneralTab();
        By iconVerticalAlignmentButton = By.xpath("//*[@id='icon-vertical-alignment']//button[@role='" + alignment.toUpperCase() + "']");
        sleep(0.5);
        clickElement(iconVerticalAlignmentButton);
    }

    public void changeShowDropCap(String showDropCap) {
        openGeneralTab();
        scrollToElementAtTop(By.id("show-dropcap"));
        By showDropCapButton = new ByChained(By.id("show-dropcap"), By.xpath("//button[@role='" + showDropCap + "']"));
        clickElement(showDropCapButton);
    }

    public void changeDropCapContent(String content) {
        openGeneralTab();
        scrollToElementAtTop(By.id("dropcap-text"));
        TextInput dropCapContentInput = new TextInput(new ByChained(By.id("dropcap-text"), By.tagName("input")));
        dropCapContentInput.setValue(content);
    }

    public void changeContentPosition(String position) {
        openGeneralTab();
        By positionButton = By.id("inspector--position--" + position);
        moveToElement(positionButton);
        clickElement(positionButton);
    }

    public void changeEnableEqualHeight(String enable) {
        openGeneralTab();
        By enableEqualHeightButton = new ByChained(By.id("enable-equal-height"), By.xpath("//button[@role='" + enable.toUpperCase() + "']"));
        clickElement(enableEqualHeightButton);
    }

    public void changeColumnsSpacingByInput(String columnsSpacing) {
        openGeneralTab();
        TextInput columnsSpacingInput = new TextInput(By.id("inspector--base--columns-spacing--input"));
        columnsSpacingInput.setValue(columnsSpacing);
    }

    public void changeColumnHeightByInput(String columnHeight) {
        openGeneralTab();
        TextInput columnHeightInput = new TextInput(By.id("inspector--base--column-height--input"));
        columnHeightInput.setValue(columnHeight);
    }

    public void changeClickAction(String action) {
        openGeneralTab();
        scrollToElementAtTop(By.id("click-action"));
        clickElement(By.xpath("//*[@id='click-action']//button"));
        selectOptionDynamic(By.cssSelector("[id^='inspector--select--']"), action);
    }

    public void changePopupContent(String content) {
        openGeneralTab();
        scrollToElementAtTop(By.id("popup-content"));
        clickElement(By.xpath("//*[@id='popup-content']//button"));
        selectOptionDynamic(By.cssSelector("[id^='inspector--select--']"), content);
    }

    public void changeVideoUrl(String url) {
        openGeneralTab();
        scrollToElementAtTop(By.id("youtube-video-url"));
        By videoUrlInput = new ByChained(By.id("youtube-video-url"), By.tagName("input"));
        new TextInput().setSelector(videoUrlInput).setValue(url);
    }

    public void changeEnableFullWidth(String enable, @Nullable String width) {
        openGeneralTab();
        By enableFullWidthButton = new ByChained(By.id("enable-full-width"), By.xpath("//button[@role='" + enable.toUpperCase() + "']"));
        clickElement(enableFullWidthButton);
        if (enable.toUpperCase().equals("NO") && width != null) {
            new TextInput().setSelector(new ByChained(By.id("image-width"), By.cssSelector("input[type='number']"))).setValue(width);
        }
    }

    public void changeImageRatio(String ratio) {
        openGeneralTab();
        By imageRatioButton = new ByChained(By.id("image-ratio"), By.xpath("//button[@role='" + ratio.toUpperCase() + "']"));
        clickElement(imageRatioButton);
    }

    public void changeImageHeight(String height) {
        openGeneralTab();
        TextInput imageHeightInput = new TextInput(new ByChained(By.id("image-height"), By.cssSelector("input[type='number']")));
        imageHeightInput.setValue(height);
    }

    public void changeImageObjectFit(String objectFit) {
        openGeneralTab();
        SwitchButton objectFitButton = new SwitchButton().setContainer(By.id("image-object-fit"));
        objectFitButton.click(objectFit);
    }

    // ================== Styling ==================

    public void changeContentColorSendKeys(String color) {
        openStylingTab();
        new TextInput()
                .setSelector(new ByChained(By.id("content-color"), By.cssSelector("input[type='text']")))
                .setValue(color);
    }

    public String changeContentColorClick() {
        openStylingTab();
        By contentColorInput = By. id("inspector--content-color--input");
        return openColorPickerAndPickColor(contentColorInput);
    }

    public void changeBackgroundStyle(String style) {
        openStylingTab();
        new SwitchButton()
                .setContainer(By.id("background-style"))
                .click(style);
    }

    public void changeBackgroundColorSendKeys(String color) {
        openStylingTab();
        new TextInput()
                .setSelector(new ByChained(By.id("background-color"), By.cssSelector("input[type='text']")))
                .setValue(color);
    }

    public String changeBackgroundColorClick() {
        openStylingTab();
        By backgroundColorInput = By.id("inspector-background-color--input");
        return openColorPickerAndPickColor(backgroundColorInput);
    }

    public void changeHorizontalAlignment(String alignment) {
        openStylingTab();
        By alignmentButton = By.id("inspector--button-toggle--horizontal-align-" + alignment);
        clickElement(alignmentButton);
    }

    public void changePadding(String padding) {
        openStylingTab();
        By paddingInput = By. id("inspector--spacing--padding");
        new TextInput().setSelector(paddingInput).setValue(padding);
    }

    public void changePadding(String type, String padding) {
        openStylingTab();
        By paddingTypeInput = By.id("inspector--spacing--padding-" + type);
        new TextInput().setSelector(paddingTypeInput).setValue(padding);
    }

    public void changeMargin(String margin) {
        openStylingTab();
        By marginInput = By. id("inspector--spacing--margin");
        new TextInput().setSelector(marginInput).setValue(margin);
    }

    public void changeMargin(String type, String margin) {
        openStylingTab();
        By marginTypeInput = By.id("inspector--spacing--margin-" + type);
        new TextInput().setSelector(marginTypeInput).setValue(margin);
    }

    public void changeFontSize(String size) {
        openStylingTab();
        By fontSizeInput = By.id("inspector--typography--font-size");
        new TextInput().setSelector(fontSizeInput).setValue(size);
    }

    public void changeOpacity(String opacity) {
        openStylingTab();
        By opacityInput = By.id("inspector--opacity--input");
        new TextInput().setSelector(opacityInput).setValue(opacity);
    }

    public void changeTextAlign(String alignment) {
        openStylingTab();
        By textAlignButton = By.id("inspector--button-toggle--text-align-" + alignment);
        clickElement(textAlignButton);
    }

    public void chaneTextDecoration(String decoration) {
        openStylingTab();
        By textDecorationButton = By.id("inspector--button-toggle--text-decoration-" + decoration);
        clickElement(textDecorationButton);
    }

    public void changeTextStyleItalic(String italic) {
        openStylingTab();
        By italicButton = By.id("inspector--button-toggle--font-style-italic");
        clickElement(italicButton);
    }

    public void changeTextStyleBold() {
        openStylingTab();
        By weightButton = By.id("inspector--button-toggle--font-weight-bold");
        clickElement(weightButton);
    }

    public void changeTextStyleDecorationLine(String decoration) {
        openStylingTab();
        By decorationButton = By.id("inspector--button-toggle--text-decoration-line-" + decoration);
        clickElement(decorationButton);
    }

    public void openTypoMoreSetting() {
        By polarisTypoMoreSettingBox = By.xpath("//div[contains(@class, 'Polaris-ShadowBevel') and .//*[@id='inspector--font-weight-selector']]");
        if (!isElementVisible(polarisTypoMoreSettingBox, 1)) {
            clickElement(By.id("typo-more-setting-activator-btn"));
        }
    }


    public void changeFontWeight(String weight) {
        openStylingTab();
        openTypoMoreSetting();
        clickElement(By.id("inspector--font-weight-selector"));
        By weightButton = By.id("inspector--select--" + weight);
        clickElement(weightButton);
    }

    public void changeLineHeightByInput(String lineHeight) {
        openStylingTab();
        openTypoMoreSetting();
        By lineHeightInput = By.id("inspector--line-height--input");
        new TextInput().setSelector(lineHeightInput).setValue(lineHeight);
    }

    public void changeLineSpacingBySlider(String lineSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By slider = By.id("inspector--line-height--slider");
        new SliderInput().setSelector(slider).setValue(lineSpacing);
    }

    public void changeLetterSpacingByInput(String letterSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By letterSpacingInput = By.id("inspector--letter-spacing--input");
        new TextInput().setSelector(letterSpacingInput).setValue(letterSpacing);
    }

    public void changeLetterSpacingBySlider(String letterSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By slider = By.id("inspector--letter-spacing--slider");
        new SliderInput().setSelector(slider).setValue(letterSpacing);
    }

    public void changeTextTransform(String transform) {
        openStylingTab();
        openTypoMoreSetting();
        By transformButton = By.id("inspector--button-toggle--text-transform-" + transform);
        clickElement(transformButton);
    }

    public void changeBorderStyle(String style) {
        openStylingTab();
        By styleButton = By.id("inspector--button-toggle--border-style-" + style);
        clickElement(styleButton);
    }

    public void changeBorderMoreSettingInputValue(By input, String value) {
        openStylingTab();
        scrollToElementAtTop(By.id("BORDER"));
        clickElement(By.id("inspector--border--more-setting--activator"));
        new TextInput().setSelector(input).setValue(value);
        clickElement(By.id("inspector--border--more-setting--activator"));
    }

    public void changeBorderWidth(String borderWidth) {
        By borderWidthInput = By.id("inspector--border--border-width");
        changeBorderMoreSettingInputValue(borderWidthInput, borderWidth);
    }

    public void changeBorderWidth(String type, String borderWidth) {
        By borderWidthInput = By.id("inspector--border--border-" + type +"-width");
        changeBorderMoreSettingInputValue(borderWidthInput, borderWidth);
    }

    public void changeBorderRadius(String borderRadius) {
        By borderRadiusInput = By.id("inspector--border--border-radius");
        changeBorderMoreSettingInputValue(borderRadiusInput, borderRadius);
    }

    public void changeDisplayStyle(String style) {
        openStylingTab();
        By styleButton = By.id("inspector--button-toggle--display-" + style);
        clickElement(styleButton);
    }

}
