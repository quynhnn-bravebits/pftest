package org.pftest.projects.testcases;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pftest.base.BaseTest;
import org.testng.annotations.Test;


@Epic("Sample Test")
@Feature("Sample Test Feature")
public class SampleTest extends BaseTest {
    @Test
    public void sampleTest() {
        getEditorPage().openHomePage();
    }
}
