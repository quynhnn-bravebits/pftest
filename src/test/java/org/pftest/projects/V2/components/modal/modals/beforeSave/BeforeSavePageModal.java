package org.pftest.projects.V2.components.modal.modals.beforeSave;

import org.openqa.selenium.By;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.modal.modals.BaseModal;

import static org.pftest.keywords.WebUI.clickElement;


public class BeforeSavePageModal extends BaseModal {
    public BeforeSavePageModal() {
        super(ModalType.BEFORE_SAVE_PAGE);
    }

    public void fillPageTitle(String title) {
        WebUI.clearAndFillText(By.id("menubar--save-modal--page-title"), title);
    }

    public void fillPageTitleAndSave(String title) {
        fillPageTitle(title);
        clickPrimaryButton();
    }

    @Override
    public void clickPrimaryButton() {
        clickButton(By.id("menubar--save-modal--save"));
    }

}
