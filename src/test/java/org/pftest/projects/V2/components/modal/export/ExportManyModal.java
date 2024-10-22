package org.pftest.projects.V2.components.modal.export;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.enums.pagefly.ListingType;

import static org.pftest.keywords.WebUI.*;

public class ExportManyModal extends ExportModal {
    public ExportManyModal(ListingType listingType) {
        super(listingType);
        this.title += "s";
    }

    public void verifySelectSelectedPages() {
        assert verifyElementChecked(By.id("modal_selected"), "Option 'Selected pages' is not selected");
    }

    public void verifySelectFilteredPages() {
        assert verifyElementChecked(By.id("modal_tab"), "Option 'Filtered pages' is not selected");
    }

    public void verifySelectAllPages() {
        assert verifyElementChecked(By.id("modal_all"), "Option 'All pages' is not selected");
    }

    /**
     *
     * @param id input option id to select
     */
    @Step("Select option {0} in 'Export pages/sections' modal")
    public void selectOptionById(String id) {
        if (verifyElementChecked(By.id(id)))
           return;
        By option = By.xpath("//label[contains(@class, 'Polaris-Choice') and @for='" + id + "']");
        waitForElementClickable(option);
        clickElement(option);
    }
}
