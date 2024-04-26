package org.pftest.projects;

import org.openqa.selenium.By;
import org.pftest.projects.pages.pages.EditorPage;

public class CommonPage {
    private By portalContainer = By.id("PolarisPortalsContainer");

    private EditorPage editorPage;

    public EditorPage getEditorPage() {
        if (editorPage == null) {
            editorPage = new EditorPage();
        }
        return editorPage;
    }
}
