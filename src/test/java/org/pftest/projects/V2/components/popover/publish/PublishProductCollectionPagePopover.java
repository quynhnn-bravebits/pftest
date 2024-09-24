package org.pftest.projects.V2.components.popover.publish;

import org.pftest.enums.pagefly.PageType;

public class PublishProductCollectionPagePopover extends PublishPagePopover {
    private PageType pageType;
    private Boolean isAssigned;
    private String buttonContent;

    public PublishProductCollectionPagePopover(Boolean isAssigned, PageType pageType) {
        this.isAssigned = isAssigned;
        this.pageType = pageType;
    }
}
