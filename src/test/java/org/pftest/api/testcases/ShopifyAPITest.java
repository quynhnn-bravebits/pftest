package org.pftest.api.testcases;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.pftest.api.manager.ShopifyAPIManager;
import org.pftest.keywords.WebUI;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ShopifyAPITest extends BaseAPITest {

    @Parameters({"edit-with-pagefly"})
    @Test(description = "Get edit with pagefly")
    public void editWithPageflyTest() throws JsonProcessingException {
        WebUI.openWebsite( "https://admin.shopify.com/store/quynhquynhiee/products?selectedView=all");
        String pageType = "product";
        String id = "123";
        ShopifyAPIManager.getEditWithPagefly(baseUrl, pageType, id);
    }
}
