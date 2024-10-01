package org.pftest.projects.V2.testcases;


import org.pftest.base.BaseTestV2;
import org.pftest.enums.pagefly.PageType;
import org.testng.annotations.Test;

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

    public void createRegularPageFromBlank(String pageTitle) {
        openPageListingPage();
        getPageListing().verifyPageLoaded();
        getPageListing().createFromBlank(PageType.PAGE);
        getCommonPageEditor().changePageTitle(pageTitle);
        getCommonPageEditor().save();
        getCommonPageEditor().publish();
    }

    @Test(description = "TC-011")
    public void test() {
//      openEditor();
//        createProductPageFromTemplate("Product Page");
        createRegularPageFromBlank("Regular Page");
//      sleep(20);
    }


}
