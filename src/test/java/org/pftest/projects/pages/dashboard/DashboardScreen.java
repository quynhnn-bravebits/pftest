package org.pftest.projects.pages.dashboard;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.pftest.constants.PagesConstants;
import org.pftest.constants.UrlConstants;
import org.pftest.projects.pages.CommonPage;

import static org.pftest.keywords.WebUI.*;

// page_url = https://admin.shopify.com/store/quynhquynhiee/apps/wip-pagefly
public class DashboardScreen extends CommonPage {
    private By recentPagesCard = By.id("dashboard--recent-pages");
    private By topBarActivatorButton = By.id("top-bar--sticky-button--activator");

    private By searchModal = By.id("pf-search-modal");
    private By searchModalInput = new ByChained(searchModal, By.id("pf-search-modal--search"));
    private By searchModalResult = new ByChained(searchModal, By.id("pf-search-modal--results"));

    public void openDashboardPage() {
        openWebsite(UrlConstants.PF_DASHBOARD_URL);
        switchToPageFlyFrame();
    }

    @Step("Wait for the dashboard page to be loaded")
    public void verifyDashboardLoaded() {
        verifyPageTitle(PagesConstants.DASHBOARD_PAGE_TITLE);
        waitForElementVisible(recentPagesCard);
        By dashboardCards = By.xpath("//*[@id=\"AppFrameMain\"]/div/div/div[1]/div[2]/div/div[contains(@class, 'Polaris-Box')]");
        verifyTrue(!getWebElements(dashboardCards).isEmpty());
        waitForElementVisible(topBarActivatorButton);
    }

    @Step("Open recent page")
    public String openRecentPage() {
        String prefixId = "dashboard--recent-pages--pf-page--";
        By recentPageXpath = new ByChained(recentPagesCard, By.xpath("//*[starts-with(@id, '" + prefixId + "')]"));
        moveToElement(recentPageXpath);
        String elementId = getAttributeElement(recentPageXpath, "id");
        String pageId = elementId.replace(prefixId, "");
        clickElement(new ByChained(recentPagesCard, By.xpath("//button[@role='edit-page']")));
        return pageId;
    }

    @Step("Open recent page by index {0}")
    public String openRecentPageByIndex(int index) {
        String prefixId = "dashboard--recent-pages--pf-page--";
        By recentPageXpath = new ByChained(recentPagesCard, By.xpath("//*[starts-with(@id, '" + prefixId + "')]" + "[" + index + "]"));
        String elementId = getAttributeElement(recentPageXpath, "id");
        String pageId = elementId.replace(prefixId, "");
        clickElement(new ByChained(recentPagesCard, By.xpath("//button[@role='edit-page']")));
        return pageId;
    }

    @Step("Open recent page by title {0}")
    public String openRecentPageByTitle(String title) {
        By recentPageXpath = new ByChained(recentPagesCard, By.xpath("//*[contains(text(), '" + title + "')]"));
        String elementId = getAttributeElement(recentPageXpath, "id");
        String pageId = elementId.replace("dashboard--recent-pages--pf-page--", "");
        clickElement(new ByChained(recentPagesCard, By.xpath("//button[@role='edit-page']")));
        return pageId;
    }

    @Step("Open search page modal")
    public void openSearchPageModal() {
        waitForElementClickable(topBarActivatorButton);
        sleep(3); // Wait for button move to top of crisp chat
        clickElement(topBarActivatorButton);
        sleep(1);
        By searchPageButton = By.id("top-bar--sticky-button--search");
        clickElement(searchPageButton);
    }

    @Step("Verify search modal is visible")
    public void verifySearchModalVisible() {
        waitForElementVisible(searchModal);
        waitForElementVisible(searchModalInput);
        waitForElementVisible(searchModalResult);
    }

    @Step("Open search result page")
    public void openSearchResultPage() {
        By pageResult = new ByChained(searchModalResult, By.xpath("//*[@id=\"pf-search-modal--results--pf-page\"]//div[@role='result-item']"));
        clickElement(pageResult);
    }

    @Step("Search page in dashboard")
    public void searchPageInDashboard() {
        openSearchPageModal();
        verifySearchModalVisible();
        setText(searchModalInput, "Test");
        By pageResults = new ByChained(searchModalResult, By.id("pf-search-modal--results--pf-page"));
        waitForElementVisible(pageResults, 15);
    }
}
