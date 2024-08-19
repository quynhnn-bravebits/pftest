package org.pftest.api.types.page;

import org.json.JSONObject;
import org.pftest.api.types.BaseResponseType;

import java.util.List;

class PageDataConfigs extends BaseResponseType {
    public Boolean applyNewImage;
    public Boolean asyncHelper;
    public Boolean autoSave;
    public String createdAt;
    public Boolean fitViewport;
    public Boolean forceByPassGoogleLightHouse;
    public List<String> ga4TrackingIDs;
    public Boolean hideHeaderFooter;
    public Boolean imageLazyLoad;
    public String initialTemplate;
    public String layout;
    public Boolean lazyLoad;
    public Boolean nativeImageLazyLoad;
    public Integer numSectionTemplates;
    public Boolean published;
    public String publishedTemplateAt;
    public Boolean showPageOutline;
    public List<String> trackingIDs;
    public List<String> updateHistory;
    public String updatedAt;
    public Boolean useThemeJQ;
    public Boolean viewCanvasSize;
    public Integer __v;
    public String _id;

}

class PageDataItem extends BaseResponseType {
    public List<String> children;
    public String createdAt;
    public List<String> styles;
    public String type;
    public String updatedAt;
    public Integer __v;
    public String _id;
}

class PageDataStyleProps extends BaseResponseType {
    public String type;
    public String styles;
    public String createdAt;
    public String updatedAt;
    public Integer __v;
    public String _id;
}

public class FetchPageDataResponse extends BaseResponseType {
    public String allCss;
    public PageDataConfigs configs;
    public String createdAt;
    public String customCss;
    public String customJs;
    public String deletedAt;
    public String header;
    public String html;
    public Boolean importPage;
    public List<PageDataItem> items;
    public String pageflyVersion;
    public List<String> preloadImages;
    public String preview;
    public String publishedAt;
    public String publishedHtml;
    public String shopDomain;
    public ShopifyPageProps shopifyPage;
    public String status;
    public List<PageDataStyleProps> styles;
    public String title;
    public String type;
    public String updatedAt;
    public Integer __v;
    public String _id;
}
