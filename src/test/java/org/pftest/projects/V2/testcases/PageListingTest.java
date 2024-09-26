package org.pftest.projects.V2.testcases;


import io.qameta.allure.Link;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.pftest.base.BaseTestV2;
import org.pftest.enums.pagefly.PageType;
import org.testng.annotations.Test;

import static org.pftest.keywords.WebUI.sleep;

public class PageListingTest extends BaseTestV2 {

    public void createCommonPageFromTemplate(PageType pageType, String pageTitle) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFromTemplate(pageType);
        getCommonPageEditor().changePageTitle(pageTitle);
        getCommonPageEditor().save();
        getCommonPageEditor().publish();
    }

    public void createProductPageFromTemplate(String pageTitle) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFromTemplate(PageType.PRODUCT);
        getProductPageEditor().changePageTitle(pageTitle);
        getProductPageEditor().save();
        getProductPageEditor().publish();
    }

    @Test(description = "TC-011")
    public void test() {
//      openEditor();
        createProductPageFromTemplate("Product Page");
//      sleep(20);
    }


}
