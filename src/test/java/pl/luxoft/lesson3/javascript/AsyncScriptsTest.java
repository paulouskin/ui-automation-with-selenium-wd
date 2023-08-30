package pl.luxoft.lesson3.javascript;

import core.BaseExerciseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.time.Duration;

import static core.Constants.ADDRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class AsyncScriptsTest extends BaseExerciseTest {

    @Test
    public void shouldExecuteScriptAsynchronouslyWithCallbackFunction() {
        driver.get(ADDRESS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Duration pause = Duration.ofSeconds(3);
        String script = String.format(
                "const callback = arguments[arguments.length - 1];" +
                        "window.setTimeout(callback, %s);", pause.toMillis());
        long initMillis = System.currentTimeMillis();
        js.executeAsyncScript(script);
        Duration elapsed = Duration.ofMillis(System.currentTimeMillis() - initMillis);
        System.out.println("Script execution time in millis: " + elapsed.toMillis());
        assertThat(elapsed, greaterThanOrEqualTo(pause));
    }
}
