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
import org.pftest.projects.commons.Toast;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

import static org.pftest.constants.ModalConstants.BEFORE_SAVE_MODAL;
import static org.pftest.constants.UrlConstants.SHOPIFY_BASE_URL;
import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/editor?type=page&id=1
public class EditorPage extends Toast {

    private By headerBar = By.id("editor-header-bar");
    private By inspector = By.id("editor--inspector");
    private By pageOutline = By.id("page-outline-section");
    private By editorDnd = By.id("pf-sandbox");
    private By pageTitle = By.id("editor-header-bar--page-title");
    private By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");
    private By catalogMenu = By.id("catalog-menu-section");
    private By catalogList = By.id("catalog-items");

    private By savedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Saved\"]");
    private By unsavedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneAttention\"]//*[text()=\"Unsaved\"]");
    private By savingBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Saving...\"]");
    private By publishedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Published\"]");
    private By unpublishedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Unpublished\"]");
    private By publishingBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Publishing...\"]");

    private By catalogAddElementButton = By.xpath("//button[@id='catalog--add-element-btn']");
    private By catalogAddShopifyElementButton = By.xpath("//button[@id='catalog--add-shopify-element-btn']");
    private By catalogAdd3rdPartyElementButton = By.xpath("//button[@id='catalog--add-3rd-party-element']");
    private By showPageOutlineButton = By.xpath("//button[@id='outline--activator']");

    private By editorBackButton = By.id("editor-header-bar--exit-btn");
    private By canvasSettingButton = By.id("canvas-size-setting-activator-btn");
    private By editorSettingButton = By.id("editor-header-bar--editor-setting--activator");
    private By moreSettingsButton = By.id("editor-header-bar--more-settings-btn");

    private By fitViewportCheckbox = By.xpath("//*[@id=\"canvas-size-setting\"]//*/label[.//*[text()='Fit viewport']]");
    private By showCanvasSizeCheckbox = By.xpath("//label[@for=\"show-canvas-size-setting-item\"]");
    private By enableAutoSaveCheckbox = By.xpath("//label[@for=\"auto-save--enable\"]");
    private By goToThemeEditorButton = By.id("go-to-theme-editor-action-btn");


    @Step("Open new {0} page editor")
    public void openNewPageEditor(PageType pageType) {
        String URL = "https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/editor?type=" + pageType.name().toLowerCase() + "&id=" + UUID.randomUUID();
        openWebsite(URL);
        switchToPageFlyFrame();
    }


    @Step("Wait for the page to be loaded")
    public void verifyEditorPageLoaded() {
        verifyElementVisible(pageTitle);
        verifyElementVisible(editorDnd);
        verifyElementVisible(inspector);
        verifyElementVisible(headerBar);
    }

//    ================== Editor Header ==================

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
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500)).until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToWindowOrTabByPosition(1);
        sleep(1);
        waitForPageLoaded();
        System.out.println(getCurrentUrl());
        assert  getCurrentUrl().contains(SHOPIFY_BASE_URL + "/themes");
        closeCurrentWindow();
        switchToWindowOrTabByPosition(0);
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
        verifyElementVisible(publishedBadge);
    }

    @Step("Verify page is Unpublished")
    public void verifyPageIsUnpublished() {
        verifyElementVisible(unpublishedBadge);
    }

    @Step("Verify page is Saving")
    public void verifyPageIsSaving() {
        verifyElementVisible(savingBadge);
        verifyShowSavingToast();
    }

    @Step("Verify page is Saved")
    public void verifyPageIsSaved() {
        verifyElementVisible(savedBadge);
        verifyShowSavedToast();
    }

    @Step("Verify page is Unsaved")
    public void verifyPageIsUnsaved() {
        verifyElementVisible(unsavedBadge);
    }

