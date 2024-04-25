package org.pftest.projects;

import org.openqa.selenium.By;
import org.pftest.projects.pages.pages.AdminShopifyPage;
import org.pftest.projects.pages.pages.EditorPage;

public class CommonPage {
    private By portalContainer = By.id("PolarisPortalsContainer");

    private AdminShopifyPage editorPage;

    public AdminShopifyPage getEditorPage() {
        if (editorPage == null) {
            editorPage = new AdminShopifyPage();
        }
        return editorPage;
    }
}
