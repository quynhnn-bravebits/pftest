package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.modal.selectShopifySources.SelectShopifySourceModal;
import org.pftest.projects.V2.components.popover.publish.BeforePublishPagePopover;
import org.pftest.projects.V2.components.popover.publish.PublishPagePopover;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.keywords.WebUI.switchToEditorFrame;

abstract class ProductCollectionPageEditor extends BaseEditor {
    protected boolean isAssigned = false;
    protected SelectShopifySourceModal selectShopifySourceModal;
    protected BeforePublishPagePopover beforePublishPagePopover;

    public void setAssigned(boolean assigned) {
        this.isAssigned = assigned;
    }

    /**
     * Case 1: Product/Collection is assigned to the page
     *  - Publish popover is shown as usual
     * Case 2: Product/Collection is not assigned to the page
     *  - Show the Before Publish Product/Collection popover to select products/collections
     *  - Select sources from the Select Products/Collections modal
     *  - Publish popover is shown after selecting products/collections
     */
    public void publish() {
        if (isAssigned) {
            super.publish();
            return;
        }
        // Click Publish button on the Contextual Toolbar
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        getBeforePublishPagePopover().clickSelectProducts();
        // Select products
        switchToDefaultContent();
        getSelectShopifySourcesModal().verifyVisible();
        getSelectShopifySourcesModal().selectItem(1);
        getSelectShopifySourcesModal().clickSelectButton();
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

    public abstract BeforePublishPagePopover getBeforePublishPagePopover();
    public abstract SelectShopifySourceModal getSelectShopifySourcesModal();
}
