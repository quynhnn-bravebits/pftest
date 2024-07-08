package org.pftest.projects.V2.pages.pageListing;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.projects.V2.pages.BaseListingScreen;

public class PageListing extends BaseListingScreen {

    private final By createFromTemplateButton = new ByChained(actionMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_TEMPLATE_BUTTON + "']"));
    private final By createFromBlankButton = new ByChained(actionMenu, By.xpath(".//*/button/span[text()='" + PagesConstants.CREATE_FROM_BLANK_BUTTON + "']"));

}
