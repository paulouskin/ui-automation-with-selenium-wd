package pl.luxoft.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.luxoft.selenium.config.DriverBase;
import pl.luxoft.selenium.pageobjects.EtsyLandingPagePO;

public class ParallelTestRunIT extends DriverBase {


    @Test
    public void shouldProceedWithShoppingAfterPolicyAcceptance() {
        var driver = DriverBase.getDriver();
        var landingPage = new EtsyLandingPagePO(driver);
        landingPage.goTo();
        landingPage.acceptDefaultPrivacyPolicy();
        Assert.assertTrue(landingPage.isUserCanProceedWithTheShopping());
    }

    @Test
    public void privacyPolicyUpdateOptionsAreAvailable() {
        var driver = DriverBase.getDriver();
        var landingPage = new EtsyLandingPagePO(driver);
        landingPage.goTo();
        landingPage.showPrivacyPolicyUpdateOptions();
        Assert.assertTrue(landingPage.arePrivacyPolicyUpdateOptionsAvailable());
    }

}
