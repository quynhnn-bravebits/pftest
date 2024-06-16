package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pftest.constants.FrameworkConstants;
import org.pftest.constants.ModalConstants;
import org.pftest.driver.DriverManager;
import org.pftest.enums.PageType;
import org.pftest.enums.RichTextOptionTagName;
import org.pftest.projects.commons.Badge;
import org.pftest.projects.commons.Toast;

import javax.annotation.Nullable;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static org.pftest.constants.ModalConstants.BEFORE_SAVE_MODAL;
import static org.pftest.constants.UrlConstants.*;
import static org.pftest.helpers.ClipboardHelper.getClipboardContent;
import static org.pftest.keywords.WebUI.*;
import static org.pftest.utils.CommonUtils.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/editor?type=page&id=1
public class EditorPage extends Toast {

    private final PageAssignmentModal pageAssignmentModal = new PageAssignmentModal();
    private final EditorPageInspector editorPageInspector = new EditorPageInspector();
    private final EditorPageSandbox editorPageSandbox = new EditorPageSandbox();
    private final Badge badge = new Badge();

    private final By headerBar = By.id("editor-header-bar");
    private final By inspector = By.id("editor--inspector");
    private final By pageOutline = By.id("page-outline-section");
    private final By editorDnd = By.id("pf-sandbox");
    private final By pageTitle = By.id("editor-header-bar--page-title");
    private final By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");
    private final By catalogMenu = By.id("catalog-menu-section");
    private final By catalogList = By.id("catalog-items");
    private final By historyActivatorButton = By.id("revisions--activator-btn");

    private final By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");
    private final By crispIcon = By.xpath("//*[@id='crisp-chatbox']//a[@aria-label='Open chat']");
    private final By openLiveChatButton = By.id("open-live-chat-activator-btn");
    private final By undoButton = By.id("pf-undo-btn");
    private final By redoButton = By.id("pf-redo-btn");

    private final By catalogAddElementButton = By.xpath("//button[@id='catalog--add-element-btn']");
    private final By catalogAddShopifyElementButton = By.xpath("//button[@id='catalog--add-shopify-element-btn']");
    private final By catalogAdd3rdPartyElementButton = By.xpath("//button[@id='catalog--add-3rd-party-element']");
    private final By showPageOutlineButton = By.xpath("//button[@id='outline--activator']");

    private final By editorBackButton = By.id("editor-header-bar--exit-btn");
    private final By canvasSettingButton = By.id("canvas-size-setting-activator-btn");
    private final By editorSettingButton = By.id("editor-header-bar--editor-setting--activator");
    private final By moreSettingsButton = By.id("editor-header-bar--more-settings-btn");
    private final By helpAndSupportButton = By.xpath("(//*[@id=\"editor-header-bar--content\"]//button)[last()]");

    private final By fitViewportCheckbox = By.xpath("//*[@id=\"canvas-size-setting\"]//*/label[.//*[text()='Fit viewport']]");
    private final By showCanvasSizeCheckbox = By.xpath("//label[@for=\"show-canvas-size-setting-item\"]");
    private final By enableAutoSaveCheckbox = By.xpath("//label[@for=\"auto-save--enable\"]");
    private final By goToThemeEditorButton = By.id("go-to-theme-editor-action-btn");
    private final By browseHelpCenterButton = By.xpath("//button//*[text()='Browse Help Center']");
    private final By startLiveChat = By.xpath("//button//*[text()='Start live chat']");
    private final By joinCommunityButton = By.xpath("//button//*[text()='Join PageFly Community']");
    private final By watchVideoTutorialsButton = By.xpath("//button//*[text()='Watch video tutorials']");

    private final By editorOverlay = By.xpath("//div[contains(@class, 'Editor-Overlay')]");


    @Step("Open new {0} page editor")
    public void openNewPageEditor(PageType pageType) {
        String URL = "https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/editor?type=" + pageType.name().toLowerCase() + "&id=" + UUID.randomUUID();
        openWebsite(URL);
        switchToPageFlyFrame();
    }

    @Step("Get canvas html source")
    public String getCanvasHtml() {
        switchToDragAndDropFrame();
        String html = DriverManager.getDriver().getPageSource();
        switchToPageFlyFrame();
        return htmlSourceSpecialElementProcessing(html);
    }

    @Step("Get canvas html processed source")
    public String getCanvasHtmlProcessed() {
        switchToDragAndDropFrame();
        String html = DriverManager.getDriver().getPageSource();
        switchToPageFlyFrame();
        return htmlSourceProcessing(html);
    }

    @Step("Get history preview source")
    public String getHistoryPreviewSource() {
        switchToHistoryPreviewFrame();
        String html = DriverManager.getDriver().getPageSource();
        switchToPageFlyFrame();
        return htmlSourceProcessing(html);
    }

    public void verifyEditorDndLoaded() {
        verifyElementVisible(editorDnd);
        switchToDragAndDropFrame();
        waitForPageLoaded();
        sleep(2);
        switchToPageFlyFrame();
    }

    @Step("Wait for the page to be loaded")
    public void verifyEditorPageLoaded() {
        waitForPageLoaded();
        verifyElementVisible(pageTitle);
        verifyElementVisible(inspector);
        verifyElementVisible(headerBar);
        verifyEditorDndLoaded();
    }

