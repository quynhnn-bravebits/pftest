package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;
import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class EditorPageInspector {
    private final By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");

    By generalButton = By.id("general");
    By stylingButton = By.id("styling");

    By contentColorInput = By.id("inspector--content-color--input");
    By colorPickerBox = By.id("inspector--content-color--color-picker-box");
    By fontFamilyActivator = By.id("inspector--font-family--activator");

    By paddingInput = By.id("inspector--spacing--padding");
    By paddingLeftInput = By.id("inspector--spacing--padding-left");
    By paddingTopInput = By.id("inspector--spacing--padding-top");
    By paddingRightInput = By.id("inspector--spacing--padding-right");
    By paddingBottomInput = By.id("inspector--spacing--padding-bottom");

    By marginInput = By.id("inspector--spacing--margin");
    By marginLeftInput = By.id("inspector--spacing--margin-left");
    By marginTopInput = By.id("inspector--spacing--margin-top");
    By marginRightInput = By.id("inspector--spacing--margin-right");
    By marginBottomInput = By.id("inspector--spacing--margin-bottom");

    public void verifyEditorPageInspectorLoaded() {
        verifyElementVisible(By.id("editor--inspector"));
        verifyElementVisible(generalButton);
        verifyElementVisible(stylingButton);
    }

    @Step("Open Styling tab")
    public void openStylingTab() {
        if (!Objects.equals(getAttributeElement(stylingButton, "aria-selected"), "true")) {
            clickElement(stylingButton);
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
        waitForElementVisible(colorPickerBox);
        By colorPicker = By.cssSelector(".Polaris-ColorPicker__ColorLayer");
        By hueSlider = By.cssSelector(".Polaris-ColorPicker__HuePicker .Polaris-ColorPicker__Slidable");
        By alphaSlider = By.cssSelector(".Polaris-ColorPicker__AlphaPicker .Polaris-ColorPicker__Slidable");
        randomClickInsideElement(colorPicker);
        randomClickInsideElement(alphaSlider);
        randomClickInsideElement(hueSlider);
        clickElement(By.id("inspector--content-color--input-Prefix"));
        return getAttributeElement(contentColorInput, "value");
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
//        clearAndFillText(fontSizeInput, fontSize);
    }

    @Step("Change font size to {fontSize}")
    public void changeFontSize(String fontSize) {
        openStylingTab();
        By fontSizeInput = By.id("inspector--font-size--input");
        clearAndFillText(fontSizeInput, fontSize);
    }


}
