package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DrawInCanvasExerciseTest extends BaseExerciseTest{


    @Test
    public void shouldDrawACircleInCanvas(){
        driver.get(DRAW_IN_CANVAS);
        Actions actions = new Actions(driver);
        var canvas = driver.findElement(By.tagName("canvas"));
        actions.moveToElement(canvas).clickAndHold();
        drawCircle(actions);
        actions.release(canvas).build().perform();
        sleep();
    }

    private void drawCircle(Actions actions) {
        int numPoints = 30;
        int radius = 10;
        for (int i = 0; i <= numPoints; i++) {
            double angle = Math.toRadians(360 * i / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;
            actions.moveByOffset((int)x, (int)y);
        }
    }

}
