package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.modal.factory.CommonPageModalFactory;
import org.pftest.projects.V2.components.modal.factory.PageModalFactory;

import static org.pftest.keywords.WebUI.*;

public class CommonPageEditor extends BaseEditor {
    @Override
    public void publish() {
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        new CommonPageModalFactory().createBeforePublishPageModal_Titled().clickPrimaryButton();
        switchToDefaultContent();
        Toast.verifyShowPublishingPageToast();
        Toast.verifyShowPublishedPageToast();
        switchToEditorFrame();
        PageHeader.verifyPublishStatus();
    }

    @Override
    public PageModalFactory getModalFactory() {
        return new CommonPageModalFactory();
    }
}
