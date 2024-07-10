package org.pftest.projects.V2.components.modal.factory;

import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.beforePublish.BeforePublishPageModal;
import org.pftest.projects.V2.components.modal.modals.beforePublish.BeforePublishProductCollectionPageModal;

public class ProductPageModalFactory extends PageModalFactory {

    @Override
    public BeforePublishPageModal createBeforePublishPageModal_Untitled() {
        return new BeforePublishProductCollectionPageModal(ModalType.BEFORE_PUBLISH_PAGE);
    }

    @Override
    public BeforePublishPageModal createBeforePublishPageModal_Titled() {
        return new BeforePublishProductCollectionPageModal(ModalType.PUBLISH_PAGE);
    }
}
