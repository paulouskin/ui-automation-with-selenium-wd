package pl.luxoft.lesson3;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static core.Constants.SHADOW_DOM;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShadowDOMTest extends BaseExerciseTest {

    @Test
    public void shouldGetDataFromShadowDOM() {
        driver.get(SHADOW_DOM);
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText(), containsStringIgnoringCase("Hello Shadow DOM"));
    }
}
