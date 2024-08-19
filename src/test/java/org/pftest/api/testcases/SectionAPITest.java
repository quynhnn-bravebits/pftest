package org.pftest.api.testcases;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import org.pftest.api.manager.SectionAPIManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SectionAPITest extends BaseAPITest{

    @Parameters({"section/usage/:id"})
    @Test(description = "Get pages using section by id", groups = {"SECTION"})
    public void getPagesUsingSectionByIdTest() throws JsonProcessingException {
        String id_data = "70d01eba-1e05-4819-9688-f4639974866f";
        SectionAPIManager.getPagesUsingSectionById(baseUrl, id_data);
        String id_empty_data = "a11cd41c-a610-4ce4-bd51-0d8837fa8b3c";
        SectionAPIManager.getPagesUsingSectionById(baseUrl, id_empty_data);
    }
}
