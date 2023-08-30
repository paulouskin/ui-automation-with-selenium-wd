package pl.luxoft.lesson2.waits;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static core.Constants.LOADING_IMAGES;
import static core.Constants.SLOW_CALCULATOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class WaitingStrategiesTest extends BaseExerciseTest {

    @Test
    public void shouldWaitForImagesWithImplicitWait() {
        driver.get(LOADING_IMAGES);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getAttribute("src"), containsStringIgnoringCase("landscape"));
    }

    @Test
    public void shouldExplicitlyWaitUntilImageDisplayed() {
        driver.get(LOADING_IMAGES);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement landscape = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("landscape"))
        );
        assertThat(landscape.getAttribute("src"), containsStringIgnoringCase("landscape"));
    }

    @Test
    public void shouldWaitUntilCalculationResultAppears() {
        driver.get(SLOW_CALCULATOR);
        By screenLocator = By.className("screen");
        WebElement screen = driver.findElement(screenLocator);
        driver.findElement(By.xpath("//span[text() = '2']")).click();
        driver.findElement(By.xpath("//span[text() = '+']")).click();
        driver.findElement(By.xpath("//span[text() = '5']")).click();
        driver.findElement(By.xpath("//span[text() = '=']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(screenLocator, "7"));
        assertThat(screen.getText(), equalTo("7"));
    }

    @Test
    public void shouldWaitForImagesWithFluentWait() {
        driver.get(LOADING_IMAGES);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement landscape = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("landscape"))
        );
        assertThat(landscape.getAttribute("src"), containsStringIgnoringCase("landscape"));
    }
}
