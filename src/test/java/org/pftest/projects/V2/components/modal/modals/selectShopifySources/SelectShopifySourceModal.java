package org.pftest.projects.V2.components.modal.modals.selectShopifySources;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.modals.BaseModal;

import static org.pftest.keywords.WebUI.*;


/**
 * **Note:** This modal belongs to the Shopify iframe, so we need to switch to the iframe before interacting with the elements inside the modal.
 * *
 * The modal contains a search input, a list of scrollable products/collections, and a select button.
 * Between the moment search input is filled and the products/collections are loaded, a loading section is displayed.
 * The Select button is disabled until at least one selected product/collection.
 */

public class SelectShopifySourceModal extends BaseModal {

    protected By searchInput = new ByChained(locator, By.xpath(".//input[starts-with(@placeholder, 'Search ')]"));
    private By loadingSection = new ByChained(locator, By.xpath(".//div[starts-with(@class, '_Loading_')]"));
    private By itemsSection = new ByChained(locator, By.xpath(".//div[starts-with(@class, '_ScrollableContainer_')]/ul"));
    private By item(int index) {
        return new ByChained(itemsSection, By.xpath("./li/div[@data-virtualized-index=" + index + "]"));
    }

    public SelectShopifySourceModal(ModalType modalType) {
       super(modalType);
    }

    @Step("Search items: {itemName}")
    public void searchItem(String itemName) {
        clearAndFillText(searchInput, itemName);
        waitForElementVisible(loadingSection);
        waitForElementVisible(itemsSection);
    }

    @Step("Select item: {index}")
    public void selectItem(int index) {
        waitForElementVisible(item(index));
        By itemCheckbox = new ByChained(item(index), By.xpath(".//input[@type='checkbox']"));

        if (verifyElementChecked(itemCheckbox)) return;

        waitForElementVisible(item(index));
        clickElement(item(index));
    }

    @Step("Click Select button")
    public void clickSelectButton() {
        clickPrimaryButton();
        sleep(0.5);
        verifyNotVisible();
    }
}
