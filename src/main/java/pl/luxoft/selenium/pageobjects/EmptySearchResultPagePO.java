package pl.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmptySearchResultPagePO {

    private static final long DEFAULT_WAIT_PERIOD = 5;
    private final WebDriverWait wait;
    private WebDriver driver;

    private static final By INVALID_SEARCH_MESSAGE_LOCATOR =
            By.xpath("//p[@class='wt-text-heading-02 wt-pt-xs-8']");

    public EmptySearchResultPagePO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_PERIOD));
    }

    public boolean isVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(INVALID_SEARCH_MESSAGE_LOCATOR)
        ).isDisplayed();
    }
}
