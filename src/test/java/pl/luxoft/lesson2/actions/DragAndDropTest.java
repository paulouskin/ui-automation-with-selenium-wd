package pl.luxoft.lesson2.actions;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static core.Constants.DRAG_AND_DROP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DragAndDropTest extends BaseExerciseTest {

    @Test
    public void shouldPerformDragAndDropWithActions() {
        driver.get(DRAG_AND_DROP);
        Actions actions = new Actions(driver);
        WebElement draggable = driver.findElement(By.id("draggable"));
        int offset = 100;
        Point initLocation = draggable.getLocation();
        actions.dragAndDropBy(draggable, offset, 0)
                .dragAndDropBy(draggable, 0, offset)
                .dragAndDropBy(draggable, -offset, 0)
                .dragAndDropBy(draggable, 0, -offset).build().perform();
        assertThat(initLocation, equalTo(draggable.getLocation()));
        WebElement target = driver.findElement(By.id("target"));
        actions.dragAndDrop(draggable, target).build().perform();
        assertThat(target.getLocation(), equalTo(draggable.getLocation()));
    }
}
