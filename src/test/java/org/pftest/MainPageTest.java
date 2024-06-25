package org.pftest;

import org.pftest.base.BaseTest;
import org.pftest.keywords.WebUI;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest extends BaseTest {
    @Test
    public void Test1() {
        WebUI.openWebsite("https://trello.com/b/WAPQKMQV/board");
        WebUI.waitForPageLoaded();
        WebUI.sleep(3);
        By from = By.xpath("//*[@data-list-id][1]//*[@data-testid='list-card'][2]");
        By to = By.xpath("//*[@data-list-id][2]//*[@data-testid='list-card'][2]");
        WebUI.dragAndDrop(from, to);
        WebUI.sleep(2);
        WebUI.dragAndDrop(to, from);
        WebUI.sleep(5);
    }
}
