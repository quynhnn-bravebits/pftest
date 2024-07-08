package org.pftest.projects.V2.components.modal.factory;

import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.beforePublish.BeforePublishPageModal;
import org.pftest.projects.V2.components.modal.modals.beforePublish.IBeforePublishPageModal;

public class HomePageModalFactory extends PageModalFactory {
    @Override
    public IBeforePublishPageModal createBeforePublishPageModal_Untitled() {
        return new BeforePublishPageModal(ModalType.BEFORE_PUBLISH_PAGE);
    }

    @Override
    public IBeforePublishPageModal createBeforePublishPageModal_Titled() {
        return new BeforePublishPageModal(ModalType.PUBLISH_PAGE);
    }
}
