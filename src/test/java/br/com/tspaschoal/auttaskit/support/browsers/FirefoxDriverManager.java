package br.com.tspaschoal.auttaskit.support.browsers;

import br.com.tspaschoal.auttaskit.support.DriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager  {

    private static final String GECKO_DRIVER_PATH_WINDOWS = "src/test/resources/drivers/firefox/windows/";
    private static final String GECKO_DRIVER_PATH_MAC = "src/test/resources/drivers/firefox/macos/geckodriver";
    private static final String GECKO_DRIVER_PATH_LINUX = "src/test/resources/drivers/firefox/linux/";

    @Override
    public void create() {
        final String path = getGeckoDriverPathByOSType();
        System.setProperty("webdriver.gecko.driver", path);
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private String getGeckoDriverPathByOSType() {
        final String osName = System.getProperty("os.name");
        if (osName.toLowerCase(Locale.ROOT).contains("windows")) {
            return GECKO_DRIVER_PATH_WINDOWS;
        } else if (osName.toLowerCase(Locale.ROOT).contains("mac")) {
            return GECKO_DRIVER_PATH_MAC;
        }
        return GECKO_DRIVER_PATH_LINUX;
    }

}
