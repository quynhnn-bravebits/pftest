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
import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

import static org.pftest.constants.ModalConstants.BEFORE_SAVE_MODAL;
import static org.pftest.constants.UrlConstants.*;
import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/editor?type=page&id=1
public class EditorPage extends Toast {

    private final PageAssignmentModal pageAssignmentModal = new PageAssignmentModal();

    private final By headerBar = By.id("editor-header-bar");
    private final By inspector = By.id("editor--inspector");
    private final By pageOutline = By.id("page-outline-section");
    private final By editorDnd = By.id("pf-sandbox");
    private final By pageTitle = By.id("editor-header-bar--page-title");
    private final By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");
    private final By catalogMenu = By.id("catalog-menu-section");
    private final By catalogList = By.id("catalog-items");

    private final By savedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Saved\"]");
    private final By unsavedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneAttention\"]//*[text()=\"Unsaved\"]");
    private final By savingBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Saving...\"]");
    private final By publishedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneSuccess\"]//*[text()=\"Published\"]");
    private final By unpublishedBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Unpublished\"]");
    private final By publishingBadge = By.xpath("//*[@id=\"editor-header-bar--status\"]//*/span[@class=\"Polaris-Badge Polaris-Badge--toneInfo\"]//*[text()=\"Publishing...\"]");
    private final By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");
    private final By crispIcon = By.xpath("//*[@id='crisp-chatbox']//a[@aria-label='Open chat']");

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
        clickElement(crispIcon);
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
        verifyElementVisible(publishedBadge, 30);
    }

    @Step("Verify page is Unpublished")
    public void verifyPageIsUnpublished() {
        verifyElementVisible(unpublishedBadge);
    }

    @Step("Verify page is Saving")
    public void verifyPageIsSaving() {
        verifyElementVisible(savingBadge);
    }

    @Step("Verify page is Saved")
    public void verifyPageIsSaved() {
        verifyElementVisible(savedBadge, 30);
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
        moveToElement(headingButton);
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
     * @param title The new title for the page. If null, the current date and time will be used.
     */
    @Step("Confirm 'Before Publish' modal when the page is untitled")
    public void confirmBeforePublishPageModal_UntitledTitle(@Nullable String title) {
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
        WebElement publishProductModal =  waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.TITLE);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);

    }

    @Step("Select Don't remind option and confirm 'Publish product page' modal")
    public void selectDontRemindAndConfirmPublishProductModal() {
        WebElement publishProductModal =  waitForElementVisible(modal);
        verifyElementTextContains(modal, ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.TITLE);
        By checkbox = new ByChained(modal, By.className("Polaris-Checkbox"));
        clickElement(checkbox);
        By publishButton = By.xpath(".//*[@role='dialog']//button//*[text()='" + ModalConstants.PUBLISHING_PRODUCT_PAGE_MODAL.PRIMARY_BUTTON + "']");
        clickElement(publishButton);
        verifyElementNotVisible(publishProductModal);
    }

    @Step("Select Don't remind option and confirm 'Publish collection page' modal")
    public void selectDontRemindAndConfirmPublishCollectionModal() {
        WebElement publishProductModal =  waitForElementVisible(modal);
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

//    ================== Drag and Drop Canvas ==================

    @Step("Take Dnd canvas screenshot")
    public File takeDndCanvasScreenshot(String name) {
        return takeElementScreenshot(editorDnd, name);
    }
}