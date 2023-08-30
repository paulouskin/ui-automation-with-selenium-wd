package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import static core.Constants.ADDRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebDriverMethodsTest extends BaseExerciseTest {


    @Test
    public void shouldCheckBasicWebDriverMethods() {
        driver.get(ADDRESS);
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        System.out.printf("Session Id is '%s'", sessionId.toString());
        assertThat(driver.getTitle(), containsString("Hands-On"));
        assertThat(driver.getCurrentUrl(), is(equalTo(ADDRESS)));
        assertThat(driver.getPageSource(), containsString("<title>"));
    }
}
