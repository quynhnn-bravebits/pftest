package org.pftest.projects.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static org.pftest.keywords.WebUI.verifyElementVisible;
import static org.pftest.keywords.WebUI.verifySelectedByValue;

// page_url = about:blank
public class PageAssignmentModal {
    By applyOptionButton = By.id("pages--page-assignment--apply-option");
    By searchAvailableResourcesInput = By.id("pages--page-assignment--available-resources--search");
    By availableResourcesList = By.xpath("//div[@id='pages--page-assignment--available-resources']//ul[contains(@class, 'Polaris-ResourceList')]");
    By searchSelectedResourcesInput = By.id("pages--page-assignment--selected-resources--search");
    By selectedResourcesList = By.xpath("//div[@id='pages--page-assignment--selected-resources']//ul[contains(@class, 'Polaris-ResourceList')]");
    By assignButton = By.id("pages--page-assignment--assign-product-btn");
    By unassignButton = By.id("pages--page-assignment--unassign-product-btn");

    By getSelectedAvailableResourceByIndex(int index) {
        return By.xpath("//div[@id='pages--page-assignment--available-resources']//li[.//input[@type='checkbox' and @aria-checked='true']][" + index + "]");
    }
    By getUnselectedAvailableResourceByIndex(int index) {
        return By.xpath("//div[@id='pages--page-assignment--available-resources']//li[.//input[@type='checkbox' and @aria-checked='false']][" + index + "]");
    }

    By getSelectedSelectedResourceByIndex(int index) {
        return By.xpath("//div[@id='pages--page-assignment--selected-resources']//li[.//input[@type='checkbox' and @aria-checked='true']][" + index + "]");
    }
    By getUnselectedSelectedResourceByIndex(int index) {
        return By.xpath("//div[@id='pages--page-assignment--selected-resources']//li[.//input[@type='checkbox' and @aria-checked='false']][" + index + "]");
    }

    public void verifyPageAssignmentModalVisible() {
        verifyElementVisible(applyOptionButton);
        verifySelectedByValue(applyOptionButton, "custom");
        verifyElementVisible(searchAvailableResourcesInput);
        verifyElementVisible(availableResourcesList);
        verifyElementVisible(searchSelectedResourcesInput);
        verifyElementVisible(assignButton);
        verifyElementVisible(unassignButton);
    }

    public void assignProducts () {

    }
}