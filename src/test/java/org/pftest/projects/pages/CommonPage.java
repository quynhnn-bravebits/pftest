package org.pftest.projects.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.pftest.projects.commons.Toast;
import org.pftest.projects.pages.dashboard.DashboardScreen;
import org.pftest.projects.pages.pages.EditorPage;
import org.pftest.projects.pages.pages.PageListingScreen;
import org.pftest.projects.pages.pages.PageSettingScreen;
import org.pftest.projects.pages.pages.SectionListingScreen;

import static org.pftest.keywords.WebUI.*;

public class CommonPage {
    protected By portalContainer = By.id("PolarisPortalsContainer");
    protected By pageTitle = By.xpath("//h1[@class='Polaris-Header-Title']");
    private By crispChatBox = By.xpath("//*[@id='crisp-chatbox']//*[@data-chat-status='ongoing']");
    protected By modal = By.xpath("//*[@id=\"PolarisPortalsContainer\"]//*//div[@role=\"dialog\"]/div[1]");

    private Toast toast;
    private EditorPage editorPage;
    private PageListingScreen pageListingScreen;
    private PageSettingScreen pageSettingScreen;
    private DashboardScreen dashboardScreen;
    private SectionListingScreen sectionListingScreen;

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

    public DashboardScreen getDashboardScreen() {
        if (dashboardScreen == null) {
            dashboardScreen = new DashboardScreen();
        }
        return dashboardScreen;
    }

    public SectionListingScreen getSectionListingScreen() {
        if (sectionListingScreen == null) {
            sectionListingScreen = new SectionListingScreen();
        }
        return sectionListingScreen;
    }

    public void verifyCrispChatBoxOpened() {
        verifyElementVisible(crispChatBox);
        Dimension size = getSizeElement(crispChatBox);
        verifyTrue(size.height > 0 && size.width > 0);
    }

    public void verifyCrispChatBoxClosed() {
        verifyElementNotVisible(crispChatBox);
        Dimension size = getSizeElement(crispChatBox);
        verifyTrue(size.height * size.width == 0);
    }

    @Step("Verify page title is {0}")
    public void verifyPageTitle(String title) {
        verifyElementVisible(pageTitle);
        verifyElementText(pageTitle, title);
    }
}
