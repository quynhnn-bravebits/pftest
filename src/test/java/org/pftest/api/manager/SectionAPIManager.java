package org.pftest.api.manager;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.types.section.FetchSectionUsageResponse;

import static org.pftest.utils.HttpRequestUtils.sendRequest;

public class SectionAPIManager {
    @Step("{url}/section/usage/{id}")
    public static FetchSectionUsageResponse getPagesUsingSectionById(String url, String id) throws JsonProcessingException {
        return sendRequest(FetchSectionUsageResponse.class, url + "/section/usage/" + id, HttpMethod.GET);
    }


}