//    ================== Page Outline ==================

    @Step("Show Page Outline")
    public void showPageOutline() {
        if (verifyElementNotVisible(pageOutline)) {
            clickElement(showPageOutlineButton);
            waitForElementVisible(pageOutline);
        }
    }

    @Step("Hide Page Outline")
    public void hidePageOutline() {
        if (verifyElementVisible(pageOutline)) {
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

//    ================== Edit Page ==================

    @Step("Drag and drop Heading element to the editor")
    public void dragAndDropHeadingElement() {
        clickElement(catalogAddElementButton);
        verifyElementVisible(catalogMenu);
        By headingButton = By.id("catalog--catalog-list--heading");
        if (waitForElementClickable(headingButton) != null) {
            clickElement(By.id("catalog--catalog-list--heading"));
        }
        verifyElementVisible(catalogList);
        By fromElement = By.className("Catalog-Image");
        By toElement = By.cssSelector("iframe");
        dragAndDrop(fromElement, toElement);
        verifyElementVisible(unsavedBadge);
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

    @Step("Verify page published successfully")
    public void verifyPagePublished() {
        verifyElementVisible(publishedBadge);
    }

    @Step("Verify page saved and published successfully")
    public void verifyPageSavedAndPublished() {
        verifyElementVisible(savedBadge, 30);
        verifyElementVisible(publishedBadge, 30);
    }

    public void clickUnpublishPageButton() {
        clickElement(By.id("editor-header-bar--other-save-actions-btn"));
        clickElement(By.id("editor-header-bar--unpublish-page-btn"));
    }

//    ================== Leave page Modal ==================
    @Step("Confirm 'Leave page' modal")
    public void confirmLeavePageModal() {
        waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.LEAVE_PAGE_MODAL.TITLE);
        clickElement(modal.xpath(".//button//*[text()='" + ModalConstants.LEAVE_PAGE_MODAL.PRIMARY_BUTTON + "']"));
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
        @Step("Confirm 'Save' modal")
        public void confirmSaveModal() {
            WebElement saveModal = waitForElementVisible(modal);
            verifyElementTextContains(modal, ModalConstants.SAVE_MODAL.TITLE);
            By saveButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.SAVE_MODAL.PRIMARY_BUTTON +"']");
            clickElement(saveButton);
            verifyElementNotVisible(saveModal);
        }

//    ================== Before Publish Modal ==================

    /**
     * <p>Waits for the modal to be visible.</p>
     * <p>Verifies that the modal contains the expected text.</p>
     * <p>If a title is provided, it fills in the title. If not, it fills in the current date and time.</p>
     * <p>Clicks the 'Publish' button on the modal.</p>
     * <p>Verifies that the modal is no longer present.</p>
     *
     * @param title The new title for the page. If null, the current date and time will be used.
     */
    @Step("Confirm 'Before Publish' modal when the page is untitled")
    public void confirmBeforePublishModal_UntitledTitle(@Nullable String title) {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.UNTITLED_TITLE);
        clearAndFillText(modal, title != null ? title : new Date().toString());
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
    public void confirmBeforePublishModal_TitledTitle(String title) {
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
    public void confirmBeforePublishModal_TitledTitle() {
        WebElement beforePublishModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.BEFORE_PUBLISH_MODAL.TITLED_TITLE);
        clickElement(By.id("menubar--save-modal--primary"));
        verifyElementNotVisible(beforePublishModal);
    }

//    ================== Enable Autosave Modal ==================

    /**
     * <p>Confirms the 'Enable Auto Save' modal.</p>
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
        By checkbox = modal.cssSelector(".Polaris-Checkbox");
        By enableButton = modal.xpath(".//button//*[text()='Enable']");

        verifyElementTextContains(modal, ModalConstants.ENABLE_AUTO_SAVE_MODAL.TITLE);
        clickElement(checkbox);
        clickElement(enableButton);
        verifyElementNotVisible(autosaveModal);
    }

    @Step("Close the 'Enable Auto Save' modal")
    public void closeEnableAutoSaveModal() {
        WebElement autosaveModal = waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.ENABLE_AUTO_SAVE_MODAL.TITLE);
        By cancelButton = modal.xpath(".//button//*[text()='Cancel']");
        clickElement(cancelButton);
        sleep(0.5);
        verifyElementNotVisible(autosaveModal);
    }


}