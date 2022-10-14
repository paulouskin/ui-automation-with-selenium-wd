package pl.luxoft.remotewebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.luxoft.selenium.config.DriverBase;

import java.net.MalformedURLException;
import java.time.Duration;

public class GoogleComSearchForIT extends DriverBase {

    /*This test can be run on Selenium grid
       Grid need to exist - please check test/resources/grid-config folder
       Verify hub URL and node ports
     1) Download Selenium Server jar
     2) Run instance as hub
     3) Run other instances as nodes which will point to hub
     4) Specify node capabilities
     5) Configure RemoteWebDriver in your tests
     6) Enjoy parallel test run*/

    WebDriverWait wait;

    @Test
    public void searchResultPageTitleShouldContainSelenium() throws MalformedURLException {
        googlingFor("selenium");
    }

    @Test
    public void searchResultPageTitleShouldContainTestNG() throws MalformedURLException {
        googlingFor("testng");
    }
    private void googlingFor(String searchQuery) throws MalformedURLException {
        //If you want to run this test without maven tweaks
        /*DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("firefox");
        caps.setBrowserName("chrome");
        WebDriver driver = new RemoteWebDriver(new URL(<your-selenium-grid-hub-url>), caps);*/
        WebDriver driver = DriverBase.getDriver();
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
    }

    private static ExpectedCondition <Boolean> pageTitleStartsWith(String searchQuery) {
        return driver1 -> driver1.getTitle().toLowerCase().startsWith(searchQuery);
    }
}