    @Step("Verify the editor overlay is visible")
    public void verifyOverlayVisible() {
        verifyElementVisible(editorOverlay);
        verifyElementTextContains(editorOverlay, "The browser window is too small for the editor");
        verifyElementTextContains(editorOverlay, "Please resize the browser to be at least 1024px wide for the editor to work again.");
    }

    @Step("Verify the editor overlay is hidden")
    public void verifyOverlayHidden() {
        verifyElementNotVisible(editorOverlay);
    }

//    ================== Editor Header ==================

    @Step("Open templates from editor page templates")
    public void openAndSelectPageTemplate() {
        // open page templates from editor
        clickElement(moreSettingsButton);
        clickElement(By.id("template-action-btn"));
        PageListingScreen pageListingScreen = new PageListingScreen();
        // select template
        pageListingScreen.selectPageTemplate();
        // confirm select template modal
        WebElement publishProductModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.SELECT_PAGE_TEMPLATE_MODAL.TITLE);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.SELECT_PAGE_TEMPLATE_MODAL.PRIMARY_BUTTON + "']");
        By checkbox = By.cssSelector(".Polaris-Checkbox");
        clickElement(checkbox);
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);
    }

    @Step("Click Undo button")
    public void clickUndoButton() {
        waitForElementClickable(undoButton);
        sleep(0.1);
        clickElement(undoButton);
    }

    @Step("Verify Undo button is disabled")
    public void verifyUndoButtonDisabled() {
        verifyButtonIsDisabled(undoButton);
    }

    @Step("Click Redo button")
    public void clickRedoButton() {
        waitForElementClickable(redoButton);
        sleep(0.1);
        clickElement(redoButton);
    }

    @Step("Verify Redo button is disabled")
    public void verifyRedoButtonDisabled() {
        verifyButtonIsDisabled(redoButton);
    }

    @Step("Enable autosave")
    public void toggleEnableAutoSave() {
        clickElement(editorSettingButton);
        clickElement(enableAutoSaveCheckbox);
        verifyElementChecked(new ByChained(enableAutoSaveCheckbox, By.tagName("input")));
    }

    public void verifyEnableAutoSave() {
        clickElement(editorSettingButton);
        verifyElementChecked(new ByChained(enableAutoSaveCheckbox, By.tagName("input")));
    }

    @Step("Click back button to exit the editor")
    public void backToPageListingScreen() {
        waitForElementClickable(editorBackButton);
        clickElement(editorBackButton);
    }

    @Step("Toggle Show Canvas Size")
    public void toggleShowCanvasSize() {
        clickElement(editorSettingButton);
        clickElement(showCanvasSizeCheckbox);
        verifyElementChecked(new ByChained(showCanvasSizeCheckbox, By.tagName("input")));
        clickElement(editorSettingButton);
    }

    @Step("Verify Show Canvas Size")
    public void verifyShowCanvasSize() {
        clickElement(editorSettingButton);
        verifyElementChecked(new ByChained(showCanvasSizeCheckbox, By.tagName("input")));
    }

    @Step("Toggle Fit Viewport")
    public void toggleFitViewport() {
        clickElement(canvasSettingButton);
        clickElement(fitViewportCheckbox);
        verifyElementChecked(new ByChained(fitViewportCheckbox, By.tagName("input")));
        clickElement(canvasSettingButton);
    }

    @Step("Verify Fit Viewport")
    public void verifyFitViewport() {
        clickElement(canvasSettingButton);
        verifyElementChecked(new ByChained(fitViewportCheckbox, By.tagName("input")));
    }

    public void verifyCrispChatBoxOpened() {
        verifyElementVisible(crispChatBox);
        Dimension size = getSizeElement(crispChatBox);
        verifyTrue(size.height > 0 && size.width > 0);
    }

    public void verifyCrispChatBoxClosed() {
        verifyElementNotVisible(crispChatBox);
        Dimension size = getSizeElement(crispChatBox);
        verifyTrue(size.height * size.width == 0);
    }

    @Step("Open Browse Help Center")
    public void openBrowserHelpCenter() {
        clickElement(helpAndSupportButton);
        clickElement(browseHelpCenterButton);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToWindowOrTabByPosition(1);
        wait.until(ExpectedConditions.urlToBe(HELP_CENTER_URL));
        closeCurrentWindow();
    }

    @Step("Open Live Chat Box")
    public void startLiveChat() {
        clickElement(helpAndSupportButton);
        clickElement(startLiveChat);
        verifyCrispChatBoxOpened();
    }

    @Step("Join PageFly Community")
    public void joinPageFlyCommunity() {
        clickElement(helpAndSupportButton);
        clickElement(joinCommunityButton);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToWindowOrTabByPosition(1);
        wait.until(ExpectedConditions.urlToBe(PF_COMMUNITY_URL));
        closeCurrentWindow();
    }

    @Step("Watch Video Tutorials")
    public void watchVideoTutorials() {
        clickElement(helpAndSupportButton);
        clickElement(watchVideoTutorialsButton);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToWindowOrTabByPosition(1);
        wait.until(ExpectedConditions.urlToBe(VIDEO_TUTORIALS_URL));
        closeCurrentWindow();
    }

    public void openLiveChat() {
        clickElement(openLiveChatButton);
        verifyCrispChatBoxOpened();
    }

    @Step("Verify Go To Theme Editor Button is enabled")
    public void verifyGoToThemeEditorButtonEnabled() {
        clickElement(moreSettingsButton);
        verifyElementClickable(goToThemeEditorButton);
    }

    @Step("Verify Go To Theme Editor Button is disabled")
    public void verifyGoToThemeEditorButtonDisabled() {
        clickElement(moreSettingsButton);
        verifyButtonIsDisabled(goToThemeEditorButton);
    }

    public void goToThemeEditor() {
        if (!verifyElementVisible(goToThemeEditorButton)) {
            clickElement(moreSettingsButton);
        }
        clickElement(goToThemeEditorButton);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToWindowOrTabByPosition(1);
        wait.until(ExpectedConditions.urlContains(SHOPIFY_BASE_URL + "/themes"));
        closeCurrentWindow();
    }

