package pl.luxoft.lesson3.javascript;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static core.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class SynchronousJSExecutionTest extends BaseExerciseTest {

    @Test
    public void shouldScrollPageDownWithJavascript() throws InterruptedException {
        driver.get(LONG_PAGE);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int pxToScroll = 1000;
        String script = "window.scrollBy(0, arguments[0])";
        jsExecutor.executeScript(script, pxToScroll);
        //Thread.sleep(3000);
    }

    @Test
    public void shouldScrollToElementWithScrollIntoViewJSFunction() throws InterruptedException {
        driver.get(LONG_PAGE);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement lastChild = driver.findElement(By.cssSelector("p:last-child"));
        String script = "arguments[0].scrollIntoView()";
        jsExecutor.executeScript(script, lastChild);
        //Thread.sleep(3000);
    }

    @Test
    public void shouldInfinitelyScrollPageToSimulateDynamicContentLoad() {
        driver.get(INFINITE_SCROLL);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0)
        );
        int initParagraphsCount = paragraphs.size();
        //int step = paragraphs.size();
        //while(initParagraphsCount <= step * 5){
        WebElement lastParagraph = driver.findElement(
                By.xpath(String.format("//p[%d]", initParagraphsCount))
        );
        String script = "arguments[0].scrollIntoView();";
        jsExecutor.executeScript(script, lastParagraph);
        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsCount)
        );
            //initParagraphsCount += step;
        //}

    }

    @Test
    public void shouldSelectColorInColorpickerWithJS() {
        driver.get(WEB_FORM);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + initColor);
        Color green = new Color(0, 255, 0, 1);
        String script = String.format(
                "arguments[0].setAttribute('value', '%s')", green.asHex()
        );
        jsExecutor.executeScript(script, colorPicker);
        String finalColor = colorPicker.getAttribute("value");
        System.out.println("Final color is " + finalColor);
        assertThat(finalColor, not(equalTo(initColor)));
        assertThat(Color.fromString(finalColor), equalTo(green));
    }
}
