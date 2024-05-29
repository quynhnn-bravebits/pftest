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

import static org.pftest.keywords.WebUI.*;


@Epic("Sample Test")
@Feature("Sample Test Feature")
public class SampleTest extends BaseTest {
    @Test
    public void sampleTest() {
       getPageListingScreen().openPageListingPage();
         getPageListingScreen().verifyPageListingLoaded();
         getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
         getEditorPage().verifyEditorPageLoaded();
         getEditorPage().dragAndDropHeadingElement();
         getEditorPage().changeContentColor();

//        getJsExecutor().executeScript(
//                "function a () {return pfSelected.state}; window.a = a"
//        );
//        getJsExecutor().executeScript("a();return a()");
    }
}
