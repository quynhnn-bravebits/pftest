package org.pftest.api.testcases;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.pftest.api.manager.PageAPIManager;
import org.pftest.projects.V2.pages.Bridge;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.pftest.keywords.WebUI.*;

public class PageAPITest extends BaseAPITest {

    @Test(description = "Fetch page list by title")
    @Parameters({"/v2/pages"})
    public void fetchPageListTest() throws JsonProcessingException {
        String title = "Slo";
        int limit = 25;
        int page = 1;
        PageAPIManager.fetchPageList(baseUrl, title, limit, page);
    }

    @Test(description = "Get page info")
    @Parameters({"/pages"})
    public void getPageInfoTest() throws JsonProcessingException {
        PageAPIManager.getPageInfo(baseUrl);
    }

    @Test(description = "Fetch page data by id")
    @Parameters({"/pages/:id"})
    // @todo: failed
    public void fetchPageDataTest() throws JsonProcessingException {
        switchToDefaultContent();
        openPageListingPage();
        String id = getPageListing().getPageIdInPageListing(1);
        PageAPIManager.fetchPageData(baseUrl, id);
    }
}
