package org.pftest.api.types.noti;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import org.pftest.api.types.BaseResponseType;

import java.util.List;

public class GetInfiniteNotiListResponse extends BaseResponseType {
    public Integer success;
    public boolean isNext;
    public List<NotiProps> notis;

}
