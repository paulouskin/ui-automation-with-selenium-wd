package pl.luxoft.lesson2.actions;

import core.BaseExerciseTest;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CopyAndPasteTest extends BaseExerciseTest {

    @Test
    public void shouldPerformCopyAndPasteTextInForm() {
        driver.get(WEB_FORM);
        Actions actions = new Actions(driver);
        WebElement sourceField = driver.findElement(By.name("my-text"));
        WebElement targetField = driver.findElement(By.name("my-textarea"));
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        actions.sendKeys(sourceField, "hello selenium").keyDown(modifier)
                .sendKeys(sourceField, "a").sendKeys(sourceField, "c")
                .sendKeys(targetField, "v").build().perform();
        assertThat(sourceField.getAttribute("value"), equalTo(targetField.getAttribute("value")));
    }
}
