package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DropdownListExerciseTest extends BaseExerciseTest{

    @Test
    public void shouldSelectOptionByVisibleText(){
        driver.get(WEB_FORM);
        WebElement selectElement = driver.findElement(By.name("my-select"));
        Select select = new Select(selectElement);
        String option = "Two";
        select.selectByVisibleText(option);
        sleep();
        Assert.assertEquals(select.getFirstSelectedOption().getText(), option);
    }

    @Test
    public void datalistShouldHaveOptionSpecified() {
        String text = "Los Angeles";
        driver.get(WEB_FORM);
        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.click();
        String optionsLocator = "//datalist[@id='my-options']/option";
        List<WebElement> options = driver.findElements(By.xpath(optionsLocator));
        options.stream().map(opt -> opt.getAttribute("value")).forEach(System.out::println);
        boolean isOptionExist = options.stream().anyMatch(opt -> opt.getAttribute("value").equalsIgnoreCase(text));
        sleep();
        Assert.assertTrue(isOptionExist);
    }
}
