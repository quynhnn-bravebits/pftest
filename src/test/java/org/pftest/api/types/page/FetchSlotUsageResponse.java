package org.pftest.api.types.page;

import java.util.List;

class FetchSlotUsageResponseData {
    public String _id;
    public String type;
    public Integer lockedPagesCount;
    public Integer unlockedPagesCount;
    public Integer publishedPagesCount;
    public Integer createdPagesCount;
}

public class FetchSlotUsageResponse {
    public Integer success;
    public List<FetchSlotUsageResponseData> data;
}
