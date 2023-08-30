package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static core.Constants.WEB_FORM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;

public class KeyboardActionsTest extends BaseExerciseTest {

    @Test
    public void shouldInteractWithKeyboard() {
        driver.get(WEB_FORM);
        WebElement inputText = driver.findElement(By.name("my-text"));
        String textValue = "Test Automation with Selenium";
        inputText.sendKeys(textValue);
        assertThat(inputText.getAttribute("value"), equalTo(textValue));
        inputText.clear();
        assertThat(inputText.getAttribute("value"), is(emptyString()));
    }
}
