package org.pftest.api.types.page;

import org.json.JSONObject;
import org.pftest.api.types.BaseResponseType;

import java.util.List;

class HistoryChangePageTypeProps extends BaseResponseType {
    public String _id;
    public String pageId;
    public String type;
    public String start;
    public String end;
}

public class ShopifyPageProps extends BaseResponseType {
    public String _id;
    public Integer __v;
    public List<String> collections;
    public String handle;
    public List<HistoryChangePageTypeProps> historyChangePageType;
    public List<JSONObject> historyCollectionsAssigned;
    public JSONObject historyProductsAssigned;
    public String id;
    public List<String> oldProducts;
    public Boolean oldSetAll;
    public List<String> products;
    public List<JSONObject> selectedTags;
    public Boolean setAll;
    public String blogId;
    public String created_at;
    public String updated_at;
    public String published_at;
    public String shop_id;
    public String template_suffix;
    public String title;

}
