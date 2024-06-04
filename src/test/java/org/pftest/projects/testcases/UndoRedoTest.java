package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.pftest.report.AllureManager;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.pftest.keywords.WebUI.sleep;
import static org.pftest.keywords.WebUI.verifyEquals;

@Epic("Undo/Redo")
public class UndoRedoTest extends BaseTest {
    @Feature("Template page")
    @Test(description = "UR-001: User undo when change the heading element in template page")
    public void undoChangeTextContentOfHeadingElement()  {
        List<String> canvasHtml = new ArrayList<>();
        int currentStep = -1;

        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;

        getEditorPage().dragAndDropHeadingElement();
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;

        getEditorPage().changeContentColor("rgb(239, 45, 201)");
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;

        getEditorPage().changeOpacity_Input("50");
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;

        getEditorPage().changePaddingValue(20);
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;

        getEditorPage().clickUndoButton();
        sleep(1);
        currentStep--;
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickUndoButton();
        getEditorPage().clickUndoButton();
        sleep(1);
        currentStep -= 2;
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickUndoButton();
        currentStep--;
        sleep(1);
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));
    }

    @Feature("Template page")
    @Test(description = "UR-002: User undo and redo in template page")
    public void undoRedoChangeTextContentOfHeadingElement() {
        List<String> canvasHtml = new ArrayList<>();
        int currentStep = -1;
        getPageListingScreen().openPageListingPage();
        getPageListingScreen().verifyPageListingLoaded();
        getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
        getEditorPage().verifyEditorPageLoaded();
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();

        getEditorPage().dragAndDropHeadingElement();
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();

        getEditorPage().changeContentColor();
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();

        getEditorPage().changeOpacity_Input("50");
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();

        getEditorPage().changePaddingValue(20);
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();

        getEditorPage().clickUndoButton();
        currentStep--;
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickUndoButton();
        currentStep--;
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickRedoButton();
        currentStep++;
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().dragAndDropParagraphElement();
        canvasHtml = canvasHtml.subList(0, currentStep + 1);
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();
        sleep(1);

        getEditorPage().changeTextContent("This is a new paragraph");
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();
        sleep(1);

        getEditorPage().clickUndoButton();
        currentStep--;
        sleep(2);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickRedoButton();
        currentStep++;
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

        getEditorPage().clickUndoButton();
        getEditorPage().clickUndoButton();
        getEditorPage().clickUndoButton();
        currentStep -= 3;
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(getEditorPage().getCanvasHtml(), canvasHtml.get(currentStep));

    }

}
