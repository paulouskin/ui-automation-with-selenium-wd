package pl.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.luxoft.selenium.pageelements.SearchField;

import java.time.Duration;

public class EtsyLandingPagePO {

    private static final String HOST = "https://www.etsy.com";
    private static final long DEFAULT_WAIT_PERIOD = 5;

    private Logger logger = LoggerFactory.getLogger(EtsyLandingPagePO.class);
    private static final String ACCEPT_DEFAULT_PRIVACY_POLICY_LOCATOR =
            "//button[@data-gdpr-single-choice-accept='true']";
    private static final By UPDATE_SETTING_BUTTON_LOCATOR = By.cssSelector("[data-gdpr-open-full-settings]");
    private static final By SITE_CUSTOMIZATION_TOGGLE_LOCATOR = By.cssSelector("[for=personalization_consent]");
    private static final By PERSONALIZED_ADVERTISING_TOGGLE_LOCATOR = By.cssSelector("[for=third_party_consent]");
    private WebDriver driver;
    private WebElement acceptButton;
    private SearchField searchField;

    private WebDriverWait wait;

    public EtsyLandingPagePO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_PERIOD));
    }

    public void goTo() {
        logger.info("Navigating to the etsy.com landing page");
        driver.get(HOST);
    }

    public void acceptDefaultPrivacyPolicy() {
        acceptButton = driver.findElement(By.xpath(ACCEPT_DEFAULT_PRIVACY_POLICY_LOCATOR));
        acceptButton.click();
    }

    public boolean isUserCanProceedWithTheShopping() {
        return wait.until(ExpectedConditions.invisibilityOf(acceptButton));
    }

    public void showPrivacyPolicyUpdateOptions() {
        var updatePolicyButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(UPDATE_SETTING_BUTTON_LOCATOR)
        );
        updatePolicyButton.click();
    }

    public boolean arePrivacyPolicyUpdateOptionsAvailable() {
        WebElement siteCustomizationToggle = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(SITE_CUSTOMIZATION_TOGGLE_LOCATOR));
        WebElement sitePersonalizedAdvertisingToggle = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(SITE_CUSTOMIZATION_TOGGLE_LOCATOR));
        return siteCustomizationToggle.isDisplayed() && sitePersonalizedAdvertisingToggle.isDisplayed();
    }

    public void searchFor(String searchQuery) {
        this.searchField = new SearchField(driver);
        searchField.enterQuery(searchQuery);
        searchField.submit();
    }
}
