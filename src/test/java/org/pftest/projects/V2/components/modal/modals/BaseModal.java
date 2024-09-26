package org.pftest.projects.V2.components.modal.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.keywords.WebUI;

public class BaseModal {
    protected By locator = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//div[starts-with(@data-portal-id, 'modal')]//div[@role=\"dialog\"]/div[1]");
    private String title;
    private String primaryButton;
    private String secondaryButton;

    public BaseModal(ModalType modalType) {
        verifyVisible();
        this.title = modalType.getTitle();
        this.secondaryButton = modalType.getSecondaryButton();
        this.primaryButton = modalType.getPrimaryButton();
    }

    public BaseModal setTitle(String title) {
        this.title = title;
        return this;
    }

    public BaseModal setPrimaryButton(String primaryButton) {
        this.primaryButton = primaryButton;
        return this;
    }

    public BaseModal setSecondaryButton(String secondaryButton) {
        this.secondaryButton = secondaryButton;
        return this;
    }

    public BaseModal build() {
        return this;
    }

    protected By getPrimaryButtonLocator() {
        return new ByChained(locator, By.xpath(".//button/span[contains(text(), '" + primaryButton + "')]"));
    }

    protected By getSecondaryButtonLocator() {
        return new ByChained(locator, By.xpath(".//button/span[contains(text(), '" + secondaryButton + "')]"));
    }

    private By getModalLocator() {
        if (title != null) {
            return new ByChained(locator, By.xpath(".//*[contains(text(), '" + this.title + "')]"));
        }
        return locator;
    }

    public boolean verifyVisible() {
        WebElement modal =  WebUI.waitForElementVisible(getModalLocator());
        WebUI.verifyTrue(modal != null && modal.isDisplayed(), "Modal is not visible");
        return modal != null && modal.isDisplayed();
    }

    public boolean verifyNotVisible() {
        return WebUI.verifyElementNotVisible(getModalLocator());
    }

    public void close() {
        By closeButton = new ByChained(locator, By.xpath(".//button[@aria-label='Close']"));
        WebUI.clickElement(closeButton);
        WebUI.sleep(0.5);
        verifyNotVisible();
    }

    public void clickPrimaryButton() {
        WebUI.clickElement(getPrimaryButtonLocator());
    }

    public void clickSecondaryButton() {
        WebUI.clickElement(getSecondaryButtonLocator());
    }

    public void clickButton(By button) {
        By buttonLocator = new ByChained(locator, button);
        WebUI.clickElement(buttonLocator);
    }
}
