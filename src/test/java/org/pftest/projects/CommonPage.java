package org.pftest.projects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.projects.commons.Toast;
import org.pftest.projects.pages.pages.EditorPage;
import org.pftest.projects.pages.pages.PageListingScreen;
import org.pftest.projects.pages.pages.PageSettingScreen;

import static org.pftest.keywords.WebUI.verifyElementText;
import static org.pftest.keywords.WebUI.verifyElementVisible;

public class CommonPage {
    private By portalContainer = By.id("PolarisPortalsContainer");
    private By pageTitle = By.xpath("//h1[@class='Polaris-Header-Title']");

    private Toast toast;
    private EditorPage editorPage;
    private PageListingScreen pageListingScreen;
    private PageSettingScreen pageSettingScreen;

    public Toast getToast() {
        if (toast == null) {
            toast = new Toast();
        }
        return toast;
    }

    public EditorPage getEditorPage() {
        if (editorPage == null) {
            editorPage = new EditorPage();
        }
        return editorPage;
    }

    public PageListingScreen getPageListingScreen() {
        if (pageListingScreen == null) {
            pageListingScreen = new PageListingScreen();
        }
        return pageListingScreen;
    }

    public PageSettingScreen getPageSettingScreen() {
        if (pageSettingScreen == null) {
            pageSettingScreen = new PageSettingScreen();
        }
        return pageSettingScreen;
    }

    @Step("Verify page title is {0}")
    public void verifyPageTitle(String title) {
        verifyElementVisible(pageTitle);
        verifyElementText(pageTitle, title);
    }
}