//    ================== Page Title ==================

    @Step("Change the page title to {0}")
    public void changePageTitle(String title) {
        clickElement(pageTitle);
        clearAndFillTextNotReachableByKeyboard(pageTitle, title);
    }

    @Step("Verify the page title is {0}")
    public void verifyPageEditorTitle(String title) {
        verifyElementTextEquals(pageTitle, title);
    }

    @Step("Verify page is Published")
    public void verifyPageIsPublished() {
        badge.verifyPublishedBadge(30);
    }

    @Step("Verify page is Unpublished")
    public void verifyPageIsUnpublished() {
        badge.verifyUnpublishedBadge();
    }

    @Step("Verify page is Saving")
    public void verifyPageIsSaving() {
        badge.verifySavingBadge();
    }

    @Step("Verify page is Saved")
    public void verifyPageIsSaved() {
        badge.verifySavedBadge(30);
    }


//    ================== Page Outline ==================

    @Step("Show Page Outline")
    public void showPageOutline() {
        if (!isElementVisible(pageOutline, 3)) {
            clickElement(showPageOutlineButton);
            waitForElementVisible(pageOutline);
        }
    }

    @Step("Hide Page Outline")
    public void hidePageOutline() {
        if (isElementVisible(pageOutline, 3)) {
            clickElement(showPageOutlineButton);
            sleep(0.5);
            verifyElementNotVisible(pageOutline);
        }
    }

    // @Todo get size return value is not actual size
    @Step("Toggle Page Outline and check canvas size")
    public void togglePageOutlineAndCheckCanvasSize() {
        hidePageOutline();
        Dimension canvasSize = getSizeElement(editorDnd);
        int canvasWidth = canvasSize.width;
        showPageOutline();
        Dimension canvasSizeAfter = getSizeElement(editorDnd);
        Dimension pageOutlineSize = getSizeElement(pageOutline);
        int canvasWidthAfter = canvasSizeAfter.width;
        int pageOutlineWidth = pageOutlineSize.width;
        System.out.println("Canvas width before: " + canvasWidth + " Canvas width after: " + canvasWidthAfter + " Page Outline width: " + pageOutlineWidth);
        System.out.println(canvasWidth - pageOutlineWidth + " " + canvasWidthAfter);
        assert canvasWidthAfter == canvasWidth - pageOutlineWidth;
    }

//    ================== Page Inspector ==================

    @Step("Change text content and verify selected element has the correct text content")
    public void changeTextContent(String text) {
        editorPageInspector.changeTextContent(text);
        String id = getSelectedElementId();
        switchToDragAndDropFrame();
        verifyElementTextEquals(By.xpath("//*[@data-pf-id='" + id + "']"), text);
        switchToPageFlyFrame();
    }

    @Step("Select text content {0} in the rich text editor and make it {1}")
    public void selectAndAdjustTextContent(String text, RichTextOptionTagName... options) {
        editorPageInspector.selectAndAdjustTextContent(text, options);
        // verify text content is adjusted
        String id = getSelectedElementId();
        String richTextContent = editorPageInspector.getRichTextHTMLContent();
        String editorElementContent = editorPageSandbox.getSelectedElementHTMLContent(id);
        verifyEquals(richTextContent, editorElementContent);
    }

    @Step("Change enable show icon to {0} and verify selected element has show correct icon")
    public void changeShowIcon(String showIcon, @Nullable String _position, @Nullable String _verticalAlignment) {
        editorPageInspector.changeShowIcon(showIcon);
        String id = getSelectedElementId();
        String position = _position;
        String verticalAlignment = _verticalAlignment;

        if (_position != null) {
            editorPageInspector.changeIconPosition(_position);
        } else {
            position =  editorPageInspector.getIconPosition();
        }

        if (_verticalAlignment != null) {
            editorPageInspector.changeIconVerticalAlignment(_verticalAlignment);
        } else if (!Objects.equals(_position, "TOP")) {
            verticalAlignment = editorPageInspector.getIconVerticalAlignment();
        }

        editorPageSandbox.verifySelectedElementHasIcon(id, position, verticalAlignment);
    }

    @Step("Paste from clipboard into selected element")
    public void pasteIntoSelectedElement() {
        String clipboardContent = getClipboardContent();
        assert clipboardContent != null;
        String id = getSelectedElementId();
        editorPageInspector.pasteClipboardTextContent();
        verifyEquals(clipboardContent, editorPageSandbox.getSelectedElementHTMLContent(id));
    }

    @Step("Change columns per line to {0} and verify selected element has the correct columns per line")
    public void changeColumnsPerLine_Input(String number) {
        editorPageInspector.changeColumnsPerLineByInput(number);
        String id = getSelectedElementId();
        int colsNum = Integer.parseInt(number);
        int min = 1, max = 12;
        int expected = colsNum < min ? min : Math.min(max, colsNum);
        DecimalFormat df = new DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.FLOOR);
        // @todo: find solution to verify number of columns per line
