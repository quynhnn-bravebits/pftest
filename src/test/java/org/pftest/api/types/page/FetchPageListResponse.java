package org.pftest.api.types.page;

import org.pftest.api.types.BaseResponseType;

import java.util.List;

class FetchPageListResponseData extends BaseResponseType {
    public Integer count;
    public List<PageItemsProps> items;
    public Integer page;
    public Integer publishedPagesCount;
    public Integer total;
    public List<String> unlockedPages;
}

public class FetchPageListResponse extends BaseResponseType {
    public Integer success;
    public FetchPageListResponseData data;
}
