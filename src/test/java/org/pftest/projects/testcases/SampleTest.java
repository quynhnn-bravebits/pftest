package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.testng.annotations.Test;


@Epic("Sample Test")
@Feature("Sample Test Feature")
public class SampleTest extends BaseTest {
    @Test
    public void sampleTest() {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().changePageTitle("Test Page");
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().confirmBeforePublishModal_TitledTitle();
        getEditorPage().verifyPageSavedAndPublished();
    }
}
