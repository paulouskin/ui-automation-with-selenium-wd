package pl.luxoft.lesson3;

import core.BaseExerciseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Set;

import static core.Constants.COOKIES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class ManageCookiesTest extends BaseExerciseTest {

    @Test
    public void shouldReadCookiesAndCheckItsContent() {
        driver.get(COOKIES);
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies.size(), equalTo(2));

        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue(), equalTo("John Doe"));
        assertThat(username.getPath(), equalTo("/"));

        driver.findElement(By.id("refresh-cookies")).click();
        WebElement cookiesList = driver.findElement(By.id("cookies-list"));
        assertThat(cookiesList.getText(), containsStringIgnoringCase("John Doe"));
    }

    @Test
    public void shouldAddCookiesAndDisplayItAfterButtonClick() {
        driver.get(COOKIES);
        WebDriver.Options options = driver.manage();
        Cookie newCookie = new Cookie("selenium-training", "Hello Selenium");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName()).getValue();
        assertThat(newCookie.getValue(), equalTo(readValue));

        driver.findElement(By.id("refresh-cookies")).click();

        WebElement cookiesList = driver.findElement(By.id("cookies-list"));
        assertThat(cookiesList.getText(), containsStringIgnoringCase(readValue));

    }

    @Test
    public void shouldEditExistingCookie() {
        driver.get(COOKIES);
        WebDriver.Options options = driver.manage();
        Cookie username = options.getCookieNamed("username");
        Cookie editedCookie = new Cookie(username.getName(), "Jack Black");
        options.addCookie(editedCookie);
        Cookie readCookie = options.getCookieNamed(username.getName());
        assertThat(editedCookie, equalTo(readCookie));
    }

    @Test
    public void shouldDeleteCookie() {
        driver.get(COOKIES);
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        Cookie username = options.getCookieNamed("username");
        options.deleteCookie(username);
        assertThat(options.getCookies().size(), equalTo(cookies.size() - 1));
    }

}
