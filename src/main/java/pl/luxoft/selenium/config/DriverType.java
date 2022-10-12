package pl.luxoft.selenium.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum DriverType implements DriverSetup{
    FIREFOX {
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxOptions options = new FirefoxOptions();
            options.merge(capabilities);
            String geckoDriverBinaryPath = "D:\\dev\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", geckoDriverBinaryPath);
            return new FirefoxDriver(options);
        }
    },
    CHROME {
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            String chromeDriverBinaryPath = "D:\\dev\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverBinaryPath);
            return new ChromeDriver(options);
        }
    };
}
