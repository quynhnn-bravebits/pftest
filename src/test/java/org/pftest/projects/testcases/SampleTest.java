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

import static org.pftest.keywords.WebUI.takeFullPageScreenshot;
import static org.pftest.keywords.WebUI.takeFullPageScreenshotBMP;


@Epic("Sample Test")
@Feature("Sample Test Feature")
public class SampleTest extends BaseTest {
    @Test
    public void sampleTest() throws IOException {
        getPageListingScreen().openPageListingPage();
        String id = "bc8ec280-a756-4f4a-a119-1080ed51a8f0";
        getPageListingScreen().openPageInPageListing(id);
        getEditorPage().verifyEditorPageLoaded();
        String img1 = takeFullPageScreenshotBMP("img1");
        System.out.println(img1);
        getEditorPage().backToPageListingScreen();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().openPageInPageListing(id);
        getEditorPage().verifyEditorPageLoaded();
        String img2 = takeFullPageScreenshotBMP("img2");
        System.out.println(img2);
        BufferedImage bufferedImg1 = ImageIO.read(new File(img1));
        BufferedImage bufferedImg2 = ImageIO.read(new File(img2));
        boolean result = ImageUtils.bufferedImagesEqual(bufferedImg1, bufferedImg2);
        System.out.println(result);
        assert result;
    }
}
