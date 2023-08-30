package pl.luxoft.lesson2.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pl.luxoft.advanced.BaseExerciseTest;



public class ContextAndDoubleClickTest extends BaseExerciseTest {

    @Test
    public void shouldPerformContextAndDoubleClickWithActions() {
        driver.get(DROPDOWN_MENU);
        Actions actions = new Actions(driver);

        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        Action contextClick = actions.contextClick(dropdown2).build();
        contextClick.perform();
        WebElement contextMenu2 = driver.findElement(By.id("context-menu-2"));
        assertThat(contextMenu2.isDisplayed(), equalTo(true));

        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        Action doubleClick = actions.doubleClick(dropdown3).build();
        doubleClick.perform();
        WebElement contextMenu3 = driver.findElement(By.id("context-menu-3"));
        assertThat(contextMenu3.isDisplayed(), equalTo(true));


    }
}
