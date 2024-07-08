package org.pftest.projects.V2.components.modal.modals;

import org.openqa.selenium.By;

public interface IBaseModal {
    boolean verifyVisible();
    boolean verifyNotVisible();
    void close();
    void clickPrimaryButton();
    void clickSecondaryButton();
    void clickButton(By buttonLocator);
}
