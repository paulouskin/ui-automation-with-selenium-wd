package pl.luxoft.lesson3.window;

import core.BaseExerciseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static core.Constants.ADDRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class WindowSizePositionTest extends BaseExerciseTest {

    @Test
    public void shouldChangeSizeAndPositionAfterWindowMaximizing() {
        driver.get(ADDRESS);
        WebDriver.Window window = driver.manage().window();
        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        System.out.printf("Initial window: position %s -- size %s \n", initialPosition, initialSize);
        window.maximize();
        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        System.out.printf("Maximized window: position %s -- size %s", maximizedPosition, maximizedSize);
        assertThat(initialPosition, not(equalTo(maximizedPosition)));
        assertThat(initialSize, not(equalTo(maximizedSize)));


    }
}
