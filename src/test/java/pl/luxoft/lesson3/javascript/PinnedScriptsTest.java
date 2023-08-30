package pl.luxoft.lesson3.javascript;

import core.BaseExerciseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Set;

import static core.Constants.ADDRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PinnedScriptsTest extends BaseExerciseTest {

    @Test
    public void shouldPinAndExecutePredefinedJSScripts() {
        driver.get(ADDRESS);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ScriptKey linkKey = jsExecutor.pin("return document.getElementsByTagName('a')[2]");
        ScriptKey firstArgKey = jsExecutor.pin("return arguments[0]");
        Set<ScriptKey> pinnedScripts = jsExecutor.getPinnedScripts();
        assertThat(pinnedScripts, hasSize(2));

        WebElement formLink = (WebElement) jsExecutor.executeScript(linkKey);
        formLink.click();
        assertThat(driver.getCurrentUrl(), not(equalTo(ADDRESS)));

        String msg = "Hello Selenium";
        String executeScript = (String) jsExecutor.executeScript(firstArgKey, msg);
        assertThat(executeScript, equalTo(msg));

        jsExecutor.unpin(linkKey);
        assertThat(jsExecutor.getPinnedScripts(), hasSize(1));

    }
}
