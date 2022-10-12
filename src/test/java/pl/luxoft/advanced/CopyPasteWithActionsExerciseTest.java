package pl.luxoft.advanced;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CopyPasteWithActionsExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldPerformCopyPasteInWebForm(){
        driver.get(WEB_FORM);
        Actions actions = new Actions(driver);
        WebElement sourceField = driver.findElement(By.id("my-text-id"));
        WebElement targetField = driver.findElement(By.name("my-textarea"));
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        actions.sendKeys(sourceField, "copypaste").keyDown(modifier)
                .sendKeys(sourceField, "a").sendKeys(sourceField, "c")
                .sendKeys(targetField, "v").build().perform();
        sleep();
        Assert.assertEquals(sourceField.getAttribute("value"), targetField.getAttribute("value"));
    }
}
