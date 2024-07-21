package org.pftest.api.types.noti;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import org.pftest.api.types.BaseResponseType;

import java.util.List;

class PathsProps extends BaseResponseType {
    public String checked;
    public String deleted;
    public String read;
    public String receivedTime;
    public String shopDomain;
    @JsonProperty("type")
    public String _type;
    public String __v;
    public String _id;
}

class StatesInitProps extends BaseResponseType {
    public boolean checked;
    public boolean deleted;
    public boolean read;
    public boolean receivedTime;
    public boolean shopDomain;
    @JsonProperty("type")
    public boolean _type;
    public boolean __v;
    public boolean _id;
}

class StatesProps extends BaseResponseType{
    @JsonProperty("default")
    public JSONObject _default;
    public JSONObject ignore;
    public JSONObject modify;
    public JSONObject require;
    public StatesInitProps init;
}

class ActivePathsProps extends BaseResponseType {
    public PathsProps paths;
    public List<String> stateNames;
    public StatesProps states;

}

class UpdatedNoti$__Props extends BaseResponseType {
    public ActivePathsProps activePaths;
    public JSONObject exclude = null;
    public JSONObject fields;
    public JSONObject selected;
    public boolean skipId;
    public boolean strictMode;
    public String _id;
}


public class UpdatedNotiProps extends BaseResponseType {
    public UpdatedNoti$__Props $__;
    public String $isNew;
    public NotiDataProps data;
    public NotiProps _doc;
}
