package org.pftest.api.manager;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.types.page.FetchPageDataResponse;
import org.pftest.api.types.page.FetchPageListResponse;
import org.pftest.api.types.page.GetPageInfoResponse;

import static org.pftest.utils.HttpRequestUtils.sendRequest;

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

}
