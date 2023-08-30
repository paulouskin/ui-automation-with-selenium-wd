package pl.luxoft.lesson2.advanced;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class FileUploadingTest extends BaseExerciseTest {

    @Test
    public void shouldUploadFile() throws IOException, InterruptedException {
        driver.get(WEB_FORM);
        WebElement inputFile = driver.findElement(By.name("my-file"));
        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        String fileName = tempFile.toAbsolutePath().toString();
        System.out.printf("Using temp file '%s' for file uploading.", fileName);
        inputFile.sendKeys(fileName);
        driver.findElement(By.tagName("form")).submit();
        Thread.sleep(5000);
        assertThat(driver.getCurrentUrl(), not(equalTo(WEB_FORM)));
    }
}
