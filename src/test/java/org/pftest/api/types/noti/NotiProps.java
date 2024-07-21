package org.pftest.api.types.noti;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import org.pftest.api.types.BaseResponseType;

public class NotiProps extends BaseResponseType {
    public String _id;
    public String type;
    public String shopDomain;
    public String receivedTime;
    public Boolean read;
    public Boolean checked;
    public Boolean deleted;
    public Integer __v;
    public NotiDataProps data;
}

