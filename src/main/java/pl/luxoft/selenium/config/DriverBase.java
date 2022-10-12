package pl.luxoft.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());

    private static ThreadLocal<DriverFactory> driverThread;

    protected static Logger logger = LoggerFactory.getLogger(DriverBase.class);

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        logger.info("Instantiating webdriver instance");
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory webDriverThread = new DriverFactory();
            webDriverThreadPool.add(webDriverThread);
            return webDriverThread;
        });
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies(){
        logger.info("Cleaning up cookies");
        getDriver().manage().deleteAllCookies();
    }

    public static RemoteWebDriver getDriver() {
        return driverThread.get().getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        logger.info("Closing webdriver instance");
        for (DriverFactory webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }
}


