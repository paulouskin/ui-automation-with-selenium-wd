package pl.luxoft.advanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseExerciseTest {

    String ADDRESS = "https://bonigarcia.dev/selenium-webdriver-java/";
    String WEB_FORM = ADDRESS + "web-form.html";
    String DROPDOWN_MENU = ADDRESS + "dropdown-menu.html";
    String DRAW_IN_CANVAS = ADDRESS + "draw-in-canvas.html";

    protected WebDriver driver;

    protected void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite
    public void globalSetUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
}
