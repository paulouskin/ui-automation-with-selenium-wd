package pl.luxoft.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.luxoft.selenium.pageobjects.EtsyLandingPagePO;

public class OnTheEtsyLandingPageChromeTest {

    WebDriver driver;

    EtsyLandingPagePO landingPage;

    @BeforeMethod
    public void testSetUp(){
        String chromeDriverBinaryPath = "D:\\dev\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverBinaryPath);
        driver = new ChromeDriver();
        landingPage = new EtsyLandingPagePO(driver);
    }

    @Test
    public void shouldProceedWithShoppingAfterPolicyAcceptance() {
        landingPage.goTo();
        landingPage.acceptDefaultPrivacyPolicy();
        Assert.assertTrue(landingPage.isUserCanProceedWithTheShopping());
    }

    @Test
    public void privacyPolicyUpdateOptionsAreAvailable() {
        landingPage.goTo();
        landingPage.showPrivacyPolicyUpdateOptions();
        Assert.assertTrue(landingPage.arePrivacyPolicyUpdateOptionsAvailable());
    }

    @AfterMethod
    public void testCleanUp() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
