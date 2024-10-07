package org.pftest.projects.V2.pages.editor;

import org.pftest.enums.pagefly.PageType;
import org.pftest.projects.V2.components.modal.selectShopifySources.SelectCollectionsModal;
import org.pftest.projects.V2.components.modal.selectShopifySources.SelectShopifySourceModal;
import org.pftest.projects.V2.components.popover.publish.BeforePublishPagePopover;

public class CollectionPageEditor extends ProductCollectionPageEditor {

    @Override
    public BeforePublishPagePopover getBeforePublishPagePopover() {
        if (this.beforePublishPagePopover == null) {
            this.beforePublishPagePopover =  new BeforePublishPagePopover(PageType.COLLECTION);
        }
        return this.beforePublishPagePopover;
    }

    @Override
    public SelectShopifySourceModal getSelectShopifySourcesModal() {
        if (this.selectShopifySourceModal == null) {
            this.selectShopifySourceModal = new SelectCollectionsModal();
        }
        return this.selectShopifySourceModal;
    }
}