//        editorPageSandbox.verifySelectedElementHasCssAttribute(id, "--c-lg", df.format(12.0/expected) );

        String expectedError = colsNum < min ? "The minimum possible value is: 1" : colsNum > max ? "The maximum possible value is: 12" : null;
        By errorField = By.xpath("//*[@id='fieldIdError' and contains(text(), '" + expectedError + "')]");
        if (expectedError != null) {
            verifyElementVisible(errorField);
        } else {
            verifyElementNotVisible(errorField);
        }
    }

    @Step("Change content position to {0} and verify selected element has the correct content position")
    public void changeContentPosition(String position) {
        editorPageInspector.changeContentPosition(position);
        String id = getSelectedElementId();
        String justifyContent = "" + position.charAt(0);
        String alignItems = "" + position.charAt(1);
        @Nullable String expectedJustifyContent = switch (justifyContent) {
            case "l" -> "flex-start";
            case "c" -> "center";
            case "r" -> "flex-end";
            default -> null;
        };
        @Nullable String expectedAlignItems = switch (alignItems) {
            case "t" -> "flex-start";
            case "m" -> "center";
            case "b" -> "flex-end";
            default -> null;
        };
        if (position.equals("lt")) {
            editorPageSandbox.verifySelectedElementNotHaveCssAttribute(id, "justify-content");
            editorPageSandbox.verifySelectedElementNotHaveCssAttribute(id, "align-items");
            return;
        }
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "justify-content", expectedJustifyContent);
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "align-items", expectedAlignItems);
    }

    @Step("Change enable equal height to {0} and verify selected element has the correct equal height")
    public void changeEnableEqualHeight(String enableEqualHeight) {
        editorPageInspector.changeEnableEqualHeight(enableEqualHeight);
        String id = getSelectedElementId();
        editorPageSandbox.verifyElementsHeightEqual(id, getSelectedElementType());
    }

    @Step("Change columns spacing to {0} and verify selected element has the correct columns spacing")
    public void changeColumnsSpacingInput(String spacing) {
        editorPageInspector.changeColumnsSpacingByInput(spacing);
        String id = getSelectedElementId();
        int min = 0, max = 60;
        int spacingValue = Math.abs(Integer.parseInt(spacing));
        int expected = spacingValue < min ? min : Math.min(max, spacingValue);

        editorPageSandbox.verifySelectedElementHasStyleAttributeValue(id, "--s-lg", truncateDecimal(expected / 2.0, 1) + "px");

        String expectedError = spacingValue < min ? "The minimum possible value is: 0" : spacingValue > max ? "The maximum possible value is: 60" : null;
        By errorField = By.xpath("//*[@id='fieldIdError' and contains(text(), '" + expectedError + "')]");
        if (expectedError != null) {
            verifyElementVisible(errorField);
        } else {
            verifyElementNotVisible(errorField);
        }
    }

    @Step("Change content color and verify selected element has the correct color")
    public void changeContentColor() {
        String targetColor = convertRGBtoRGBA(editorPageInspector.changeContentColorClick());
        System.out.println("targetColor = " + targetColor);
        String id = getSelectedElementId();
        System.out.println("id = " + id);
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "color", targetColor);
    }

    @Step("Change content color to {0} and verify selected element has the correct color")
    public void changeContentColor(String color) {
        String targetColor = convertRGBtoRGBA(color);
        editorPageInspector.changeContentColorSendKeys(color);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "color", targetColor);
    }

    @Step("Change padding and verify selected element has the correct padding")
    public void changePaddingValue(Integer value) {
        editorPageInspector.changePadding(value);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "padding", value + "px");
    }

    @Step("Change padding {0} and verify selected element has the correct padding")
    public void changePaddingValue(String type, Integer value) {
        editorPageInspector.changePadding(type, value);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "padding-" + type, value + "px");
    }

    @Step("Change margin and verify selected element has the correct margin")
    public void changeMarginValue(Integer value) {
        editorPageInspector.changeMargin(value);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "margin", value + "px");
    }

    @Step("Change margin {0} and verify selected element has the correct margin")
    public void changeMarginValue(String type, Integer value) {
        editorPageInspector.changeMargin(type, value);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "margin-" + type, value + "px");
    }

    @Step("Change font and verify selected element has the correct font")
    public void changeFontFamily(String fontFamily) {
        editorPageInspector.selectFontFamily(fontFamily);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-family", fontFamily);
    }

    @Step("Change font size and verify selected element has the correct font size")
    public void changeFontSize_Slide(Integer fontSize) {
        editorPageInspector.changeFontSize(fontSize);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-size", fontSize + "px");
    }

    @Step("Change font size and verify selected element has the correct font size")
    public void changeFontSize_Input(String fontSize) {
        editorPageInspector.changeFontSize(fontSize);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-size", fontSize + "px");
    }

    @Step("Change opacity and verify selected element has the correct opacity")
    public void changeOpacity_Slide(Integer opacity) {
        editorPageInspector.changeOpacity(opacity);
        String id = getSelectedElementId();
        int expected = opacity < 20 ? 20 : Math.min(100, opacity);
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "opacity", String.valueOf(expected / 100.0).replaceAll("\\.0$", ""));
    }

    @Step("Change opacity and verify selected element has the correct opacity")
    public void changeOpacity_Input(String opacityStr) {
        editorPageInspector.changeOpacity(opacityStr);
        String id = getSelectedElementId();
        int opacity = Integer.parseInt(opacityStr);
        int expected = opacity < 20 ? 20 : Math.min(100, opacity);
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "opacity", String.valueOf(expected / 100.0).replaceAll("\\.0$", ""));

        String expectedError = opacity < 20 ? "The minimum possible value is: 20" : opacity > 100 ? "The maximum possible value is: 100" : null;
        By errorField = By.xpath("//*[@id='fieldIdError' and contains(text(), '" + expectedError + "')]");
        if (expectedError != null) {
            verifyElementVisible(errorField);
        }
    }

    @Step("Change text align and verify selected element has the correct text align")
    public void changeTextAlignment(String textAlign) {
        editorPageInspector.changeTextAlign(textAlign);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "text-align", textAlign);
    }

    @Step("Change text decoration and verify selected element has the correct text decoration")
    public void changeTextDecoration(String textDecoration) {
        editorPageInspector.changeTextDecoration(textDecoration);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "text-decoration-line", textDecoration);
    }

    @Step("Toggle off text decoration and verify selected element has removed text decoration attribute")
    public void toggleOffTextDecoration(String textDecoration) {
        editorPageInspector.changeTextDecoration(textDecoration);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "text-decoration-line", textDecoration);
    }

    @Step("Change text style and verify selected element has the correct text style")
    public void changeTextStyleItalic(String textStyle) {
        editorPageInspector.changeTextStyleItalic(textStyle);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-style", textStyle);
    }

    @Step("Toggle off text style and verify selected element has removed text style attribute")
    public void toggleOffTextStyleItalic(String textStyle) {
        editorPageInspector.changeTextStyleItalic(textStyle);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "font-style", textStyle);
    }

    @Step("Change font weight and verify selected element has the correct font weight")
    public void changeTextStyleBold(String fontWeight) {
        editorPageInspector.changeTextStyleBold(fontWeight);
        String id = getSelectedElementId();
        // In CSS, the "bold" keyword is equivalent to the numerical value 700.
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-weight", "700");
    }

    @Step("Toggle off font weight and verify selected element has removed font weight attribute")
    public void toggleOffTextStyleBold(String fontWeight) {
        editorPageInspector.changeTextStyleBold(fontWeight);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "font-weight", fontWeight);
    }

    @Step("Change text decoration line to {0} and verify selected element has the correct text decoration line")
    public void changeTextStyleDecorationLine(String textDecoration) {
        editorPageInspector.changeTextStyleDecorationLine(textDecoration);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "text-decoration-line", textDecoration);
    }

    @Step("Toggle off text decoration line and verify selected element has removed text decoration line attribute")
    public void toggleOffTextStyleDecorationLine(String textDecoration) {
        editorPageInspector.changeTextStyleDecorationLine(textDecoration);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "text-decoration-line", textDecoration);
    }

    @Step("Change font weight to {0} and verify selected element has the correct font weight")
    public void changeFontWeight(String fontWeight) {
        editorPageInspector.changeFontWeight(fontWeight);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "font-weight", fontWeight);
    }

    @Step("Change line height to {0} and verify selected element has the correct line height")
    public void changeLineHeightByInput(String lineHeight) {
        editorPageInspector.changeLineHeightByInput(lineHeight);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "line-height", lineHeight + "px");
    }

    @Step("Change line height to {0} and verify selected element has the correct line height")
    public void changeLineHeightBySlider(String lineHeight) {
        editorPageInspector.changeLineSpacingBySlider(Integer.parseInt(lineHeight));
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "line-height", lineHeight + "px");
    }

    @Step("Change letter spacing to {0} and verify selected element has the correct line spacing")
    public void changeLetterSpacingByInput(String letterSpacing) {
        editorPageInspector.changeLetterSpacingByInput(letterSpacing);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "letter-spacing", letterSpacing + "px");
    }

    @Step("Change letter spacing to {0} and verify selected element has the correct line spacing")
    public void changeLetterSpacingBySlider(String letterSpacing) {
        int value = Integer.parseInt(letterSpacing);
        editorPageInspector.changeLetterSpacingBySlider(value);
        String id = getSelectedElementId();
        int min = -20;
        int max = 20;
        int expected = min > value ? min : Math.min(max, value);
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "letter-spacing", expected + "px");
    }

    @Step("Change column height to {0} and verify selected element has the correct column height")
    public void changeColumnHeightInput(String columnHeight) {
        editorPageInspector.changeColumnHeightByInput(columnHeight);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "min-height", columnHeight + "px");
    }

    @Step("Change text transform to {0} and verify selected element has the correct text transform")
    public void changeTextTransform(String textTransform) {
        editorPageInspector.changeTextTransform(textTransform);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "text-transform", textTransform);
    }

    @Step("Toggle off text transform and verify selected element has removed text transform attribute")
    public void toggleOffTextTransform(String textTransform) {
        editorPageInspector.changeTextTransform(textTransform);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "text-transform", textTransform);
    }

    @Step("Change background color and verify selected element has the correct background color")
    public void changeBackgroundColor() {
        String targetColor = editorPageInspector.changeBackgroundColorClick();
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "background-color", convertRGBtoRGBA(targetColor));
    }

    @Step("Change background color to {0} and verify selected element has the correct background color")
    public void changeBackgroundColor(String color) {
        editorPageInspector.changeBackgroundColorSendKeys(color);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "background-color", convertRGBtoRGBA(color));
    }

    @Step("Change border style and verify selected element has the correct border style")
    public void changeBorderStyle(String borderStyle) {
        editorPageInspector.changeBorderStyle(borderStyle);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "border-style", borderStyle);
    }

    @Step("Toggle off border style and verify selected element has removed border style attribute")
    public void toggleOffBorderStyle(String borderStyle) {
        editorPageInspector.changeBorderStyle(borderStyle);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasRemovedCssAttributeValue(id, "border-style", borderStyle);
    }

    @Step("Change border radius to {0} and verify selected element has the correct border radius")
    public void changeBorderRadius(String borderRadius) {
        editorPageInspector.changeBorderRadius(borderRadius);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "border-radius", borderRadius + "px");
    }

    @Step("Change border width to {0} and verify selected element has the correct border width")
    public void changeBorderWidth(String borderWidth) {
        editorPageInspector.changeBorderWidth(borderWidth);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "border-width", borderWidth + "px");
    }

    @Step("Change show drop cap to {0}, drop cap content to {1} and verify selected element has the correct drop cap")
    public void changeShowDropCap(String showDropCap, @Nullable String dropCapContent) {
        editorPageInspector.changeShowDropCap(showDropCap);
        if (showDropCap.equals("NO")) {
            return;
        }
        String id = getSelectedElementId();
        switchToDragAndDropFrame();
        verifyElementVisible(By.xpath("//*[@data-pf-id='" + id + "']//*[@data-pf-type='Dropcap']"));
        switchToPageFlyFrame();
        if (dropCapContent != null) {
            editorPageSandbox.selectElement("Dropcap");
            id = getSelectedElementId();
            editorPageInspector.changeDropCapContent(dropCapContent);
            switchToDragAndDropFrame();
            verifyElementTextEquals(By.xpath("//*[@data-pf-id='" + id + "']"), dropCapContent);
            switchToPageFlyFrame();
        }

    }

    @Step("Change display style and verify selected element has the correct display style")
    public void changeDisplayStyle(String displayStyle, String displayStyleValue) {
        editorPageInspector.changeDisplayStyle(displayStyle);
        String id = getSelectedElementId();
        editorPageSandbox.verifySelectedElementHasCssAttributeValue(id, "display", displayStyleValue);
    }

    @Step("Add new item to the list and verify the item is added")
    public void addNewItemToList() {
        String id = getSelectedElementId();
        String type = getSelectedElementType();

        int befNum = editorPageSandbox.getElementsNumber(id, type);
        if (Objects.equals(type, "Row")) {
            int maxCols = editorPageInspector.getColumnsPerLine();
            if (befNum >= maxCols) {
                System.out.println("The maximum number of columns per row is reached");
                return;
            }
        }
        else if (Objects.equals(type, "Column")) {
            if (befNum >= 12) {
                System.out.println("The maximum number of columns per row is reached");
                return;
            }
        }
        editorPageInspector.addNewItemToList();
        sleep(0.5);
        int aftNum = editorPageSandbox.getElementsNumber(id, type);
        verifyTrue(aftNum == befNum + 1, "The new item is not added to the list");
    }


    @Step("Remove item from the list and verify the item is removed")
    public void removeItemFromList() {
        int befNum = editorPageSandbox.getElementsNumber(getSelectedElementId(), getSelectedElementType());
        editorPageInspector.removeItemFromList();
        sleep(0.5);
        clickOnSelectedElement();
        sleep(0.5);
        int aftNum = editorPageSandbox.getElementsNumber(getSelectedElementId(), getSelectedElementType());
        verifyTrue(aftNum == befNum - 1, "The item is not removed from the list");
    }


