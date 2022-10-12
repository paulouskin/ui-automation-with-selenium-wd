package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitingStrategiesExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldWaitForImagesWithImplicitWait() {
        driver.get(LOADING_IMAGES);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = driver.findElement(By.id("landscape"));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilCalculationResultAppears() {
        driver.get(SLOW_CALCULATOR);
        By screenLocator = By.className("screen");
        var screen = driver.findElement(screenLocator);
        driver.findElement(By.xpath("//span[text() = '8']")).click();
        driver.findElement(By.xpath("//span[text() = '+']")).click();
        driver.findElement(By.xpath("//span[text() = '6']")).click();
        driver.findElement(By.xpath("//span[text() = '=']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(screenLocator, "14"));
        Assert.assertEquals(screen.getText(), "14");
        sleep();
    }
}
