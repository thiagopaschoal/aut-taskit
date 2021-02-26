package br.com.tspaschoal.auttaskit.tests;

import static org.hamcrest.CoreMatchers.*;

import br.com.tspaschoal.auttaskit.pages.HomePage;
import br.com.tspaschoal.auttaskit.support.DriverManagerFactory;
import br.com.tspaschoal.auttaskit.support.enums.DriverType;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        this.webDriver = DriverManagerFactory.getDriver(DriverType.FIREFOX);
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void shouldTestIfWebSiteWasOpenCorrectly() {
        String textFromLogo = new HomePage(this.webDriver).getTextFromLogo();
        assertThat(textFromLogo, is("Task it!"));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }

}
