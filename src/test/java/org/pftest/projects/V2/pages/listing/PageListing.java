package org.pftest.projects.V2.pages.listing;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.enums.pagefly.EditorType;
import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.components.drawer.DrawerManager;
import org.pftest.projects.V2.components.modal.factory.ListingModalFactory;
import org.pftest.projects.V2.components.modal.modals.selectEditorType.SelectEditorTypeModal;
import org.pftest.projects.V2.pages.BaseListingScreen;

import static org.pftest.keywords.WebUI.*;

public class PageListing extends BaseListingScreen {

    private final By createFromTemplateButton = new ByChained(actionMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_TEMPLATE_BUTTON + "']"));
    private final By createFromBlankButton = new ByChained(primaryMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_BLANK_BUTTON + "']"));

    @Override
    @Step("Verify page listing page is loaded")
    public void verifyPageLoaded() {
        verifyPageTitle(PagesConstants.PAGE_LISTING_PAGE_TITLE);
        verifyElementVisible(importButton);
        verifyElementVisible(exportButton);
        verifyElementVisible(createFromTemplateButton);
        verifyElementVisible(createFromBlankButton);
        verifyElementVisible(indexTable);
    }

    @Step("Select editor type from Create new page modal: {editorType}")
    public void selectEditorType(EditorType editorType) {
        switchToDefaultContent();
        SelectEditorTypeModal selectEditorTypeModal = ListingModalFactory.createSelectEditorTypeModal();
        selectEditorTypeModal.verifyVisible();
        selectEditorTypeModal.selectEditorOption(editorType);
    }

    @Step("Create new page from template: {pageType} with editor type: {editorType}")
    private void createFromTemplate(PageType pageType, EditorType editorType) {
        clickElement(createFromTemplateButton);
        By pageTypeButton = By.id(pageType.toString().toLowerCase() + "-template");
        waitForElementVisible(pageTypeButton);
        clickElement(pageTypeButton);

        selectEditorType(editorType);

        sleep(1);
        waitForPageLoaded();
        switchToEditorFrame();
        DrawerManager.getTemplatesDrawer(ListingType.PAGE).selectTemplate();
    }

    public void createLegacyLayoutFromTemplate(PageType pageType) {
        this.createFromTemplate(pageType, EditorType.LEGACY);
    }

    public void createFlexLayoutFromTemplate(PageType pageType) {
        this.createFromTemplate(pageType, EditorType.FLEX);
    }

    @Step("Create new page from blank: {pageType} with editor type: {editorType}")
    private void createFromBlank(PageType pageType, EditorType editorType) {
        clickElement(createFromBlankButton);
        By pageTypeButton = By.id(pageType.name().toLowerCase() + "-blank");
        waitForElementVisible(pageTypeButton);
        clickElement(pageTypeButton);

        selectEditorType(editorType);

        sleep(1);
        waitForPageLoaded();
        switchToEditorFrame();
    }

    public void createLegacyLayoutFromBlank(PageType pageType) {
        this.createFromBlank(pageType, EditorType.LEGACY);
    }

    public void createFlexLayoutFromBlank(PageType pageType) {
        this.createFromBlank(pageType, EditorType.FLEX);
    }


}
