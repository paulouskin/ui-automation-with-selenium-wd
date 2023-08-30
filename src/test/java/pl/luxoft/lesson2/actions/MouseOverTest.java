package pl.luxoft.lesson2.actions;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static core.Constants.MOUSE_OVER;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class MouseOverTest extends BaseExerciseTest {

    @Test
    public void shouldPerformMouseOverUsingActions() {
        driver.get(MOUSE_OVER);
        Actions actions = new Actions(driver);
        List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");
        for (String imageName :
                imageList) {
            String xpath = String.format("//img[@src='img/%s.png']", imageName);
            WebElement image = driver.findElement(By.xpath(xpath));
            actions.moveToElement(image).build().perform();
            WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("div")).near(image));
            assertThat(caption.getText(), containsStringIgnoringCase(imageName));
        }
    }
}
