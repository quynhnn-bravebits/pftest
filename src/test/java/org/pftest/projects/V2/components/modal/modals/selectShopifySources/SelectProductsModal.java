package org.pftest.projects.V2.components.modal.modals.selectShopifySources;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ModalType;


public class SelectProductsModal extends SelectShopifySourceModal {

    public SelectProductsModal() {
        super(ModalType.SELECT_PRODUCTS);
        this.searchInput = new ByChained(locator, By.xpath(".//input[starts-with(@placeholder, 'Search products')]"));
    }
}