//    ================== Edit Page ==================

    public void dragAndDropElementToSandbox(By catalog, By list, By toElement) {
        waitForElementClickable(catalog);
        clickElement(catalog);
        moveToElement(list);
        if (waitForElementClickable(list, 10) != null) {
            clickElement(list);
        }
        verifyElementVisible(catalogList);
        By fromElement = By.className("Catalog-Image");
        dragAndDropFromAppIframeToPfSandbox(fromElement, toElement);

        badge.verifyUnsavedBadge();
    }

    public void dragAndDropElement(By catalog, By list) {
        waitForElementClickable(catalog);
        clickElement(catalog);
        verifyElementVisible(catalog);
        moveToElement(list);
        if (waitForElementClickable(list, 10) != null) {
            clickElement(list);
        }
        verifyElementVisible(catalogList);
        By fromElement = By.className("Catalog-Image");
        By toElement = By.cssSelector("iframe");
        dragAndDrop(fromElement, toElement);
        badge.verifyUnsavedBadge();
    }

    public void dragAndDropCatalogElement(By list) {
        dragAndDropElement(catalogAddElementButton, list);
    }

    public void dragAndDropShopifyElement(By list) {
        dragAndDropElement(catalogAddShopifyElementButton, list);
    }

    public void dragAndDrop3rdPartyElement(By list) {
        dragAndDropElement(catalogAdd3rdPartyElementButton, list);
    }

    @Step("Drag and drop Layout element to the editor")
    public void dragAndDropLayoutElement() {
        dragAndDropCatalogElement(By.id("catalog--catalog-list--layout"));
    }

    @Step("Drag and drop Heading element to the editor")
    public void dragAndDropHeadingElement() {
        By headingButton = By.id("catalog--catalog-list--heading");
        dragAndDropCatalogElement(headingButton);
    }

    @Step("Drag and drop Paragraph element to the editor")
    public void dragAndDropParagraphElement() {
        By paragraphButton = By.id("catalog--catalog-list--paragraph");
        dragAndDropCatalogElement(paragraphButton);
    }

