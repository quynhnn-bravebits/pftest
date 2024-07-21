package org.pftest.api.types.page;

import org.pftest.api.types.BaseResponseType;

class PageItemsConfigs extends BaseResponseType {
    public Boolean published;
}

public class PageItemsProps extends BaseResponseType {
    public String _id;
    public PageItemsConfigs configs;
    public String deletedAt;
    public Boolean importPage;
    public String pageflyVersion;
    public String preview;
    public ShopifyPageProps shopifyPage;
    public String status;
    public String title;
    public String type;
    public String updatedAt;
    public String publishedAt;
}
