package br.com.tspaschoal.auttaskit.tests;

import br.com.tspaschoal.auttaskit.pages.HomePage;
import br.com.tspaschoal.auttaskit.pages.ProfilePage;
import br.com.tspaschoal.auttaskit.support.DriverManagerFactory;
import static br.com.tspaschoal.auttaskit.support.enums.DriverType.*;
import static org.hamcrest.CoreMatchers.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "data/ProfileTest.csv")
public class ProfileTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        this.webDriver = DriverManagerFactory.getDriver(FIREFOX);
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void shouldTestAddMoreDataAboutYou(
            @Param(name="user") String user,
            @Param(name="password") String password,
            @Param(name="type") String type,
            @Param(name="contact") String contact,
            @Param(name="message") String expectedMessage
    ) {
        final String toastMessage = new HomePage(this.webDriver)
                .clickSignIn()
                .typeLogin(user)
                .typePassword(password)
                .clickSignIn()
                .clickUserProfile()
                .clickTabMoreDataAboutYou()
                .clickButtonAddMoreData()
                .addData(type, contact)
                .getToast().getText();

        assertThat(toastMessage, is(expectedMessage));
    }

    @Test
    public void shouldTestRemoveContact(
            @Param(name="user") String user,
            @Param(name="password") String password,
            @Param(name="type") String type,
            @Param(name="contact") String contact,
            @Param(name="message") String expectedMessage
    ){

        final WebElement toastContactSave = new HomePage(this.webDriver)
                .clickSignIn()
                .typeLogin(user)
                .typePassword(password)
                .clickSignIn()
                .clickUserProfile()
                .clickTabMoreDataAboutYou()
                .clickButtonAddMoreData()
                .addData(type, contact)
                .getToast();

        final ProfilePage profilePage = new ProfilePage(this.webDriver);
        final WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 5000);
        webDriverWait.until(ExpectedConditions.stalenessOf(toastContactSave));

        profilePage.removeContact(contact);
        this.webDriver.switchTo().alert().accept();
        final String toastMessage = profilePage.getToast().getText();
        assertThat(toastMessage, is(expectedMessage));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}
