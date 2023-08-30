package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RelativeLocatorsTest extends BaseExerciseTest {

    @Test
    public void shouldFindElementUsingRelativeLocators() {
        driver.get(WEB_FORM);
        WebElement link = driver.findElement(By.linkText("Return to index"));
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement readOnly = driver.findElement(relativeBy.above(link));
        assertThat(readOnly.getAttribute("name"), equalTo("my-readonly"));
    }
}
