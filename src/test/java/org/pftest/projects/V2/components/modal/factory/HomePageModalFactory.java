package org.pftest.projects.V2.components.modal.factory;

import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.beforePublish.BeforePublishPageModal;

public class HomePageModalFactory extends PageModalFactory {
    @Override
    public BeforePublishPageModal createBeforePublishPageModal_Untitled() {
        return new BeforePublishPageModal(ModalType.BEFORE_PUBLISH_PAGE);
    }

    @Override
    public BeforePublishPageModal createBeforePublishPageModal_Titled() {
        return new BeforePublishPageModal(ModalType.PUBLISH_PAGE);
    }
}
