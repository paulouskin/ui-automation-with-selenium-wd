package pl.luxoft.lesson2.advanced;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static core.Constants.WEB_FORM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DatePickerTest extends BaseExerciseTest {

    @Test
    public void shouldSelectRequiredDateUsingSelenium() {
        driver.get(WEB_FORM);
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();

        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();

        WebElement monthElement = driver.findElement(By.xpath(
                String.format("//th[contains(.,'%d')]", currentYear)
        ));
        monthElement.click();

        WebElement arrowLeft = driver.findElement(
                RelativeLocator.with(By.tagName("th")).toRightOf(monthElement)
        );
        arrowLeft.click();

        WebElement monthPastYear = driver.findElement(
                RelativeLocator.with(By.cssSelector("span[class$=focused]")).below(arrowLeft)
        );
        monthPastYear.click();

        WebElement dayElement = driver.findElement(
                By.xpath(String.format("//td[@class='day' and contains(text(),'%d')]", currentDay))
        );
        dayElement.click();

        String oneYearBack = datePicker.getAttribute("value");
        System.out.println("Final date in date picker: " + oneYearBack);

        LocalDate previousYear = today.minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        System.out.println("Expected date: " + expectedDate);

        assertThat(oneYearBack, equalTo(expectedDate));
    }
}
