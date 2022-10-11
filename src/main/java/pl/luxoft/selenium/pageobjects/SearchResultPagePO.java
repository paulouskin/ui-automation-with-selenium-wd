package pl.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SearchResultPagePO {
    private static final long DEFAULT_WAIT_PERIOD = 5;

    private static final By SEARCH_RESULT_ITEMS = By.xpath("//h3[contains(@class,'v2-listing-card__title')]");
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LoggerFactory.getLogger(SearchResultPagePO.class);
    public SearchResultPagePO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_PERIOD));
    }

    public boolean isSearchResultsContainsRequiredItemsForQuery(String searchQuery) {
        logger.info("Verifying search result items contains query '" + searchQuery +"'");
        var tokinezedSearchQuery = Arrays.stream(searchQuery.split(" ")).toList();
        return getItemTitles().stream()
                        .limit(1)
                        .map(line -> Arrays.stream(line.toLowerCase().split(" ")).toList())
                        .allMatch(line -> line.containsAll(tokinezedSearchQuery));
    }

    private List<String> getItemTitles() {
        return getItemTitlesElements().stream().map(WebElement::getText).toList();
    }

    private List<WebElement> getItemTitlesElements() {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_RESULT_ITEMS)
        );
    }
}
