package org.pftest.projects.V2.testcases;

import org.pftest.enums.pagefly.PageType;
import org.testng.annotations.DataProvider;

/**
 * Global data provider for whole project
 */
public class DataProviderFactory {
    @DataProvider(name = "pageTypes", parallel = true)
    public Object[][] pageTypes() {
        return new Object[][]{
                {PageType.PAGE},
                {PageType.COLLECTION},
                {PageType.PRODUCT},
                {PageType.BLOG},
                {PageType.HOME},
        };
    }
}
