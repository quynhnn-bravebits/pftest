package org.pftest.projects.V2.components.modal.selectShopifySources;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ModalType;

public class SelectCollectionsModal extends SelectShopifySourceModal{

    public SelectCollectionsModal() {
        super(ModalType.SELECT_COLLECTIONS);
        this.searchInput = new ByChained(locator, By.xpath(".//input[starts-with(@placeholder, 'Search collections')]"));
    }
}
