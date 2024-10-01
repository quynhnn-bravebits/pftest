package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.modal.factory.ProductPageModalFactory;
import org.pftest.projects.V2.components.popover.publish.BeforePublishProductPagePopover;
import org.pftest.projects.V2.components.popover.publish.PublishPagePopover;

import static org.pftest.keywords.WebUI.*;

public class ProductPageEditor extends BaseEditor {
    private boolean isAssignedProduct = false;
    private ProductPageModalFactory modalFactory;
    private BeforePublishProductPagePopover beforePublishProductPagePopover;


    public void setAssignedProduct(boolean assignedProduct) {
        this.isAssignedProduct = assignedProduct;
    }

    /**
     * Case 1: Product is assigned to the page
     *  - Publish popover is shown as usual
     * Case 2: Product is not assigned to the page
     *  - Show the Before Publish Product popover to select products
     *  - Select products from the Select Products modal
     *  - Publish popover is shown after selecting products
     */
    public void publish() {
        if (isAssignedProduct) {
            super.publish();
            return;
        }
        // Click Publish button on the Contextual Toolbar
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        getBeforePublishProductPagePopover().clickSelectProducts();
        // Select products
        switchToDefaultContent();
        ProductPageModalFactory().createSelectProductsModal().verifyVisible();
        ProductPageModalFactory().createSelectProductsModal().selectItem(1);
        ProductPageModalFactory().createSelectProductsModal().clickSelectButton();
        Toast.verifyShowPageAssignmentToast();

        // Publish the page
        switchToEditorFrame();
        new PublishPagePopover().clickPublish();
        switchToDefaultContent();
        // Verify the toast messages
        Toast.verifyShowPublishingPageToast();
        Toast.verifyShowPublishedPageToast();
        switchToEditorFrame();
        PageHeader.verifyPublishStatus();

    }

    public BeforePublishProductPagePopover getBeforePublishProductPagePopover() {
        if (beforePublishProductPagePopover == null) {
            beforePublishProductPagePopover = new BeforePublishProductPagePopover();
        }
        return beforePublishProductPagePopover;
    }

    public ProductPageModalFactory ProductPageModalFactory() {
        if (modalFactory == null) {
            modalFactory = new ProductPageModalFactory();
        }
        return modalFactory;
    }
}