//    ================== Save/Publish Page ==================

    @Step("Click 'Save' button")
    public void clickSavePageButton() {
        clickElement(By.id("editor-header-bar--save-page-btn"));
    }

    @Step("Click 'Save and Publish' button")
    public void clickSaveAndPublishPageButton() {
        clickElement(By.id("editor-header-bar--other-save-actions-btn"));
        clickElement(By.id("editor-header-bar--save-and-publish-page-btn"));
    }


    @Step("Verify page saved and published successfully")
    public void verifyPageSavedAndPublished() {
        badge.verifySavedBadge(30);
        badge.verifyPublishedBadge(30);
    }

    @Step("Click 'Unpublish' button")
    public void clickUnpublishPageButton() {
        clickElement(By.id("editor-header-bar--other-save-actions-btn"));
        clickElement(By.id("editor-header-bar--unpublish-page-btn"));
    }

//    ================== Leave page Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Leave page' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Leave page' modal")
    public void confirmLeavePageModal() {
        waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.LEAVE_PAGE_MODAL.TITLE);
        clickElement(By.xpath(".//button//*[text()='" + ModalConstants.LEAVE_PAGE_MODAL.PRIMARY_BUTTON + "']"));
        verifyElementNotVisible(modal);
    }

//    ================== Before Save Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>If a title is provided, it fills in the title. If not, it fills in the current date and time.</p>
     * <p>Clicks the 'Save' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     *
     * @param title The new title for the page.
     */
    @Step("Confirm 'Before Save' modal when the page title is untitled")
    public void confirmBeforeSaveModal_UntitledTitle(String title) {
        WebElement beforeSaveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, BEFORE_SAVE_MODAL.TITLE);
        clearAndFillText(By.id("menubar--save-modal--page-title"), title);
        clickElement(By.id("menubar--save-modal--save"));
        verifyElementNotVisible(beforeSaveModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Save' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Before Save' modal when the page title is titled")
    public void confirmBeforeSaveModal_TitledTitle() {
        WebElement beforeSaveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, BEFORE_SAVE_MODAL.TITLE);
        clickElement(By.id("menubar--save-modal--save"));
        verifyElementNotVisible(beforeSaveModal);
    }


