package org.pftest.projects.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pftest.projects.CommonPage;

import java.util.List;
import java.util.UUID;

import static org.pftest.keywords.WebUI.openWebsite;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/pageflybackend-9/pages
public class AdminShopifyPage extends CommonPage {

    public void openHomePage() {
        String URL = "https://admin.shopify.com/store/quynhquynhiee/apps/rc-pagefly-1/editor?type=home&id=" + UUID.randomUUID();
        openWebsite(URL);
    }
}