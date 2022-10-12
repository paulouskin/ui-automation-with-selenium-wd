package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploadExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldUploadTemporaryFileUsingSelenium() throws IOException {
        driver.get(WEB_FORM);
        WebElement inputFile = driver.findElement(By.name("my-file"));
        Path tempFile = Files.createTempFile("tempfile",".tmp");
        String fileName = tempFile.toAbsolutePath().toString();
        logger.info(String.format("Using temp file '%s' for file uploading", fileName));
        inputFile.sendKeys(fileName);
        driver.findElement(By.tagName("form")).submit();
        sleep();
        Assert.assertNotEquals(driver.getCurrentUrl(), WEB_FORM);
    }
}
