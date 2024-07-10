package org.pftest.projects.V2.components.modal.modals.beforePublish;

import org.openqa.selenium.By;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.enums.pagefly.PageType;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.assignment.PageAssignmentModal;

public class BeforePublishProductCollectionPageModal extends BeforePublishPageModal{
    private PageAssignmentModal pageAssignment;

    public BeforePublishProductCollectionPageModal(ModalType modalType) {
        super(modalType);
    }

    public void assign() {
        pageAssignment.assignProductCollection();
    }

    public void publish(PageType pageType, String title) {
        fillPageTitle(title);
        clickButton(By.id("menubar--save-modal--primary"));
        WebUI.sleep(0.5);

        if (pageType == PageType.PRODUCT) {
            pageAssignment.verifyPageAssignmentModalVisible();
            pageAssignment.assignProductCollection();
            clickButton(By.id("menubar--save-modal--primary"));
            WebUI.sleep(0.5);
        }

        verifyNotVisible();
    }
}
