package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.common.toast.Toast;
import org.pftest.projects.V2.components.editor.PageHeader;
import org.pftest.projects.V2.components.popover.publish.PublishPagePopover;

import static org.pftest.keywords.WebUI.*;
import static org.pftest.keywords.WebUI.switchToEditorFrame;

public class HomePageEditor extends BaseEditor {

    public void publish() {
        switchToDefaultContent();
        waitForElementVisible(publishButton);
        clickElement(publishButton);
        switchToEditorFrame();
        new PublishPagePopover().clickPublish();
        sleep(0.5);
        new PublishPagePopover().clickPublish();
        switchToDefaultContent();
        Toast.verifyShowPublishingPageToast();
        Toast.verifyShowPublishedPageToast();
        switchToEditorFrame();
        PageHeader.verifyPublishStatus();
    }

}
