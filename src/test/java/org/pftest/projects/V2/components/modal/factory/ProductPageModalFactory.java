package org.pftest.projects.V2.components.modal.factory;

import org.pftest.projects.V2.components.modal.modals.selectShopifySources.SelectProductsModal;

public class ProductPageModalFactory extends PageModalFactory {

    public SelectProductsModal createSelectProductsModal() {
        return new SelectProductsModal();
    }
}
