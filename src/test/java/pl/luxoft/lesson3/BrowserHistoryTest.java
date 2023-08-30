package pl.luxoft.lesson3;

import core.BaseExerciseTest;
import org.testng.annotations.Test;

import static core.Constants.ADDRESS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BrowserHistoryTest extends BaseExerciseTest {

    @Test
    public void shouldNavigateBetweenPagesWithNavigateMethods() {
        String firstPage = ADDRESS + "navigation1.html";
        String secondPage = ADDRESS + "navigation2.html";
        String thirdPage = ADDRESS + "navigation3.html";
        driver.get(firstPage);

        driver.navigate().to(secondPage);
        driver.navigate().to(thirdPage);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        assertThat(driver.getCurrentUrl(), equalTo(thirdPage));
    }

}
