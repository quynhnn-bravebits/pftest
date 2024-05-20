package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.pftest.utils.ImageUtils;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.pftest.keywords.WebUI.takeFullPageScreenshotBMP;


@Epic("Sample Test")
@Feature("Sample Test Feature")
public class SampleTest extends BaseTest {
    @Test
    public void sampleTest() throws IOException {
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        getEditorPage().clickSaveAndPublishPageButton();
        getEditorPage().verifyPageIsSaving();
        getEditorPage().confirmBeforePublishSectionModal_UntitledTitle();
        getToast().verifyShowPublishingSectionToast();
        getToast().verifyShowPublishedSectionToast();
        getEditorPage().closePublishedSectionModal();
        getEditorPage().closeEnableAutoSaveModal();

    }
}
