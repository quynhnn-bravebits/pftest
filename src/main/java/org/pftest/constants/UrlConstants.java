package org.pftest.constants;

import org.pftest.enums.PageType;

public class UrlConstants {
    private static final String store = "quynhquynhiee";
    private static final String app = "wip-pagefly";

    public static final String SHOPIFY_BASE_URL = String.format("https://admin.shopify.com/store/%s", store);
    public static final String PF_COMMUNITY_URL = "https://www.facebook.com/groups/pagefly";
    public static final String VIDEO_TUTORIALS_URL = "https://www.youtube.com/c/PageFlyShopifyPageBuilderApp";
    public static final String PF_BASE_URL = String.format("https://admin.shopify.com/store/%s/apps/%s", store, app);
    public static final String PF_DASHBOARD_URL = String.format("%s/dashboard", PF_BASE_URL);
    public static final String PF_PAGES_URL = String.format("%s/pages", PF_BASE_URL);
    public static final String PF_SECTIONS_URL = String.format("%s/sections", PF_BASE_URL);
    public static String PF_EDITOR_URL(PageType pageType, String id) {
        return String.format("%s/editor?type=%s&id=", PF_BASE_URL, pageType.name().toLowerCase(), id);
    }

    public static final String HELP_CENTER_URL = "https://help.pagefly.io/";
}
