package org.pftest.projects.V2.pages.editor;

import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.components.modal.selectShopifySources.SelectProductsModal;
import org.pftest.projects.V2.components.modal.selectShopifySources.SelectShopifySourceModal;
import org.pftest.projects.V2.components.popover.publish.BeforePublishPagePopover;

public class ProductPageEditor extends ProductCollectionPageEditor {

    @Override
    public BeforePublishPagePopover getBeforePublishPagePopover() {
        if (beforePublishPagePopover == null) {
            beforePublishPagePopover = new BeforePublishPagePopover(PageType.PRODUCT);
        }
        return beforePublishPagePopover;
    }

    @Override
    public SelectShopifySourceModal getSelectShopifySourcesModal() {
        if (selectShopifySourceModal == null) {
            selectShopifySourceModal = new SelectProductsModal();
        }
        return selectShopifySourceModal;
    }

//    private boolean isAssigned = false;
//    private SelectShopifySourceModal selectProductsModal;
//    private BeforePublishPagePopover beforePublishProductPagePopover;
//
//    public void setAssigned(boolean assigned) {
//        this.isAssigned = assigned;
//    }
//
//    /**
//     * Case 1: Product is assigned to the page
//     *  - Publish popover is shown as usual
//     * Case 2: Product is not assigned to the page
//     *  - Show the Before Publish Product popover to select products
//     *  - Select products from the Select Products modal
//     *  - Publish popover is shown after selecting products
//     */
//    public void publish() {
//        if (isAssigned) {
//            super.publish();
//            return;
//        }
//        // Click Publish button on the Contextual Toolbar
//        switchToDefaultContent();
//        waitForElementVisible(publishButton);
//        clickElement(publishButton);
//        switchToEditorFrame();
//        getBeforePublishProductPagePopover().clickSelectProducts();
//        // Select products
//        switchToDefaultContent();
//        getSelectProductsModal().verifyVisible();
//        getSelectProductsModal().selectItem(1);
//        getSelectProductsModal().clickSelectButton();
//        Toast.verifyShowPageAssignmentToast();
//
//        // Publish the page
//        switchToEditorFrame();
//        new PublishPagePopover().clickPublish();
//        switchToDefaultContent();
//        // Verify the toast messages
//        Toast.verifyShowPublishingPageToast();
//        Toast.verifyShowPublishedPageToast();
//        switchToEditorFrame();
//        PageHeader.verifyPublishStatus();
//
//    }
//
//    public BeforePublishPagePopover getBeforePublishProductPagePopover() {
//        if (beforePublishProductPagePopover == null) {
//            beforePublishProductPagePopover = new BeforePublishPagePopover(PageType.PRODUCT);
//        }
//        return beforePublishProductPagePopover;
//    }
//
//    public SelectShopifySourceModal getSelectProductsModal() {
//        if (selectProductsModal == null) {
//            selectProductsModal = new SelectProductsModal();
//        }
//        return selectProductsModal;
//    }
}
