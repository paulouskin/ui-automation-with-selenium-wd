package pl.luxoft.lesson3;

import core.BaseExerciseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static core.Constants.DIALOG_BOXES;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DialogBoxesTest extends BaseExerciseTest {

    @Test
    public void shouldAcceptAlertPopUp() {
        driver.get(DIALOG_BOXES);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("my-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText(), equalTo("Hello world!"));
        alert.accept();
    }

    @Test
    public void shouldHandleConfirmationPopUp() {
        driver.get(DIALOG_BOXES);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("my-confirm")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirm = driver.switchTo().alert();
        assertThat(confirm.getText(), equalTo("Is this correct?"));
        confirm.dismiss();
    }

    @Test
    public void shouldHandlePromptPopUp() {
        driver.get(DIALOG_BOXES);
        String phrase = "This is Selenium!";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("my-prompt")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys(phrase);
        prompt.accept();
        String promptText = driver.findElement(By.id("prompt-text")).getText();
        assertThat(promptText,containsStringIgnoringCase(phrase));

    }
}
