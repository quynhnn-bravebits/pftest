package org.pftest.projects.V2.components.modal.selectEditorType;

import org.openqa.selenium.By;
import org.pftest.enums.pagefly.EditorType;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.keywords.WebUI;
import org.pftest.projects.V2.components.modal.BaseModal;

/*
 * This modal is shown before open the editor page
 * It allows the user to choose layout type for the editor page
 *  - Flex editor: Using flex system
 *  - Basic editor: Using grid system
 */
public class SelectEditorTypeModal extends BaseModal {

    public SelectEditorTypeModal() {
        super(ModalType.SELECT_EDITOR_TYPE);
        this.locator = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//div[starts-with(@data-portal-id, 'overlay')]//div[@role=\"dialog\"]");
    }

    public void selectEditorOption(EditorType editorType) {
        WebUI.switchToCreateNewPageFrame();
        WebUI.clickElement(editorType.getOptionLocator());
    }
}
