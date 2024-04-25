package org.pftest.driver;

import org.openqa.selenium.WebDriver;

public class TargetFactory {
    public WebDriver createInstance(String browser) {
        WebDriver webDriver;
        try {
            webDriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Browser name is invalid or not supported");
        }
        return webDriver;
    }
}
