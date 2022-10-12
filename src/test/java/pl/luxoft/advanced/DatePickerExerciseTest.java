package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldPickRequiredDateUsingSelenium() {
        driver.get(WEB_FORM);
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();

        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();
        var monthElement = driver.findElement(
                By.xpath(String.format("//th[contains(.,'%d')]", currentYear))
        );
        monthElement.click();

        var arrowLeft = driver.findElement(
                RelativeLocator.with(By.tagName("th")).toRightOf(monthElement)
        );
        arrowLeft.click();

        var monthPastYear = driver.findElement(
                By.cssSelector("span.month.focused")
        );
        monthPastYear.click();

        var dayElement = driver.findElement(
                By.xpath(String.format("//td[@class='day' and contains(text(),'%d')]", currentDay))
        );
        dayElement.click();

        String oneYearBack = datePicker.getAttribute("value");
        logger.info("Final date in date picker: " + oneYearBack );

        LocalDate previousYear = today.minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        logger.info("Expected date: " + expectedDate);
        Assert.assertEquals(oneYearBack, expectedDate);
        sleep();
    }
}
