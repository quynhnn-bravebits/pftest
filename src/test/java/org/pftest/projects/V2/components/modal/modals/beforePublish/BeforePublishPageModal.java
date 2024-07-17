package org.pftest.projects.V2.components.modal.modals.beforePublish;

import org.openqa.selenium.By;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.modal.modals.BaseModal;


public class BeforePublishPageModal extends BaseModal implements IBeforePublishPageModal {

    public BeforePublishPageModal(ModalType modalType) {
        super(modalType);
    }

    @Override
    public void clickPrimaryButton() {
        WebUI.clickElement(By.id("menubar--save-modal--primary"));
    }

    public void fillPageTitle(String title) {
        WebUI.clearAndFillText(By.id("menubar--save-modal--page-title"), title);
    }

    public void fillPageUrl(String url) {
        WebUI.clearAndFillText(By.id("menubar--save-modal--page-url"), url);
    }



}
