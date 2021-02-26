package br.com.tspaschoal.auttaskit.support;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;
    public void kill() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public abstract void create();

    public WebDriver getDriver() {
        if (driver == null) {
            create();
        }
        return this.driver;
    }
}
