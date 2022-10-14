package pl.luxoft.advanced;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.Test;

public class JavaScriptExecutionExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldScrollDownBy1000PxUsingJavascript() {
        ScreenShotEventListener listener = new ScreenShotEventListener();
        driver = new EventFiringDecorator(listener).decorate(driver);
        driver.get(LONG_PAGE);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int pxToScroll = 1000;
        String script = "window.scrollBy(0, arguments[0])";
        jsExecutor.executeScript(script, pxToScroll);
        sleep();
    }
}
