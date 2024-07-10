package org.pftest.projects.V2.components.modal.factory;

import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.BaseModal;
import org.pftest.projects.V2.components.modal.modals.beforePublish.BeforePublishPageModal;
import org.pftest.projects.V2.components.modal.modals.beforeSave.BeforeSavePageModal;

abstract class PageModalFactory {

    public BaseModal createPublishPageModal() {
        return new BaseModal(ModalType.PUBLISH_PAGE);
    }

    public BaseModal createSavePageModal() {
        return new BaseModal(ModalType.SAVE_PAGE);
    }

    public BeforeSavePageModal createBeforeSavePageModal() {
        return new BeforeSavePageModal();
    }

    public abstract BeforePublishPageModal createBeforePublishPageModal_Untitled();

    public abstract BeforePublishPageModal createBeforePublishPageModal_Titled();

}
