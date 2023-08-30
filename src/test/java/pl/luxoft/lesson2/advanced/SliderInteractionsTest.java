package pl.luxoft.lesson2.advanced;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static core.Constants.WEB_FORM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class SliderInteractionsTest extends BaseExerciseTest {

    @Test
    public void shouldChangeSliderValueAttribute() {
        driver.get(WEB_FORM);
        WebElement slider = driver.findElement(By.name("my-range"));
        String initValue = slider.getAttribute("value");
        System.out.printf("Initial slider value: %s \n", initValue);
        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        String endValue = slider.getAttribute("value");
        System.out.printf("Final slider value: %s", endValue);
        assertThat(initValue, not(equalTo(endValue)));
    }
}
