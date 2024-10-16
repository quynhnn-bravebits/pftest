package org.pftest.projects.V2.components.modal.delete;

import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.BaseModal;

public class DeletePageSectionModal extends BaseModal {

    public DeletePageSectionModal(ListingType listingType, Integer num) {
        super(ModalType.DELETE);
        this.title = String.format(ModalType.DELETE.getTitle(), num, listingType.toString().toLowerCase() + "s");
    }
}
