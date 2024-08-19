package org.pftest.api.testcases;

import io.qameta.allure.Story;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.openqa.selenium.remote.http.HttpMethod;
import org.pftest.api.manager.NotificationsAPIManager;
import org.pftest.api.types.noti.GetInfiniteNotiListResponse;
import org.pftest.api.types.noti.MarkAllNotiAsCheckedResponse;
import org.pftest.api.types.noti.NotiProps;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.util.List;

import static org.pftest.utils.HttpRequestUtils.sendRequest;

@Story("Notifications API")
public class NotificationsAPITest extends BaseAPITest {
    private final String url = baseUrl + "/noti?";
    private List<NotiProps> notiPropsList;

    @Test(description = "Get all notifications")
    public void getInfiniteNotiListTest() throws JsonProcessingException {
        GetInfiniteNotiListResponse response  = NotificationsAPIManager.getInfiniteNotiList(url, shop, 25, 1);
        notiPropsList = response.notis;
    }

    @Test(description = "Open a notification", dependsOnMethods = "getInfiniteNotiListTest")
    public void markAllNotiAsCheckedTest() throws JsonProcessingException {
        // open a notification
        String _id = notiPropsList.get(0)._id;
        MarkAllNotiAsCheckedResponse response = NotificationsAPIManager.markAllNotiAsChecked(url, _id);
    }

//    @Test(description = "Create new notification for new store")
//    public void sendUserActionTest() throws JsonProcessingException {
//        // create new notification for new store
//        MarkAllNotiAsCheckedResponse response = sendRequest(MarkAllNotiAsCheckedResponse.class, url + "shop=" + shop + "&type=WELCOME_NEW_USER", HttpMethod.POST);
//        System.out.println(response);
//    }

}
