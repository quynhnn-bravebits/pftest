package org.pftest.api.types.section;

import org.pftest.api.types.page.FetchPageDataResponse;

import java.util.ArrayList;

class FetchSectionUsage extends FetchPageDataResponse {
    public String configs;
    public ArrayList<String> items;
    public String shopifyPage;
    public ArrayList<String> styles;
}

class FetchSectionUsageData {
    public String pageId;
    public FetchSectionUsage usage;
}

public class FetchSectionUsageResponse extends FetchPageDataResponse {
    public Integer success;
    public ArrayList<FetchSectionUsageData> data;
}
