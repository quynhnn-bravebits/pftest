package org.pftest.api.types.page;

import org.pftest.api.types.BaseResponseType;

import java.util.List;

public class GetPageInfoResponse extends BaseResponseType {
    public Integer count;
    public Integer publishedPagesCount;
    public List<String> unlockedPages;
}
