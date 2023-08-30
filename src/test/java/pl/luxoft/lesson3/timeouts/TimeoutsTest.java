package pl.luxoft.lesson3.timeouts;

import core.BaseExerciseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.time.Duration;

import static core.Constants.ADDRESS;

public class TimeoutsTest extends BaseExerciseTest {

    @Test(expectedExceptions = TimeoutException.class)
    public void shouldThrowTimeoutExceptionForSmallPageLoadWait() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1));
        driver.get(ADDRESS);
    }

    @Test(expectedExceptions = ScriptTimeoutException.class)
    public void shouldThrowScriptTimeoutExceptionForShortScriptTimeout() {
        driver.get(ADDRESS);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(2));
        long waitInMillis = Duration.ofSeconds(4).toMillis();
        String script = String.format("const callback = arguments[arguments.length - 1];" +
                "window.setTimeout(callback, %s)", waitInMillis);
        jsExecutor.executeAsyncScript(script);
    }
}
