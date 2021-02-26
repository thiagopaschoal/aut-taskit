package br.com.tspaschoal.auttaskit.support.browsers;

import br.com.tspaschoal.auttaskit.support.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    private static final String CHROME_DRIVER_PATH_WINDOWS = "src/test/resources/drivers/chrome/windows/";
    private static final String CHROME_DRIVER_PATH_MAC = "src/test/resources/drivers/chrome/macos/chromedriver";
    private static final String CHROME_DRIVER_PATH_LINUX = "src/test/resources/drivers/chrome/linux/";

    @Override
    public void create() {
        final String path = getChromeDriverPathByOSType();
        System.setProperty("webdriver.chrome.driver", path);
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private String getChromeDriverPathByOSType() {
        final String osName = System.getProperty("os.name");
        if (osName.toLowerCase(Locale.ROOT).contains("windows")) {
            return CHROME_DRIVER_PATH_WINDOWS;
        } else if (osName.toLowerCase(Locale.ROOT).contains("mac")) {
            return CHROME_DRIVER_PATH_MAC;
        }
        return CHROME_DRIVER_PATH_LINUX;
    }
}
