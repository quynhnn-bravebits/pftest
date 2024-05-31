package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

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
            clickElement(stylingButton);
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
            clickElement(generalButton);
        }
    }

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
        By fontFamilyDropdown = By.id("inspector--font-family-selector--activator");
        clickElement(fontFamilyDropdown);
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
        By sliderSelector = By.id("inspector--font-size--slider");
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

    @Step("Change font size to {fontSize}")
    public void changeFontSize(String fontSize) {
        openStylingTab();
        By fontSizeInput = By.id("inspector--font-size--input");
        clearAndFillText(fontSizeInput, fontSize);
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
}