//    ================== Save Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Save' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Save page' modal")
    public void confirmSavePageModal() {
        WebElement saveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.SAVE_MODAL.TITLE);
        By saveButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.SAVE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(saveButton);
        verifyElementNotVisible(saveModal);
    }

    @Step("Select Don't remind option and confirm 'Save page' modal")
    public void selectDontRemindAndConfirmSavePageModal() {
        WebElement saveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.SAVE_MODAL.TITLE);
        By checkbox = new ByChained(modal, By.className("Polaris-Checkbox"));
        clickElement(checkbox);
        By saveButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.SAVE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(saveButton);
        verifyElementNotVisible(saveModal);
    }

//    ================== Before Publish Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Publishing homepage' modal")
    public void confirmPublishingHomepageModal() {
        WebElement publishingModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_HOMEPAGE_MODAL.TITLE);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_HOMEPAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishingModal);
    }

    @Step("Select Don't remind option and confirm 'Publishing homepage' modal")
    public void selectDontRemindAndConfirmPublishingHomePageModal() {
        WebElement publishingModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_HOMEPAGE_MODAL.TITLE);
        By checkbox = new ByChained(modal, By.className("Polaris-Checkbox"));
        clickElement(checkbox);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_HOMEPAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishingModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>If a title is provided, it fills in the title. If not, it fills in the current date and time.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     *
     */
    @Step("Confirm 'Before Publish' modal when the page is untitled")
    public void confirmBeforePublishPageModal_UntitledTitle() {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.UNTITLED_TITLE);
        clearAndFillText((modal), new Date().toString());
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Fills in the provided title.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     *
     * @param title The new title for the page. If null, the current title will be used.
     */
    @Step("Confirm 'Before Publish' modal when the page is titled")
    public void confirmBeforePublishPageModal_TitledTitle(String title) {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.TITLED_TITLE);
        clearAndFillText(modal, title);
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Before Publish' modal when the page is titled")
    public void confirmBeforePublishPageModal_TitledTitle() {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.TITLED_TITLE);
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Before Publish Section' modal")
    public void confirmBeforePublishSectionModal_UntitledTitle() {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_SECTION_MODAL.TITLE);
        clearAndFillText(By.id("menubar--save-modal--page-title"), new Date().toString());
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

    @Step("Confirm 'Your page is ready to publish!' modal")
    public void confirmBeforePublishProductCollectionPageModal_TitledTitle() {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.TITLED_TITLE);
        clickElement(By.id("menubar--save-modal--primary"));
        pageAssignmentModal.verifyPageAssignmentModalVisible();
        pageAssignmentModal.assignProduct();
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

    @Step("Confirm 'Publish product page' modal")
    public void confirmPublishProductModal() {
        WebElement publishProductModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.TITLE);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);

    }

    @Step("Select Don't remind option and confirm 'Publish product page' modal")
    public void selectDontRemindAndConfirmPublishProductModal() {
        WebElement publishProductModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.TITLE);
        By checkbox = new ByChained(modal, By.className("Polaris-Checkbox"));
        clickElement(checkbox);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);
    }

    @Step("Select Don't remind option and confirm 'Publish collection page' modal")
    public void selectDontRemindAndConfirmPublishCollectionModal() {
        WebElement publishProductModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_COLLECTION_PAGE_MODAL.TITLE);
        By checkbox = new ByChained(modal, By.className("Polaris-Checkbox"));
        clickElement(checkbox);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_COLLECTION_PAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);
    }

