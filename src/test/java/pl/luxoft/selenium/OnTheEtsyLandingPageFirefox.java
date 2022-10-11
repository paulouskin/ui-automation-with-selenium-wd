package pl.luxoft.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class OnTheEtsyLandingPageFirefox {

    WebDriver driver;

    @BeforeMethod
    public void testSetUp(){
        String geckoDriverBinaryPath = "D:\\dev\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", geckoDriverBinaryPath);
        driver = new FirefoxDriver();
    }

    @Test
    public void shouldProceedWithShoppingAfterPolicyAcceptance() throws InterruptedException {
        //go to the etsy.com landing page - arrange
        driver.get("https://www.etsy.com");
        //accept default privacy policy - act
        String acceptButtonLocator = "//*[@id=\"gdpr-single-choice-overlay\"]/div/div[2]/div[2]/button";
        WebElement acceptButton = driver.findElement(By.xpath(acceptButtonLocator));
        acceptButton.click();
        //verify that Alice The Shopper can proceed with the shopping - assert
        Thread.sleep(2000);
        Assert.assertFalse(acceptButton.isDisplayed());
    }

    @AfterMethod
    public void testCleanUp() {
        driver.quit();
    }
}
