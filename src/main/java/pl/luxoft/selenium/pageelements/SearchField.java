package pl.luxoft.selenium.pageelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchField {

    private static final By SEARCH_QUERY_AREA_LOCATOR = By.xpath("//*[@id='global-enhancements-search-query']");
    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@data-id='gnav-search-submit-button']");
    private static final long DEFAULT_WAIT_PERIOD = 5;

    private WebElement searchQueryTextArea;
    private WebElement searchSubmitButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchField(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_PERIOD));
        initElements();
    }

    private void initElements() {
        searchQueryTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_QUERY_AREA_LOCATOR));
        searchSubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON_LOCATOR));
    }

    public void enterQuery(String searchQuery) {
        searchQueryTextArea.clear();
        searchQueryTextArea.sendKeys(searchQuery);
    }

    public void submit() {
        searchQueryTextArea.sendKeys(Keys.ENTER);
    }
}
