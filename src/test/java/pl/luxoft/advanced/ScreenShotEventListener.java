package pl.luxoft.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenShotEventListener implements WebDriverListener {

    private static final Logger logger = LoggerFactory.getLogger(ScreenShotEventListener.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Looking for element with locator: " + locator.toString());
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        WebDriverListener.super.beforeQuit(driver);
        takeScreenshot(driver);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        WebDriverListener.super.afterGet(driver, url);
        //takeScreenshot(driver);
    }

    private void takeScreenshot(WebDriver driver) {
        TakesScreenshot tsDriver = (TakesScreenshot) driver;
        File screenShot = tsDriver.getScreenshotAs(OutputType.FILE);
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        String screenshotFileName = String.format(
                "%s-screen.png",sessionId
        );
        Path destination = Paths.get(screenshotFileName);
        try {
            Files.move(screenShot.toPath(), destination);
        } catch (IOException e){
            logger.error("Exception moving screenshot from {} to {}", screenShot, destination, e);
        }
    }
}
