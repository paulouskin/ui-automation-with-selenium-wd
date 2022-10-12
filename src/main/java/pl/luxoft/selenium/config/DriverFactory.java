package pl.luxoft.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private RemoteWebDriver webDriver;

    private final DriverType selectedDriverType;

    public DriverFactory() {
        DriverType driverType = DriverType.CHROME;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException exc){
            logger.error("Unknown driver specified, defaulting to " + driverType);
        } catch (NullPointerException exc) {
            logger.error("No driver specified, defaulting to " +  driverType);
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() {
        if (null == webDriver){
            instantiateWebDriver(selectedDriverType);
        }
        return webDriver;
    }

    private void instantiateWebDriver(DriverType selectedDriverType) {
        logger.info("Selected browser: " + selectedDriverType);
        DesiredCapabilities dc = new DesiredCapabilities();
        webDriver = selectedDriverType.getWebDriverObject(dc);
    }

    public void quitDriver() {
        if (null != webDriver){
            webDriver.quit();
            webDriver = null;
        }
    }
}
