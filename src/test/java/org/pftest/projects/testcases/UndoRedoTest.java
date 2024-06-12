package org.pftest.projects.testcases;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pftest.base.BaseTest;
import org.pftest.driver.DriverManager;
import org.pftest.enums.PageType;
import org.pftest.report.AllureManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.pftest.keywords.WebUI.*;

@Epic("Undo/Redo")
public class UndoRedoTest extends BaseTest {
    private final PageEditingTest pageEditingTest = new PageEditingTest();
    private List<String> canvasHtml;
    private int currentStep;

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
        sleep(0.5);
    }

    @Step("{description}")
    public void undo(String description, int steps) {
        for (int i = 0; i < steps; i++) {
            getEditorPage().clickUndoButton();
            currentStep--;
        }

        waitForPageLoaded();
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
        waitForPageLoaded();
        sleep(1);
        AllureManager.takeScreenshotStep();
        String expected = canvasHtml.get(currentStep);
        String actual = getEditorPage().getCanvasHtml();
        verifyEquals(actual, expected);
    }

    @Feature("Template page")
    @Severity(CRITICAL)
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
    @Severity(CRITICAL)
    @Test(description = "UR-002: User undo and redo in template page")
    public void undoRedoInTemplatePage() {
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

    @Flaky
    @Feature("Version history")
    @Severity(CRITICAL)
    @Test(description = "UR-003: User undo and redo when change the page template")
    public void undoRedoChangePageTemplate() {
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

        addStep(
                "Step 5: Open and select page template",
                () -> {
                    getEditorPage().openAndSelectPageTemplate();
                    waitForPageLoaded();
                }
        );

        undo(
                "Step 6: Undo change page template",
                1
        );

        redo(
                "Step 7: Redo change page template",
                1
        );

        undo(
                "Step 8: Undo change template and padding value",
                2
        );

        undo(
                "Step 9: Undo change opacity and content color",
                2
        );

        undo(
                "Step 10: Undo drag and drop heading element",
                1
        );

        getEditorPage().verifyUndoButtonDisabled();
    }

    @Feature("Element")
    @Severity(CRITICAL)
    @Story("Layout")
    @Test(description = "UR-005: User undo and redo when change the layout element styles")
    public void undoRedoChangeLayoutElementStyles() {
        addStep(
                "Step 0: Init plank page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                    getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
                    getEditorPage().verifyEditorPageLoaded();
                }
        );

        addStep(
                "Step 1: Drag and drop layout element",
                () -> getEditorPage().dragAndDropLayoutElement()
        );

        addStep(
                "Step 2: Change content color",
                () -> getEditorPage().changeContentColor("rgb(239, 45, 201)")
        );

        addStep(
                "Step 3: Change padding value",
                () -> getEditorPage().changePaddingValue(20)
        );

        addStep(
                "Step 4: Change margin value",
                () -> getEditorPage().changeMarginValue(20)
        );

        addStep(
                "Step 5: Change font family",
                () -> getEditorPage().changeFontFamily("Abel")
        );

        addStep(
                "Step 6: Change font size",
                () -> getEditorPage().changeFontSize_Input("30")
        );

        sleep(1);

        undo(
                "Step 7: Undo change font size",
                1
        );

        undo(
                "Step 8: Undo change font family",
                1
        );

        undo(
                "Step 9: Undo change margin value",
                1
        );

        undo(
                "Step 10: Undo change padding value",
                1
        );

        undo(
                "Step 11: Undo change content color",
                1
        );

        undo(
                "Step 12: Undo drag and drop layout element",
                1
        );

        getEditorPage().verifyUndoButtonDisabled();

        redo(
                "Step 13: Redo drag and drop layout element",
                1
        );

        redo(
                "Step 14: Redo change content color",
                1
        );

        redo(
                "Step 15: Redo change padding value",
                1
        );

        redo(
                "Step 16: Redo change margin value",
                1
        );

        redo(
                "Step 17: Redo change font family",
                1
        );

        redo(
                "Step 18: Redo change font size",
                1
        );

        getEditorPage().verifyRedoButtonDisabled();
    }

    @Feature("Element")
    @Severity(CRITICAL)
    @Story("Layout")
    @Test(description = "UR-006: User undo and redo when set row content")
    public void undoRedoSetRowContent() {
        addStep(
                "Step 0: Init plank page",
                () -> {
                    getPageListingScreen().openPageListingPage();
                    getPageListingScreen().verifyPageListingLoaded();
                    getPageListingScreen().createNewPageFromBlank(PageType.PAGE);
                    getEditorPage().verifyEditorPageLoaded();
                }
        );

        addStep(
                "Step 1: Drag and drop layout element",
                () -> getEditorPage().dragAndDropLayoutElement()
        );

        addStep(
                "Step 2: Drag and drop heading element",
                () -> getEditorPage().dragAndDropHeadingElement()
        );

        System.out.println("Selected section element");
        getEditorPageSandbox().selectElement("Section");
        System.out.println("=====================================");
        System.out.println("Selected row element");
        getEditorPageSandbox().selectElement("Row");
        System.out.println("=====================================");
        System.out.println("Selected column element");
        getEditorPageSandbox().selectElement("Column");
        System.out.println("=====================================");
        sleep(3);
    }


    @Step("{description}")
    public void addHistory(String description, Runnable step) {
        step.run();
        pageEditingTest.savePageSuccessfully();
        canvasHtml = canvasHtml.subList(0, currentStep + 1);
        String source = getEditorPage().getCanvasHtmlProcessed();
        canvasHtml.add(source);
        currentStep++;
        AllureManager.takeScreenshotStep();
    }

    @Step("Restore to history version {index} and verify the source code of canvas")
    public void restoreToHistoryVersion(int index) {
        String expectedSource = getEditorPage().restoreToVersionHistory(index+1);
        sleep(1);
        AllureManager.takeScreenshotStep();
        verifyEquals(expectedSource, canvasHtml.get(currentStep - index));
        String actualSource = getEditorPage().getCanvasHtmlProcessed();
        verifyEquals(actualSource, expectedSource);
    }

    @Flaky
    @Severity(CRITICAL)
    @Feature("Version history")
    @Test(description = "UR-004: User restore a previous version of a page", groups = "Select Don't remind me again")
    public void restorePreviousVersion() {
        try {
            pageEditingTest.saveNewPageFromBlank_selectDontRemindSavePage(PageType.PAGE);

            addHistory(
                    "Step 1: Init template page",
                    () -> {
                        getEditorPage().dragAndDropHeadingElement();
                        getEditorPage().changeContentColor("rgb(239, 45, 201)");
                    }
            );

            addHistory(
                    "Step 2: Drag and drop paragraph element",
                    () -> getEditorPage().dragAndDropParagraphElement()
            );

            addHistory(
                    "Step 3: Open and select page template",
                    () -> {
                        getEditorPage().openAndSelectPageTemplate();
                        waitForPageLoaded();
                    }
            );

            System.out.println("Current step: " + currentStep);

            for (int i = currentStep; i >= 0; i--) {
                restoreToHistoryVersion(i);
            }

        } finally {
            resetDontRemind();
        }
    }
}