//    ================== Published Section Modal ==================

    @Step("Close the 'Section published successfully' modal")
    public void closePublishedSectionModal() {
//        WebElement publishedModal = waitForElementVisible(modal);
//        verifyElementTextContains(modal, ModalConstants.PUBLISH_SECTION_SUCCESS_MODAL.TITLE);
        WebElement publishedModal = waitForElementTextContains(modal, ModalConstants.PUBLISH_SECTION_SUCCESS_MODAL.TITLE);

        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISH_SECTION_SUCCESS_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishedModal);
    }

//    ================== Enable Autosave Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the checkbox in the modal footer.</p>
     * <p>Clicks the 'Enable' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Confirm 'Enable Auto Save' modal")
    public void confirmEnableAutoSaveModal() {
        // Define the modal and its elements
        WebElement autosaveModal = waitForElementVisible(modal);
        By checkbox = By.cssSelector(".Polaris-Checkbox");
        By enableButton = By.xpath(".//button//*[text()='Enable']");

        verifyElementTextContains(modal, ModalConstants.ENABLE_AUTO_SAVE_MODAL.TITLE);
        clickElement(checkbox);
        clickElement(enableButton);
        verifyElementNotVisible(autosaveModal);
    }

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>Clicks the 'Cancel' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     */
    @Step("Close the 'Enable Auto Save' modal")
    public void closeEnableAutoSaveModal() {
        WebElement autosaveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.ENABLE_AUTO_SAVE_MODAL.TITLE);
        By cancelButton = By.xpath(".//button//*[text()='Cancel']");
        clickElement(cancelButton);
        sleep(0.5);
        verifyElementNotVisible(autosaveModal);
    }

//    ================== History ==================

    @Step("Open 'Version history' modal")
    public void openVersionHistoryModal() {
        clickElement(historyActivatorButton);
        verifyElementVisible(modal);
        verifyElementTextContains(modal, "Version history");
        waitForPageLoaded();
    }

    @Step("Restore to version history {0}")
    public String restoreToVersionHistory(int index) {
        openVersionHistoryModal();
        sleep(2);
        By versionButton = new ByChained(modal, By.xpath("//button[@id]"));
        By versionButtonByIndex = By.xpath("(//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]//button[@id])[" + index + "]");
        waitForElementVisible(versionButtonByIndex, 15);
        waitForElementClickable(versionButtonByIndex, 15);
        clickElement(versionButtonByIndex);
        waitForPageLoaded();
        sleep(2);
        String source = getHistoryPreviewSource();
        clickPolarisButtonHasText("Restore");
        clickPolarisButtonHasText("Restore"); // confirm modal
        verifyElementNotVisible(modal);
        waitForPageLoaded();
        return source;
    }


//    ================== Drag and Drop Canvas ==================

    @Step("Take Dnd canvas screenshot")
    public File takeDndCanvasScreenshot(String name) {
        return takeElementScreenshot(editorDnd, name);
    }
}