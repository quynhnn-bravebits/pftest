package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.RichTextOptionTagName;

import javax.annotation.Nullable;
import java.util.Objects;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.projects.commons.Toast.waitForToast;

// page_url = about:blank
public class EditorPageInspector {
    private final By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");

    By generalButton = By.id("general");
    By stylingButton = By.id("styling");

    By contentColorInput = By.id("inspector--content-color--input");
    By contentColorPickerBox = By.id("inspector--content-color--color-picker-box");
    By fontFamilyActivator = By.id("inspector--font-family--activator");
    By backgroundColorInput = By.id("inspector-background-color--input");
    By backgroundColorPickerBox = By.id("inspector-background-color--color-picker-box");

    By paddingInput = By.id("inspector--spacing--padding");
    By marginInput = By.id("inspector--spacing--margin");

    public void verifyEditorPageInspectorLoaded() {
        verifyElementVisible(By.id("editor--inspector"));
        verifyElementVisible(generalButton);
        verifyElementVisible(stylingButton);
    }

    /*
     * Change font size using range slider
     *
     * @param fontSize font size to change
     * @param sliderSelector selector of the range slider
     *
     */
    public void rangeSlider(int fontSize, By sliderSelector) {
        WebElement slider = waitForElementVisible(sliderSelector);
        int currentFontSize = (int) Double.parseDouble(getAttributeElement(sliderSelector, "aria-valuenow"));
        if (currentFontSize > fontSize) {
            for (int i = 0; i <= currentFontSize - fontSize; i++) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        else if (currentFontSize < fontSize) {
            for (int i = 0; i < fontSize - currentFontSize; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        }
    }

    /*
     * Random click inside element
     *
     * @param by selector of the element
     *
     */
    public void randomClickColorPicker() {
        By colorPicker = By.cssSelector(".Polaris-ColorPicker__ColorLayer");
        By hueSlider = By.cssSelector(".Polaris-ColorPicker__HuePicker .Polaris-ColorPicker__Slidable");
        By alphaSlider = By.cssSelector(".Polaris-ColorPicker__AlphaPicker .Polaris-ColorPicker__Slidable");
        randomClickInsideElement(colorPicker);
        randomClickInsideElement(alphaSlider);
        randomClickInsideElement(hueSlider);
    }

    @Step("Open Styling tab")
    public void openStylingTab() {
        if (!Objects.equals(getAttributeElement(stylingButton, "aria-selected"), "true")) {
            waitForElementClickable(stylingButton);
            sleep(0.1);
            clickElement(stylingButton);
            verifyElementAttributeValue(stylingButton, "aria-selected", "true");
        }
    }

    @Step("Open typo more setting")
    public void openTypoMoreSetting() {
        By polarisTypoMoreSettingBox = By.xpath("//div[contains(@class, 'Polaris-ShadowBevel') and .//*[@id='inspector--font-weight-selector']]");
        if (!isElementVisible(polarisTypoMoreSettingBox, 1)) {
            clickElement(By.id("typo-more-setting-activator-btn"));
        }
    }

    @Step("Open General tab")
    public void openGeneralTab() {
        if (!Objects.equals(getAttributeElement(generalButton, "aria-selected"), "true")) {
            waitForElementClickable(generalButton);
            sleep(0.1);
            clickElement(generalButton);
            verifyElementAttributeValue(generalButton, "aria-selected", "true");
        }
    }

    public int getColumnsPerLine () {
        openGeneralTab();
        By columnsPerLineInput = By.id("inspector--base--columns-per-line--input");
        return Integer.parseInt(getAttributeElement(columnsPerLineInput, "value"));
    }

    public String getRichTextHTMLContent () {
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

//    ================== INSPECTOR ACTIONS ==================
//
//    ================== GENERAL TAB ==================
    @Step("Change text content to {content}")
    public void changeTextContent(String content) {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        verifyElementVisible(textContentInput);
        clearTextCtrlA(textContentInput);
        clearAndFillText(textContentInput, content);
        sleep(0.3);
    }

    @Step("Paste text from clipboard to text box")
    public void pasteClipboardTextContent() {
        openGeneralTab();
        By textContentInput = By.id("pf_text_editor_");
        verifyElementVisible(textContentInput);
        clearText(textContentInput);
        moveCursorToEndOfContent(textContentInput);
        pasteStyleByShortcut();
        sleep(0.3);
    }

    @Step("Select text content {text} and adjust to {option}")
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

    @Step("Change show icon to {showIcon}")
    public void changeShowIcon(String showIcon) {
        openGeneralTab();
        By showIconButton = new ByChained(By.id("show-icon"), By.xpath("//button[@role='" + showIcon.toUpperCase() + "']"));
        clickElement(showIconButton);
    }

    @Step("Change icon position to {position}")
    public void changeIconPosition(String position) {
        System.out.println("Change icon position to " + position);
        openGeneralTab();
        By iconPositionButton = By.xpath("//*[@id='icon-position']//button[@role='" + position.toUpperCase() + "']");
        sleep(0.5);
        clickElement(iconPositionButton);
    }

    @Step("Change icon vertical alignment to {alignment}")
    public void changeIconVerticalAlignment(String alignment) {
        System.out.println("Change icon vertical alignment to " + alignment);
        openGeneralTab();
        By iconVerticalAlignmentButton = By.xpath("//*[@id='icon-vertical-alignment']//button[@role='" + alignment.toUpperCase() + "']");
        sleep(0.5);
        clickElement(iconVerticalAlignmentButton);
    }

    @Step("Change show drop cap to {showDropCap}")
    public void changeShowDropCap(String showDropCap) {
        openGeneralTab();
        scrollToElementAtTop(By.id("show-dropcap"));
        By showDropCapButton = new ByChained(By.id("show-dropcap"), By.xpath("//button[@role='" + showDropCap + "']"));
        clickElement(showDropCapButton);
    }

    @Step("Change drop cap content to {content}")
    public void changeDropCapContent(String content) {
        openGeneralTab();
        scrollToElementAtTop(By.id("dropcap-text"));
        By dropCapContentInput = new ByChained(By.id("dropcap-text"), By.tagName("input"));
        clearAndFillText(dropCapContentInput, content);
    }

    @Step("Add new item")
    public void addNewItemToList() {
        openGeneralTab();
        By addNewItemButton = By.id("sortable-list--add-new-item-btn");
        waitForElementClickable(addNewItemButton);
        clickElement(addNewItemButton);
    }

    @Step("Change columns per line to {columnsPerLine}")
    public void changeColumnsPerLineByInput(String columnsPerLine) {
        openGeneralTab();
        By columnsPerLineInput = By.id("inspector--base--columns-per-line--input");
        clearAndFillText(columnsPerLineInput, columnsPerLine);
    }

    @Step("Change content position to {position}")
    public void changeContentPosition(String position) {
        openGeneralTab();
        By positionButton = By.id("inspector--position--" + position);
        moveToElement(positionButton);
        clickElement(positionButton);
    }

    @Step("Change enable equal height to {enable}")
    public void changeEnableEqualHeight(String enable) {
        openGeneralTab();
        By enableEqualHeightButton = new ByChained(By.id("enable-equal-height"), By.xpath("//button[@role='" + enable + "']"));
        clickElement(enableEqualHeightButton);
    }

    @Step("Change columns spacing to {columnsSpacing}")
    public void changeColumnsSpacingByInput(String columnsSpacing) {
        openGeneralTab();
        By columnsSpacingInput = By.id("inspector--base--columns-spacing--input");
        clearAndFillText(columnsSpacingInput, columnsSpacing);
    }

    @Step("Change column height to {columnHeight}")
    public void changeColumnHeightByInput(String columnHeight) {
        openGeneralTab();
        By columnHeightInput = By.id("inspector--base--column-height--input");
        clearAndFillText(columnHeightInput, columnHeight);
    }

    @Step("Click delete inside Content/Columns")
    public void removeItemFromList() {
        openGeneralTab();
        By deleteButton = By.xpath("//*[@id='CONTENT']//*[starts-with(@id, 'sortable-item-')]//*[contains(@class, 'delete-item-btn')]");
        clickElement(deleteButton);
    }

    @Step("Change click action to {action}")
    public void changeClickAction(String action) {
        openGeneralTab();
        scrollToElementAtTop(By.id("click-action"));
        clickElement(By.xpath("//*[@id='click-action']//button"));
        selectOptionDynamic(By.cssSelector("[id^='inspector--select--']"), action);
    }

    @Step("Change popup content to {content}")
    public void changePopupContent(String content) {
        openGeneralTab();
        scrollToElementAtTop(By.id("popup-content"));
        clickElement(By.xpath("//*[@id='popup-content']//button"));
        selectOptionDynamic(By.cssSelector("[id^='inspector--select--']"), content);
    }

    @Step("Change popup content url to {url}")
    public void changeVideoUrl(String url) {
        openGeneralTab();
        scrollToElementAtTop(By.id("youtube-video-url"));
        By videoUrlInput = new ByChained(By.id("youtube-video-url"), By.tagName("input"));
        clearAndFillText(videoUrlInput, url);
    }

    @Step("Change enable full width to {enable}")
    public void changeEnableFullWidth(String enable, @Nullable String width) {
        openGeneralTab();
        By enableFullWidthButton = new ByChained(By.id("enable-full-width"), By.xpath("//button[@role='" + enable.toUpperCase() + "']"));
        clickElement(enableFullWidthButton);
        if (enable.toUpperCase().equals("YES") && width != null) {
            clearAndFillText(new ByChained(By.id("enable-full-width"), By.cssSelector("input[type='number']")), width);
        }
    }

    @Step("Change image ratio to {ratio}")
    public void changeImageRatio(String ratio) {
        openGeneralTab();
        By imageRatioButton = new ByChained(By.id("image-ratio"), By.xpath("//button[@role='" + ratio.toUpperCase() + "']"));
        clickElement(imageRatioButton);
    }

    @Step("Change image height to {height}")
    public void changeImageHeight(String height) {
        openGeneralTab();
        By imageHeightInput = new ByChained(By.id("image-height"), By.cssSelector("input[type='number']"));
        clearAndFillText(imageHeightInput, height);
    }

    @Step("Change image object fit to {objectFit}")
    public void changeImageObjectFit(String objectFit) {
        openGeneralTab();
        By objectFitButton = new ByChained(By.id("image-object-fit"), By.xpath("//button[@role='" + objectFit.toUpperCase() + "']"));
        clickElement(objectFitButton);
    }

//    ================== STYLING TAB ==================
    @Step("Change content color to {color}")
    public void changeContentColorSendKeys(String color) {
        openStylingTab();
        verifyElementVisible(contentColorInput);
        clearAndFillText(contentColorInput, color);
    }

    @Step("Change content color to random color and return the color value")
    public String changeContentColorClick() {
        openStylingTab();
        clickElement(contentColorInput);
        waitForElementVisible(contentColorPickerBox);
        randomClickColorPicker();
        clickElement(By.id("inspector--content-color--input-Prefix"));
        return getAttributeElement(contentColorInput, "value");
    }

    @Step("Change background color to {color}")
    public void changeBackgroundColorSendKeys(String color) {
        openStylingTab();
        verifyElementVisible(backgroundColorInput);
        clearAndFillText(backgroundColorInput, color);
    }

    @Step("Change background color to random color and return the color value")
    public String changeBackgroundColorClick() {
        openStylingTab();
        clickElement(backgroundColorInput);
        waitForElementVisible(backgroundColorPickerBox);
        randomClickColorPicker();
        clickElement(By.id("inspector-background-color--input-Prefix"));
        return getAttributeElement(backgroundColorInput, "value");
    }

    @Step("Change horizontal alignment to {alignment}")
    public void changeHorizontalAlignment(String alignment) {
        openStylingTab();
        By alignmentButton = By.id("inspector--button-toggle--horizontal-align-" + alignment);
        clickElement(alignmentButton);
    }

    @Step("Change padding to {padding}")
    public void changePadding(Integer padding) {
        openStylingTab();
        verifyElementVisible(paddingInput);
        clearAndFillText(paddingInput, String.valueOf(padding));
    }

    @Step("Change padding {type} to {padding}")
    public void changePadding(String type, Integer padding) {
        openStylingTab();
        By paddingTypeInput = By.id("inspector--spacing--padding-" + type);
        verifyElementVisible(paddingTypeInput);
        clearAndFillText(paddingTypeInput, String.valueOf(padding));
    }

    @Step("Change margin to {margin}")
    public void changeMargin(Integer margin) {
        openStylingTab();
        verifyElementVisible(marginInput);
        clearAndFillText(marginInput, String.valueOf(margin));
    }

    @Step("Change margin {type} to {margin}")
    public void changeMargin(String type, Integer margin) {
        openStylingTab();
        By marginTypeInput = By.id("inspector--spacing--margin-" + type);
        verifyElementVisible(marginTypeInput);
        clearAndFillText(marginTypeInput, String.valueOf(margin));
    }

    @Step("Open select image modal")
    public void openSelectImageModal() {
        clickElement(By.id("media-manager--select-image--activator"));
        waitForElementTextContains(modal, "Select image");
    }

    @Step("Upload image from {path} to media files")
    public void uploadImageToMediaFiles(String path) {
        waitForElementVisible(By.className("Polaris-DropZone-FileUpload__Action"));
        // Get media number before upload
        By media = new ByChained(modal, By.xpath("//div[@media]"));
        int previousMediaNumber = getWebElements(media).size();
        // Add image from local
        uploadFileWithLocalForm(By.className("Polaris-DropZone-FileUpload__Action"),path);
        waitForToast("Media uploaded", 30);
        // Get media number after upload success
        int currentMediaNumber = getWebElements(media).size();
        verifyTrue(currentMediaNumber == (previousMediaNumber + 1), "Media file is not uploaded");
    }

    @Step("Upload image from url {url} to media files")
    public void uploadImageFromUrlToMediaFiles(String url) {
        By addUrlButton = new ByChained(modal, By.xpath("//button/*[text()='Add from URL']"));
        waitForElementVisible(addUrlButton);
        // Get media number before upload
        By media = new ByChained(modal, By.xpath("//div[@media]"));
        int previousMediaNumber = getWebElements(media).size();
        // Add image from url
        clickElement(addUrlButton);
        clearAndFillText(By.id("upload-url"), url);
        waitForElementClickable(By.id("media-manager--media-selector-modal--select"));
        clickElement(By.id("media-manager--media-selector-modal--select"));
        waitForToast("Media uploaded", 30);
        // Get media number after upload success
        int currentMediaNumber = getWebElements(media).size();
        verifyTrue(currentMediaNumber == (previousMediaNumber + 1), "Media file is not uploaded");
    }

    @Step("Select image from media files")
    public String selectImageFromMediaFiles() {
        By image = new ByChained(modal, By.xpath("//div[@media]"));
        waitForElementVisible(image);
        waitForElementClickable(image);
        clickElement(image);
        String url = getAttributeElement(new ByChained(image, By.tagName("img")), "src");
        sleep(0.5);
        verifyElementClickable(By.id("media-manager--media-selector-modal--select"));
        clickElement(By.id("media-manager--media-selector-modal--select"));
        sleep(0.5);
        verifyElementNotVisible(modal);
        return url;
    }

    public String openModalAndSelectImageFromMediaFiles() {
        openSelectImageModal();
        return selectImageFromMediaFiles();
    }

    /**
     * Open select image modal, upload image from computer and select it from media files
     *
     * @param path local path of the image
     * @return shopify url of the selected image
     */
    public String selectNewUploadedImageFromComputer(String path) {
        openSelectImageModal();
        uploadImageToMediaFiles(path);
        return selectImageFromMediaFiles();
    }

    /**
     * Open select image modal, upload image from url and select it from media files
     *
     * @param url url of the image
     * @return shopify url of the selected image
     */
    public String selectNewUploadedImageFromUrl(String url) {
        openSelectImageModal();
        uploadImageFromUrlToMediaFiles(url);
        return selectImageFromMediaFiles();
    }

    @Step("Change background size to {size}")
    public void changeBackgroundSize(String size) {
        openStylingTab();
        scrollToElementAtTop(By.id("BACKGROUND"));
        clickElement(By.id("inspector--background--more-setting--activator"));
        moveToElement(By.id("background-size"));
        clickElement(By.id("inspector--button-toggle--background-size-" + size.toLowerCase()));
        clickElement(By.id("inspector--background--more-setting--activator"));
    }

    @Step("Change background position to {position}")
    public void changeBackgroundPosition(String position) {
        openStylingTab();
        scrollToElementAtTop(By.id("BACKGROUND"));
        clickElement(By.id("inspector--background--more-setting--activator"));
        moveToElement(By.id("background-position"));
        clickElement(By.id("inspector--position--" + position.toLowerCase()));
        clickElement(By.id("inspector--background--more-setting--activator"));
    }

    @Step("Add font to inspector")
    public void addFontToInspector(By by) {
        openStylingTab();
        clickElement(fontFamilyActivator);
        waitForElementVisible(modal);
        if (!verifyElementChecked(new ByChained(by,  By.tagName("input")))) {
            clickElement(by);
            clickPolarisButtonHasText("Select");
        }
        else {
            clickPolarisButtonHasText("Cancel");
        }
        verifyElementNotVisible(modal);
    }

    /**
     * Select font family from inspector
     * @param fontFamily font family name
     */
    @Step("Select font family {fontFamily}")
    public void selectFontFamily(String fontFamily) {
        openStylingTab();
        scrollToElementAtTop(By.id("TYPOGRAPHY"));
        By fontFamilyDropdown = By.id("inspector--font-family-selector--activator");
        clickElement(fontFamilyDropdown);
        waitForElementVisible(By.id("inspector--font-family-selector--font-default"));
        By fontSelector = By.cssSelector("[id^='inspector--font-family-selector---font']");
        if (verifyOptionDynamicExist(fontSelector, fontFamily)) {
            selectOptionDynamic(fontSelector, fontFamily);
        } else {
            addFontToInspector(By.id(fontFamily));
            clickElement(fontFamilyDropdown);
            selectOptionDynamic(fontSelector, fontFamily);
        }
    }

    @Step("Change font size to {fontSize}")
    public void changeFontSize(int fontSize) {
        openStylingTab();
        By slider = By.id("inspector--font-size--slider");
        rangeSlider(fontSize, slider);
    }

    @Step("Change font size to {fontSize}")
    public void changeFontSize(String fontSize) {
        openStylingTab();
        By fontSizeInput = By.id("inspector--font-size--input");
        clearAndFillText(fontSizeInput, fontSize);
    }

    @Step("Change opacity to {opacity}")
    public void changeOpacity(int opacity) {
        openStylingTab();
        By slider = By.id("inspector--opacity--slider");
        rangeSlider(opacity, slider);
    }

    @Step("Change opacity to {opacity}")
    public void changeOpacity(String opacity) {
        openStylingTab();
        By opacityInput = By.id("inspector--opacity--input");
        clearAndFillText(opacityInput, opacity);
    }

    @Step("Change text alignment to {alignment}")
    public void changeTextAlign(String alignment) {
        openStylingTab();
        By alignmentButton = By.id("inspector--button-toggle--text-align-" + alignment);
        clickElement(alignmentButton);
    }

    @Step("Change text decoration to {decoration}")
    public void changeTextDecoration(String decoration) {
        openStylingTab();
        By decorationButton = By.id("inspector--button-toggle--text-decoration-line-" + decoration);
        clickElement(decorationButton);
    }

    @Step("Change text style to {style}")
    public void changeTextStyleItalic(String style) {
        openStylingTab();
        By styleButton = By.id("inspector--button-toggle--font-style-" + style);
        clickElement(styleButton);
    }

    @Step("Change font weight to {weight}")
    public void changeTextStyleBold(String weight) {
        openStylingTab();
        By weightButton = By.id("inspector--button-toggle--font-weight-" + weight);
        clickElement(weightButton);
    }

    @Step("Change text decoration to {decoration}")
    public void changeTextStyleDecorationLine(String decoration) {
        openStylingTab();
        By decorationButton = By.id("inspector--button-toggle--text-decoration-line-" + decoration);
        clickElement(decorationButton);
    }

    @Step("Change font weight to {weight}")
    public void changeFontWeight(String weight) {
        openStylingTab();
        openTypoMoreSetting();
        clickElement(By.id("inspector--font-weight-selector"));
        By weightButton = By.id("inspector--select--" + weight);
        clickElement(weightButton);
    }

    @Step("Change line height to {lineHeight}")
    public void changeLineHeightByInput(String lineHeight) {
        openStylingTab();
        openTypoMoreSetting();
        By lineHeightInput = By.id("inspector--line-height--input");
        clearAndFillText(lineHeightInput, lineHeight);
    }

    @Step("Change line spacing to {lineSpacing}")
    public void changeLineSpacingBySlider(int lineSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By slider = By.id("inspector--line-height--slider");
        rangeSlider(lineSpacing, slider);
    }

    @Step("Change letter spacing to {letterSpacing}")
    public void changeLetterSpacingByInput(String letterSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By letterSpacingInput = By.id("inspector--letter-spacing--input");
        clearAndFillText(letterSpacingInput, letterSpacing);
    }

    @Step("Change letter spacing to {letterSpacing}")
    public void changeLetterSpacingBySlider(int letterSpacing) {
        openStylingTab();
        openTypoMoreSetting();
        By slider = By.id("inspector--letter-spacing--slider");
        rangeSlider(letterSpacing, slider);
    }

    @Step("Change text transform to {transform}")
    public void changeTextTransform(String transform) {
        openStylingTab();
        openTypoMoreSetting();
        By transformButton = By.id("inspector--button-toggle--text-transform-" + transform);
        clickElement(transformButton);
    }

    @Step("Change border style to {style}")
    public void changeBorderStyle(String style) {
        openStylingTab();
        By styleButton = By.id("inspector--button-toggle--border-style-" + style);
        clickElement(styleButton);
    }


    @Step("Change border width to {borderWidth}")
    public void changeBorderWidth(String borderWidth) {
        openStylingTab();
        scrollToElementAtTop(By.id("BORDER"));
        clickElement(By.id("inspector--border--more-setting--activator"));
        By borderWidthInput = By.id("inspector--border--border-width");
        clearAndFillText(borderWidthInput, borderWidth);
        clickElement(By.id("inspector--border--more-setting--activator"));
    }

    @Step("Change border {type} width to {borderWidth}")
    public void changeBorderWidth(String type, String borderWidth) {
        openStylingTab();
        scrollToElementAtTop(By.id("BORDER"));
        clickElement(By.id("inspector--border--more-setting--activator"));
        By borderWidthInput = By.id("inspector--border--border-" + type +"-width");
        clearAndFillText(borderWidthInput, borderWidth);
        clickElement(By.id("inspector--border--more-setting--activator"));
    }

    @Step("Change border radius to {borderRadius}")
    public void changeBorderRadius(String borderRadius) {
        openStylingTab();
        scrollToElementAtTop(By.id("BORDER"));
        clickElement(By.id("inspector--border--more-setting--activator"));
        By borderRadiusInput = By.id("inspector--border--border-radius");
        clearAndFillText(borderRadiusInput, borderRadius);
        clickElement(By.id("inspector--border--more-setting--activator"));
    }

    @Step("Change border {type} radius to {borderRadius}")
    public void changeBorderRadius(String type, String borderRadius) {
        openStylingTab();
        scrollToElementAtTop(By.id("BORDER"));
        clickElement(By.id("inspector--border--more-setting--activator"));
        By borderRadiusInput = By.id("inspector--border--border-" + type +"-radius");
        clearAndFillText(borderRadiusInput, borderRadius);
        clickElement(By.id("inspector--border--more-setting--activator"));
    }

    @Step("Change display style to {style}")
    public void changeDisplayStyle(String style) {
        openStylingTab();
        By styleButton = By.id("inspector--button-toggle--display-" + style);
        clickElement(styleButton);
    }
}
