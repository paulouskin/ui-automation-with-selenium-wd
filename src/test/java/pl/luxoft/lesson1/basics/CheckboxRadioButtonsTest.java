package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static core.Constants.WEB_FORM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckboxRadioButtonsTest extends BaseExerciseTest {

    @Test
    public void shouldChangeButtonsAttributesWhenSelected() {
        driver.get(WEB_FORM);
        WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
        checkbox2.click();
        assertThat(checkbox2.isSelected(), equalTo(true));

        WebElement radio2 = driver.findElement(By.id("my-radio-2"));
        radio2.click();
        assertThat(radio2.isSelected(), equalTo(true));
    }
}
