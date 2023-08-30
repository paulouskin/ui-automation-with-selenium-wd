package pl.luxoft.lesson3;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static core.Constants.WEB_FORM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DropdownListTest extends BaseExerciseTest {

    @Test
    public void shouldSelectOptionByVisibleTextInDropdown() {
        driver.get(WEB_FORM);
        Select select = new Select(driver.findElement(By.name("my-select")));
        String optionLabel = "Two";
        select.selectByVisibleText(optionLabel);
        assertThat(select.getFirstSelectedOption().getText(), equalTo(optionLabel));
    }

    @Test
    public void shouldInteractWithHtmlDatalistUsingSeleniumCommands() throws InterruptedException {
        driver.get(WEB_FORM);
        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.click();
        String text = "Los Angeles";
        String locator = "//datalist[@id='my-options']/option";
        List<WebElement> options = driver.findElements(By.xpath(locator));
        boolean isOptionExist =
                options.stream()
                        .anyMatch(opt -> opt.getAttribute("value").equalsIgnoreCase(text));
        //if (isOptionExist) datalist.sendKeys(text);
        Assert.assertTrue(isOptionExist);
    }
}
