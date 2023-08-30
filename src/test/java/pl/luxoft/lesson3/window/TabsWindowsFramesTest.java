package pl.luxoft.lesson3.window;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static core.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TabsWindowsFramesTest extends BaseExerciseTest {

    @Test
    public void shouldOpenNewTabAndCheckWindowHandlesCount() {
        driver.get(ADDRESS);
        String initHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(WEB_FORM);
        assertThat(driver.getWindowHandles().size(), equalTo(2));

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles().size(), equalTo(1));

    }

    @Test
    public void shouldOpenNewWindowAndCheckWindowHandlesCount() {
        driver.get(ADDRESS);
        String initHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(SLOW_CALCULATOR);
        assertThat(driver.getWindowHandles().size(), equalTo(2));

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles().size(), equalTo(1));

    }

    @Test
    public void shouldGetNumberOfParagraphsOnIFramePage() {
        driver.get(IFRAMES);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt("my-iframe")
        );
        By pName = By.tagName("p");
        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(pName, 0)
        );
        List<WebElement> paragraphs = driver.findElements(pName);
        assertThat(paragraphs.size(), equalTo(20));
    }

    @Test
    public void shouldGetNumberOfParagraphsOnIFramePageSecondOption() {
        driver.get(FRAMES);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String frameName = "frame-body";
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name(frameName))
        );
        driver.switchTo().frame(frameName);
        By pName = By.tagName("p");
        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(pName, 0)
        );
        List<WebElement> paragraphs = driver.findElements(pName);
        assertThat(paragraphs.size(), equalTo(20));
    }
}
