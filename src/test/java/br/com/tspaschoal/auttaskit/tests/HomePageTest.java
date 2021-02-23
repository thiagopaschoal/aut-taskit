package br.com.tspaschoal.auttaskit.tests;

import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void shouldTestIfWebSiteWasOpenCorrectly() {
        WebElement element = this.webDriver.findElement(By.className("brand-logo"));
        assertThat(element.getText(), is("Task it!"));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }

}
