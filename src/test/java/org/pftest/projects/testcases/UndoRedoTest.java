package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.pftest.base.BaseTest;
import org.pftest.enums.PageType;
import org.pftest.report.AllureManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.pftest.keywords.WebUI.sleep;
import static org.pftest.keywords.WebUI.verifyEquals;

@Epic("Undo/Redo")
public class UndoRedoTest extends BaseTest {
    private List<String> canvasHtml = new ArrayList<>();
    private int currentStep = -1;

    @BeforeMethod
    public void resetSteps () {
        canvasHtml = new ArrayList<>();
        currentStep = -1;
    }

    @Step("{description}")
    public void addStep(String description, Runnable step) {
        step.run();
        canvasHtml = canvasHtml.subList(0, currentStep + 1);
        canvasHtml.add(getEditorPage().getCanvasHtml());
        currentStep++;
        AllureManager.takeScreenshotStep();
    }

    @Step("{description}")
    public void undo(String description, int steps) {
        for (int i = 0; i < steps; i++) {
            getEditorPage().clickUndoButton();
            currentStep--;
        }

        sleep(1);
        AllureManager.takeScreenshotStep();
        String expected = canvasHtml.get(currentStep);
        String actual = getEditorPage().getCanvasHtml();
        verifyEquals(actual, expected);
    }

    @Step("{description}")
    public void redo(String description, int steps) {
        for (int i = 0; i < steps; i++) {
            getEditorPage().clickRedoButton();
            currentStep++;
        }
        sleep(1);
        String expected = canvasHtml.get(currentStep);
        String actual = getEditorPage().getCanvasHtml();
        verifyEquals(actual, expected);
    }

    @Feature("Template page")
    @Test(description = "UR-001: User undo when change the heading element in template page")
    public void undoChangeTextContentOfHeadingElement() {
        addStep(
                "Step 0: Init template page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                    getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
                    getEditorPage().verifyEditorPageLoaded();
                }
        );

        addStep(
                "Step 1: Drag and drop heading element",
                () -> getEditorPage().dragAndDropHeadingElement()
        );

        addStep(
                "Step 2: Change content color",
                () -> getEditorPage().changeContentColor("rgb(239, 45, 201)")
        );

        addStep(
                "Step 3: Change opacity",
                () -> getEditorPage().changeOpacity_Input("50")
        );

        addStep(
                "Step 4: Change padding value",
                () -> getEditorPage().changePaddingValue(20)
        );

        sleep(1);

        undo(
                "Step 5: Undo change padding value",
                1
        );

        undo(
                "Step 6: Undo change opacity and content color",
                2
        );

        undo(
                "Step 7: Undo drag and drop heading element",
                1
        );

        getEditorPage().verifyUndoButtonDisabled();
    }

    @Feature("Template page")
    @Test(description = "UR-002: User undo and redo in template page")
    public void undoRedoChangeTextContentOfHeadingElement() {
        addStep(
                "Step 0: Init template page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                    getPageListingScreen().createNewPageFromTemplate(PageType.PAGE);
                    getEditorPage().verifyEditorPageLoaded();
                }
        );

        addStep(
                "Step 1: Drag and drop heading element",
                () -> getEditorPage().dragAndDropHeadingElement()
        );

        addStep(
                "Step 2: Change content color",
                () -> getEditorPage().changeContentColor("rgb(239, 45, 201)")
        );

        addStep(
                "Step 3: Change opacity",
                () -> getEditorPage().changeOpacity_Input("50")
        );

        addStep(
                "Step 4: Change padding value",
                () -> getEditorPage().changePaddingValue(20)
        );

        sleep(1);

        undo(
                "Step 5: Undo change padding value",
                1
        );

        undo(
                "Step 6: Undo change opacity",
                1
        );

        redo(
                "Step 7: Redo change opacity",
                1
        );

        addStep(
                "Step 8: Drag and drop paragraph element",
                () -> getEditorPage().dragAndDropParagraphElement()
        );

        addStep(
                "Step 9: Change paragraph text content",
                () -> getEditorPage().changeTextContent("This is a new paragraph")
        );

        undo(
                "Step 10: Undo change text content",
                1
        );

        redo(
                "Step 11: Redo change text content",
                1
        );

        undo (
                "Step 12: Undo all",
                5
        );

        getEditorPage().verifyUndoButtonDisabled();
    }

}
