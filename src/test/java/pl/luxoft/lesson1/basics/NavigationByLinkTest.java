package pl.luxoft.lesson1.basics;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static core.Constants.ADDRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class NavigationByLinkTest extends BaseExerciseTest {

    @Test
    public void shouldNavigateByLinksToOtherPages() {
        driver.get(ADDRESS);
        driver.findElement(By.xpath("//a[.='Navigation']")).click();
        driver.findElement(By.xpath("//a[.='Next']")).click();
        driver.findElement(By.xpath("//a[.='3']")).click();
        driver.findElement(By.xpath("//a[.='2']")).click();
        driver.findElement(By.xpath("//a[.='Previous']")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(bodyText, containsString("Lorem ipsum"));
    }
}
