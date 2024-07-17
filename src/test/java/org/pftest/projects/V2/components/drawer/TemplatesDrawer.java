package org.pftest.projects.V2.components.drawer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ListingType;

import java.util.Random;

import static org.pftest.constants.FrameworkConstants.WAIT_IMPLICIT;
import static org.pftest.keywords.WebUI.*;

public class TemplatesDrawer extends BaseDrawer {
    private final ListingType type;
    private static final By templateCard = By.xpath("//div[@class='template-list-modal--template-list--template-card']");
    private static final By selectButton = By.xpath("//button/*[text()='Select']");
    private final By templateCardSelectButton = new ByChained(templateCard, selectButton);

    public TemplatesDrawer(ListingType type) {
        this.type = type;
        this.container = By.id("page-template-drawer");
        if (type == ListingType.PAGE) {
            this.title = By.xpath("//h3[text()='Page templates']");
        } else if (type == ListingType.SECTION) {
            this.title = By.xpath("//h3[text()='Section templates']");
        }
    }

    private void waitForDrawerToLoad() {
        waitForElementVisible(title);
        waitForElementVisible(templateCard, WAIT_IMPLICIT);
    }

    /**
     * Randomly select a template
     */
    public void selectTemplate() {
        waitForDrawerToLoad();

        int templatesCount = getWebElements(templateCard).size();
        // Generate a random index between 0 and 5
        int index = new Random().nextInt(1, templatesCount);
        By template = By.xpath("(" + templateCard.toString().replace("By.xpath: ", "") + "//div[@class='template-title'])[" + index + "]");
        waitForElementVisible(template);
        moveToElement(template);
        By button = new ByChained(template, selectButton);
        waitForElementVisible(button);
        hoverOnElement(button);

        waitForElementClickable(button);
        clickElement(button);
    }

}
