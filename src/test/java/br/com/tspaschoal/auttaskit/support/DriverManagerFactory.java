package br.com.tspaschoal.auttaskit.support;

import br.com.tspaschoal.auttaskit.support.browsers.ChromeDriverManager;
import br.com.tspaschoal.auttaskit.support.browsers.FirefoxDriverManager;
import br.com.tspaschoal.auttaskit.support.enums.DriverType;
import org.openqa.selenium.WebDriver;

public class DriverManagerFactory {
    public static WebDriver getDriver(DriverType type) {
        WebDriver driver = null;
        switch (type) {
            case CHROME:
                final ChromeDriverManager chromeDriverManager = new ChromeDriverManager();
                driver = chromeDriverManager.getDriver();
                break;
            case SAFARI:
                throw new UnsupportedOperationException("we don't have implementation yet for this browser.");
            case EDGE:
                throw new UnsupportedOperationException("we don't have implementation yet for this browser.");
            default:
                final FirefoxDriverManager firefoxDriverManager = new FirefoxDriverManager();
                driver = firefoxDriverManager.getDriver();
        }
        return driver;
    }
}
