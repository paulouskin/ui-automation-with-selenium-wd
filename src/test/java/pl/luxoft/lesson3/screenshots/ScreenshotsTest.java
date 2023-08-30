package pl.luxoft.lesson3.screenshots;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static core.Constants.ADDRESS;
import static core.Constants.WEB_FORM;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ScreenshotsTest extends BaseExerciseTest {

    @Test
    public void shouldMakeScreenshotForTargetPage() throws IOException {
        driver.get(ADDRESS);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot created on " + screenshot);
        Path destination = Paths.get("screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
        System.out.println("Screenshot moved to " + destination);
        assertThat(destination.toFile().exists(), equalTo(true));
    }

    @Test
    public void shouldTakeWebElementScreenshot() throws IOException {
        driver.get(WEB_FORM);
        WebElement form = driver.findElement(By.tagName("form"));
        File screenshot = form.getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("webelement-screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
        assertThat(destination.toFile().exists(), equalTo(true));
    }
}
