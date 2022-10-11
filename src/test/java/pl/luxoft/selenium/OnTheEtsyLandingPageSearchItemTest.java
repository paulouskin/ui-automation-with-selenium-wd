package pl.luxoft.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.luxoft.selenium.pageobjects.EmptySearchResultPagePO;
import pl.luxoft.selenium.pageobjects.EtsyLandingPagePO;
import pl.luxoft.selenium.pageobjects.SearchResultPagePO;

import java.time.Duration;

public class OnTheEtsyLandingPageSearchItemTest {

    WebDriver driver;

    EtsyLandingPagePO landingPage;
    SearchResultPagePO searchResultPage;
    EmptySearchResultPagePO emptySearchResultPage;

    @BeforeMethod
    public void testSetUp() throws InterruptedException {
        String chromeDriverBinaryPath = "D:\\dev\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverBinaryPath);
        driver = new ChromeDriver();
        landingPage = new EtsyLandingPagePO(driver);
        searchResultPage = new SearchResultPagePO(driver);
        emptySearchResultPage = new EmptySearchResultPagePO(driver);
    }

    @Test
    public void shouldContainSearchQueryInSearchResultItemTitle(){
        String searchQuery = "leather bag";
        landingPage.goTo();
        landingPage.acceptDefaultPrivacyPolicy();
        landingPage.searchFor(searchQuery);
        Assert.assertTrue(searchResultPage.isSearchResultsContainsRequiredItemsForQuery(searchQuery));
    }

    @Test
    public void shouldGoToNoResultsFoundPageWhenUsingInvalidQuery(){
        String searchQuery = "!@#2132131241249814219864127498142hrqi3h2981y981281498!*@&^$@!$@&";
        landingPage.goTo();
        landingPage.acceptDefaultPrivacyPolicy();
        landingPage.searchFor(searchQuery);
        Assert.assertTrue(emptySearchResultPage.isVisible());
    }

    @AfterMethod
    public void testCleanUp() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
