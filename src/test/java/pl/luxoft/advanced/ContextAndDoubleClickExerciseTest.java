package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextAndDoubleClickExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldPerformContextAndDoubleClickWithActions() {
        driver.get(DROPDOWN_MENU);
        Actions actions = new Actions(driver);
        var context_click_dropdown = driver.findElement(By.id("my-dropdown-2"));
        Action contextClick = actions.contextClick(context_click_dropdown).build();
        contextClick.perform();
        var context_menu = driver.findElement(By.id("context-menu-2"));
        Assert.assertTrue(context_menu.isDisplayed());

        var doubleClickDropdown = driver.findElement(By.id("my-dropdown-3"));
        Action doubleClick = actions.doubleClick(doubleClickDropdown).build();
        doubleClick.perform();
        var doubleClickMenu = driver.findElement(By.id("context-menu-3"));
        Assert.assertTrue(doubleClickMenu.isDisplayed());
        sleep();
    }

}
