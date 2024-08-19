package org.pftest.api.manager;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.types.page.*;

import static org.pftest.utils.HttpRequestUtils.sendRequest;
import static org.pftest.utils.HttpRequestUtils.waitForGlobalAuthData;

public class PageAPIManager {
    @Step("{url}/v2/pages?limit={limit}&page={page}&type=&title={title}&sortBy=updatedAt&orderBy=desc")
    public static FetchPageListResponse fetchPageList(String url, String title, int limit, int page) throws JsonProcessingException {
        return sendRequest(FetchPageListResponse.class, url + "/v2/pages?limit="+limit+"&page="+page+"&type=&title="+title+"&sortBy=updatedAt&orderBy=desc'", HttpMethod.GET);
    }

    @Step("{url}/pages/info")
    public static GetPageInfoResponse getPageInfo(String url) throws JsonProcessingException {
        return sendRequest(GetPageInfoResponse.class, url+"/pages/info", HttpMethod.GET);
    }

    @Step("{url}/page/{id}")
    public static FetchPageDataResponse fetchPageData(String url, String id) throws JsonProcessingException {
        return sendRequest(FetchPageDataResponse.class, url+"/page/"+id, HttpMethod.GET);
    }

    @Step("{url}/pages/usage")
    public static FetchSlotUsageResponse fetchSlotUsage (String url) throws JsonProcessingException {
         return sendRequest(FetchSlotUsageResponse.class, url+"/pages/usage", HttpMethod.GET);
    }

    @Step("{url}/pages/delete-permanently?id={id}")
    public static PageDataFromTrashResponse deletePagePermanentlyById(String url, String id) throws JsonProcessingException {
        return sendRequest(PageDataFromTrashResponse.class, url+"/pages/delete-permanently?id="+id, HttpMethod.GET);
    }

    @Step("{url}/pages/recover?id={id}")
    public static PageDataFromTrashResponse recoverPageById(String url, String id) throws JsonProcessingException {
        return sendRequest(PageDataFromTrashResponse.class, url+"/pages/recover?id="+id, HttpMethod.GET);
    }

    @Step("{url}/export-pages?type={type}")
    public static void exportPages(String url, String type) throws JsonProcessingException {
//        String authData = (String) WebUI.getJsExecutor().executeScript("return window.globalAuth.data");
        String authData =  waitForGlobalAuthData();
        System.out.println(authData);
        String response = sendRequest(String.class, url + "/export-pages?type=" + type + "&authData=" + authData, HttpMethod.GET);
        System.out.println(response);
    }

}
