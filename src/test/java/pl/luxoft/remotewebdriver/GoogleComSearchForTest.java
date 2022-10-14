package pl.luxoft.remotewebdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GoogleComSearchForTest {

    WebDriverWait wait;

    @BeforeSuite
    public void globalSetup() {
        //WebDriverManager.chromedriver().setup();
    }

    @Test
    public void searchResultPageTitleShouldContainSelenium() throws MalformedURLException {
        googlingFor("selenium");
    }

    @Test
    public void searchResultPageTitleShouldContainTestNG() throws MalformedURLException {
        googlingFor("testng");
    }
    private void googlingFor(String searchQuery) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("firefox");
        // caps.setBrowserName("chrome");
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.129:4444"), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.google.com");
        var acceptPolicyButton = driver.findElement(By.id("L2AGLb"));
        acceptPolicyButton.click();
        var searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchField.clear();
        searchField.sendKeys(searchQuery + Keys.ENTER);
        wait.until(pageTitleStartsWith(searchQuery));
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
        Assert.assertTrue(pageTitle.toLowerCase().startsWith(searchQuery));
        driver.quit();
    }

    private static ExpectedCondition <Boolean> pageTitleStartsWith(String searchQuery) {
        return driver1 -> driver1.getTitle().toLowerCase().startsWith(searchQuery);
    }

    @AfterMethod
    public void cleanUp() {

    }
}
