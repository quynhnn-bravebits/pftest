package org.pftest.projects.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.projects.pages.CommonPage;

import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly/pages/settings?type=page&id=0400aa7b-c8e5-4e78-b444-19b1b6f1ab11
public class PageSettingScreen extends CommonPage {
    private By editPageButton = By.xpath("//*/button/span[text()='Edit page']");

    private By viewLivePageButton = By.xpath("//*/button/span[text()='View live page']");

    @Step("Wait for the page setting screen to be loaded")
    public void verifyPageSettingScreenLoaded() {
        verifyElementVisible(editPageButton);
        verifyElementVisible(viewLivePageButton);
    }

    @Step("Click on the 'Edit page' button")
    public void clickEditPageButton() {
        clickElement(editPageButton);
    }
}