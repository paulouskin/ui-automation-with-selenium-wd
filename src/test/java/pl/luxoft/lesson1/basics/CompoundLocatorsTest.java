package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.Test;

import java.util.List;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CompoundLocatorsTest extends BaseExerciseTest {

    @Test
    public void shouldFindElementByIdOrName() {
        driver.get(WEB_FORM);
        WebElement fileElement = driver.findElement(new ByIdOrName("my-file"));
        assertThat(fileElement.getAttribute("id"), is(emptyString()));
        assertThat(fileElement.getAttribute("name"), is(not(emptyString())));
    }

    @Test
    public void shouldFindElementByChained() {
        driver.get(WEB_FORM);
        List<WebElement> rowsInForm = driver.findElements(
           new ByChained(By.tagName("form"), By.className("row"))
        );
        assertThat(rowsInForm.size(), equalTo(1));
    }

    @Test
    public void shouldFindElementsWithByAll() {
        driver.get(WEB_FORM);
        List<WebElement> rowsInForm = driver.findElements(
                new ByAll(By.tagName("form"), By.className("row"))
        );
        assertThat(rowsInForm.size(), equalTo(5));
    }
}
