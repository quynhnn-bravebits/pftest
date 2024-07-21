package org.pftest.api.manager;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.types.noti.GetInfiniteNotiListResponse;
import org.pftest.api.types.noti.MarkAllNotiAsCheckedResponse;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.pftest.utils.HttpRequestUtils.sendRequest;

public class NotificationsAPIManager {
    @Step("{url}shop={shop}&limit={limit}&page={page}")
    public static GetInfiniteNotiListResponse getInfiniteNotiList(String url, String shop, int limit, int page) throws JsonProcessingException {
        GetInfiniteNotiListResponse response  = sendRequest(GetInfiniteNotiListResponse.class,url + "shop="+shop+"&limit="+limit+"&page=" + page, HttpMethod.GET);
        return response;
    }

    @Step("{url}_id={_id}")
    public static MarkAllNotiAsCheckedResponse markAllNotiAsChecked(String url, String _id) throws JsonProcessingException {
        // open a notification
        MarkAllNotiAsCheckedResponse response = sendRequest(MarkAllNotiAsCheckedResponse.class, url + "_id=" + _id, HttpMethod.PUT);
        return response;
    }
}
