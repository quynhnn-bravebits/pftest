package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

public class ElementsDrawer extends BaseDrawer {
    private By catalogsContainer = By.id("catalog-items");
    private By searchInput = new ByChained(container, By.id("element-list-drawer--search"));
    private By buttonpageFlyTabButton = new ByChained(container, By.id("element"));
    private By shopifyTabButton = new ByChained(container, By.id("shopify"));
    private By catalogItem = new ByChained(catalogsContainer, By.xpath(".//div[@class='Catalog-Image']"));

    public ElementsDrawer() {
        super(By.id("element-list-drawer-button"), By.id("element-list-drawer"));
    }

    public void searchElement(String elementName) {
        clearAndFillText(searchInput, elementName);
    }

    private void openTab(By button) {
        if (!Objects.equals(getAttributeElement(button, "aria-selected"), "true")) {
            waitForElementClickable(button);
            sleep(0.1);
            clickElement(button);
            verifyElementAttributeValue(button, "aria-selected", "true");
        }
    }

    public void openPageFlyTab() {
        openTab(buttonpageFlyTabButton);
    }

    public void openShopifyTab() {
        openTab(shopifyTabButton);
    }

    public void openProductDetailsElements() {
        By productDetailsButton = new ByChained(container, By.id("catalog--catalog-list--product-details"));
        openShopifyTab();
        clickElement(productDetailsButton);
        verifyElementVisible(catalogsContainer);
        verifyElementVisible(catalogItem);
    }
}
