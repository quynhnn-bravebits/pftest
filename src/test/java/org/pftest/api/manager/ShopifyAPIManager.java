package org.pftest.api.manager;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.types.pagefly.EditWithPageflyResponse;

import static org.pftest.utils.HttpRequestUtils.sendRequest;

public class ShopifyAPIManager {
    @Step("{url}/edit-with-pagefly?pageType={pageType}&id={id}")
    public static EditWithPageflyResponse getEditWithPagefly(String url, String pageType, String id) throws JsonProcessingException {
        return sendRequest(EditWithPageflyResponse.class, url + "/edit-with-pagefly?pageType=" + pageType + "&id=" + id , HttpMethod.GET);
    }
}
