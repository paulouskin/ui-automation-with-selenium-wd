package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationStrategiesTest extends BaseExerciseTest {

    @Test
    public void shouldFindElementByTagName() throws InterruptedException {
        driver.get(WEB_FORM);
        Thread.sleep(3000);
        WebElement textArea = driver.findElement(By.tagName("textarea"));
        assertThat(textArea.getAttribute("spellcheck"),equalTo("true"));
    }

    @Test
    public void shouldFindElementByHtmlAttributes() {
        driver.get(WEB_FORM);
        //By name
        WebElement textByName = driver.findElement(By.name("my-text"));
        assertThat(textByName.isEnabled(), equalTo(true));
        //By id
        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getAttribute("type"), equalTo("text"));
        assertThat(textById.getDomAttribute("type"), equalTo("text"));
        assertThat(textById.getDomProperty("type"), equalTo("text"));
        assertThat(textById.getAttribute("myprop"), equalTo("myvalue"));
        assertThat(textById.getDomAttribute("myprop"), equalTo("myvalue"));
        assertThat(textById.getDomProperty("myprop"), equalTo(null));
        //By class
        List<WebElement> byClassName = driver.findElements(By.className("form-control"));
        assertThat(byClassName.size(), greaterThan(0));
        assertThat(byClassName.get(0).getAttribute("name"), equalTo("my-text"));
    }

    @Test
    public void shouldFindElementByLinkText() {
        driver.get(WEB_FORM);
        WebElement linkByText = driver.findElement(By.linkText("Return to index"));
        assertThat(linkByText.getTagName(), equalTo("a"));
        assertThat(linkByText.getCssValue("text-align"), equalTo("start"));

        WebElement linkByPartialText = driver.findElement(By.partialLinkText("index"));
        assertThat(linkByPartialText.getLocation(), equalTo(linkByText.getLocation()));
    }

    @Test
    public void shouldFindElementByCssSelector() {
        driver.get(WEB_FORM);
        WebElement hidden = driver.findElement(By.cssSelector("input[type=hidden]"));
        assertThat(hidden.isDisplayed(), equalTo(false));

        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox1.getAttribute("id"), is(equalTo("my-check-1")));
        assertThat(checkbox1.isSelected(), equalTo(true));

        WebElement checkbox2 = driver.findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox2.getAttribute("id"), equalTo("my-check-2"));
        assertThat(checkbox2.isSelected(), equalTo(false));
    }

    @Test
    public void shouldFindElementByXPath() {
        driver.get(WEB_FORM);
        WebElement radio1 = driver.findElement(By.xpath("//*[@type='radio' and @checked]"));
        assertThat(radio1.getAttribute("id"), equalTo("my-radio-1"));
        assertThat(radio1.isSelected(), equalTo(true));

        WebElement radio2 = driver.findElement(By.xpath("//*[@type='radio' and not(@checked)]"));
        assertThat(radio2.getAttribute("id"), equalTo("my-radio-2"));
        assertThat(radio2.isSelected(), equalTo(false));

    }

}
