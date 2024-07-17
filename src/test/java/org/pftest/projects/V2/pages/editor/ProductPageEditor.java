package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.modal.factory.PageModalFactory;
import org.pftest.projects.V2.components.modal.factory.ProductPageModalFactory;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.keywords.WebUI.switchToEditorFrame;

public class ProductPageEditor extends BaseEditor {

    @Override
    public void publish() {
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        new ProductPageModalFactory().createBeforePublishPageModal_Titled().clickPrimaryButton();
    }

    @Override
    public PageModalFactory getModalFactory() {
        return new ProductPageModalFactory();
    }
}
