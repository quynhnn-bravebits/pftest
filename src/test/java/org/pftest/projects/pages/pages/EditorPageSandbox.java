package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static org.pftest.keywords.WebUI.*;

// page_url = about:blank
public class EditorPageSandbox {

    @Step("Verify selected element has id {id} has style attribute {styleAttribute} with value {styleValue}")
    public void verifySelectedElementHasStyleAttributeValue(String id, String styleAttribute, String styleValue) {
        switchToDragAndDropFrame();
        verifyElementStyleAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), styleAttribute, styleValue);
        switchToPageFlyFrame();
    }

    @Step("Verify selected element has id {id} has css attribute {cssAttribute} with value {cssValue}")
    public void verifySelectedElementHasCssAttributeValue(String id, String cssAttribute, String cssValue) {
        switchToDragAndDropFrame();
        verifyElementAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute, cssValue);
        switchToPageFlyFrame();
    }

    @Step("Verify selected element has id {id} has removed css attribute {cssAttribute}")
    public void verifySelectedElementHasRemovedCssAttributeValue(String id, String cssAttribute, String cssValue) {
        switchToDragAndDropFrame();
        verifyElementNotHaveCssAttributeValue(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute, cssValue);
        switchToPageFlyFrame();
    }

    @Step("Verify selected element has id {id} not have css attribute {cssAttribute}")
    public void verifySelectedElementNotHaveCssAttribute (String id, String cssAttribute) {
        switchToDragAndDropFrame();
        verifyElementNotHaveCssAttribute(By.xpath("//*[@data-pf-id='" + id + "']"), cssAttribute);
        switchToPageFlyFrame();
    }

    @Step("Select {0} element")
    public void selectElement(String type) {
        By element = By.xpath("//*[@data-pf-type='" + type + "']");
        clickOnBorderElement(element);
        sleep(0.5);
        verifyTrue(Objects.equals(getSelectedElementType(), type), "Selected element is not a " + type + " element");
    }

    private class NewItemType {
        String elementType;
        String parentType;

        public NewItemType(String elementType, String parentType) {
            this.elementType = elementType;
            this.parentType = parentType;

        }
    }

    private NewItemType getNewItemElementType (String type) {
        return switch (type) {
            case "Column", "Row" -> new NewItemType("Column", "Row");
            case "Section" -> new NewItemType("Row", "Section");
            default -> null;
        };
    }

    /**
     * Get the number of elements of type {elementType} that are children of the element with type {parentType}
     * @param id selected element id
     * @param type selected element type
     * @return number of elements
     */
    public int getElementsNumber (String id, String type) {
        try {
            switchToDragAndDropFrame();
            String elementType = getNewItemElementType(type).elementType;
            String parentType = getNewItemElementType(type).parentType;
            String xpath;
            if (type.equals("Column")) {
                xpath = "//*[@data-pf-type='" + elementType + "' and @data-pf-id='" + id + "']/ancestor::*[@data-pf-type='" + parentType + "'][1]//*[@data-pf-type='" + elementType + "']";
            } else {
                xpath = "//*[@data-pf-type='" + parentType + "' and @data-pf-id='" + id + "']//*[@data-pf-type='" + elementType + "']";
            }
            return getWebElements(By.xpath(xpath)).size();
        } finally {
            switchToPageFlyFrame();
        }
    }

    /**
     * Verify that the height of all elements that are children of the element with id {id} are equal
     * @param id selected element id
     * @param type selected element type
     * @return true if all elements have the same height, false otherwise
     */
    @Step("Verify all elements that are children of the element with id {id} have the same height")
    public void verifyElementsHeightEqual (String id, String type) {
        try {
            switchToDragAndDropFrame();
            String elementType = getNewItemElementType(type).elementType;
            String parentType = getNewItemElementType(type).parentType;
            String xpath = "//*[@data-pf-type='" + parentType + "' and @data-pf-id='" + id + "']//*[@data-pf-type='" + elementType + "']";

            List<WebElement> elements = getWebElements(By.xpath(xpath));
            int height = elements.getFirst().getSize().height;
            for (WebElement element : elements) {
                if (element.getSize().height != height) {
                    verifyTrue(false, "Elements do not have the same height");
                }
            }
        } finally {
            switchToPageFlyFrame();
        }
    }

}