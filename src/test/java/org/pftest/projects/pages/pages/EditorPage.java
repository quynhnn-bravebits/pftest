package org.pftest.projects.pages.pages;

import org.openqa.selenium.By;
import org.pftest.projects.CommonPage;

import java.util.UUID;

import static org.pftest.keywords.WebUI.*;

public class EditorPage extends CommonPage {
    private By headerBar = By.id("editor-header-bar");
    private By inspector = By.id("editor--inspector");
    private By pageOutline = By.id("page-outline-section");
    private By editorDnd = By.id("editor-dnd-wrapper");
    private By pageTitle = By.id("editor-header-bar--page-title");

//    public void openHomePage() {
//        String URL = "https://admin.shopify.com/store/quynhquynhiee/apps/rc-pagefly-1/editor?type=home&id=" + UUID.randomUUID();
//        openWebsite(URL);
//    }


    public void changePageTitle (String title) {

    }
}
