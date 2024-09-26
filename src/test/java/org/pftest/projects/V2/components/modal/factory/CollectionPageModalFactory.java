package org.pftest.projects.V2.components.modal.factory;

import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.selectShopifySources.SelectCollectionsModal;

public class CollectionPageModalFactory extends PageModalFactory {

    public SelectCollectionsModal createSelectCollectionsModal() {
        return new SelectCollectionsModal(ModalType.SELECT_COLLECTIONS);
    }
}
