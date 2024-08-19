package org.pftest.api.testcases;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.pftest.api.manager.PageAPIManager;
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

    @Test(description = "Fetch page data by id", groups = {"PAGES"})
    @Parameters({"/page/:id"})
    public void fetchPageDataTest() throws JsonProcessingException {
        switchToDefaultContent();
        openPageListingPage();
        String id = getPageListing().getPageIdInPageListing(1);
        PageAPIManager.fetchPageData(baseUrl, id);
    }

    @Test(description = "Fetch slot usage")
    @Parameters({"/pages/usage"})
    public void fetchSlotUsageTest() throws JsonProcessingException {
        PageAPIManager.fetchSlotUsage(baseUrl);
    }

    @Test(description = "Delete page permanently by id", groups = {"TRASH"})
    @Parameters({"/pages/delete-permanently"})
    public void deletePagePermanentlyByIdTest() throws JsonProcessingException {
        openTrashPage();
        String id = getPageListing().getPageIdInPageListing(1);
        PageAPIManager.deletePagePermanentlyById(baseUrl, id);
    }

    @Test(description = "Recover page by id", groups = {"TRASH"})
    @Parameters({"/pages/recover"})
    public void recoverPageByIdTest() throws JsonProcessingException {
        openTrashPage();
        String id = getPageListing().getPageIdInPageListing(1);
        PageAPIManager.recoverPageById(baseUrl, id);
    }


    // TODO Bad Unicode escape in JSON
    @Test(description = "Export pages", groups = {"PAGES"})
    @Parameters({"/export-pages"})
    public void exportPagesTest() throws JsonProcessingException {
        PageAPIManager.exportPages(baseUrl, "all");
    }

    
}
