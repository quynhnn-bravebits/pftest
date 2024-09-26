package org.pftest.projects.V2.components.popover.publish;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.pftest.keywords.WebUI;

public class PublishBlogPagePopover extends PublishPagePopover {
    private By blogCollectionSelect = By.id("menubar--save-modal--blog-select");

    @Step("Select blog collection: {blogCollection}")
    public void selectBlogCollection(String blogCollection) {
        WebUI.selectOptionByText(blogCollectionSelect, blogCollection);
    }

}
