package org.pftest.projects.V2.components.drawer;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ListingType;
import org.pftest.projects.V2.components.popover.template.SelectTemplatePopover;

import java.util.Random;

import static org.pftest.helpers.Helpers.retryAction;
import static org.pftest.keywords.WebUI.*;

public class TemplatesDrawer extends BaseDrawer {
    private static final By templateCard = By.xpath("//div[@class='template-list-modal--template-list--template-card']");
    private static final By selectButton = By.xpath("//button/*[text()='Select']");
    private final By templateCardSelectButton = new ByChained(templateCard, selectButton);
    private SelectTemplatePopover selectTemplatePopover;

    public TemplatesDrawer(ListingType type) {
        this.container = By.id("page-template-drawer");
        this.activator = By.id("page-template-drawer-button");
        if (type == ListingType.PAGE) {
            this.title = new ByChained(this.container, By.xpath(".//h3[text()='Page templates']"));
        } else if (type == ListingType.SECTION) {
            this.title = new ByChained(this.container, By.xpath(".//h3[text()='Section templates']"));
        }
    }

    private void waitForDrawerToLoad() {
        waitForElementVisible(title);
        waitForElementVisible(templateCard);
        sleep(3);
    }


    /**
     * Randomly select a template
     */
    @Step("Select a template")
    public void selectTemplate() {
        waitForDrawerToLoad();

        int templatesCount = getWebElements(templateCard).size();

        // Because the card list is rendered several times by JS, the action may fail after hovering on the element
        retryAction(() -> {
            // Randomly index the template card
            int index = new Random().nextInt(1, templatesCount);
            By template = By.xpath("(" + templateCard.toString().replace("By.xpath: ", "") + "//div[@class='template-title'])[" + index + "]");

            waitForElementVisible(template, 3);
            moveToElement(template);
            // Select button inside the template card
            By button = new ByChained(template, selectButton);
            waitForElementVisible(button);
            hoverOnElement(button);

            waitForElementClickable(button);
            clickElement(button);
        }, 3);


    }

    @Step("Confirm select template popover")
    public void confirmSelectTemplatePopover() {
        selectTemplatePopover = new SelectTemplatePopover();
        selectTemplatePopover.verifyVisible();
        selectTemplatePopover.checkUnderstandCheckbox();
        selectTemplatePopover.clickConfirmButton();
        selectTemplatePopover.verifyNotVisible();
    }

}
