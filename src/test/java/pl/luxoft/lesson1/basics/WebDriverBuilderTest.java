package pl.luxoft.lesson1.basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WebDriverBuilderTest {

    WebDriver webDriver;

    @BeforeClass
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        webDriver = RemoteWebDriver.builder().oneOf(new FirefoxOptions())
                .addAlternative(new ChromeOptions()).build();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void shouldRunFirefoxFirstAndChromeAsAlternative() {
        webDriver.get("http://www.google.com");
        assertThat(webDriver.getTitle().toLowerCase(), containsString("google"));
    }

}
