package org.pftest.projects.V2.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import org.pftest.report.AllureManager;

import static org.pftest.keywords.WebUI.verifyElementText;
import static org.pftest.keywords.WebUI.verifyElementVisible;

public abstract class BasePage {
    protected By pageTitle = By.xpath("//h1[@class='Polaris-Header-Title']");
    protected By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");

    @Step("Verify page title is {0}")
    public void verifyPageTitle(String title) {
        verifyElementVisible(pageTitle);
        verifyElementText(pageTitle, title);
    }

    @Step("{description}")
    public void addStep(String description, Runnable step) {
        step.run();
        AllureManager.takeScreenshotStep();
    }

    public abstract void verifyPageLoaded();

}
