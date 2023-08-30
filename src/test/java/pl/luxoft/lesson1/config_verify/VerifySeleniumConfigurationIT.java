package pl.luxoft.lesson1.config_verify;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class VerifySeleniumConfigurationIT {

    protected WebDriver driver;

    @BeforeClass
    public void globalSetUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void shouldGoToGooglePageAndVerifyTitle() {
        driver.get("https://www.google.com");
        assertThat(driver.getTitle().toLowerCase(), containsString("google"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
