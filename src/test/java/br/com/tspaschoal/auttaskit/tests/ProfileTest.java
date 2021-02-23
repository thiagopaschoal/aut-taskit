package br.com.tspaschoal.auttaskit.tests;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProfileTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
        this.webDriver.findElement(By.linkText("Sign in")).click();

        final WebElement formSignInBox = this.webDriver.findElement(By.id("signinbox"));
        formSignInBox.findElement(By.name("login")).sendKeys("thiago");
        formSignInBox.findElement(By.name("password")).sendKeys("123456");
        formSignInBox.findElement(By.linkText("SIGN IN")).click();

        this.webDriver.findElement(By.className("me")).click();
        this.webDriver.findElement(By.xpath("//a[@href=\"#moredata\"]")).click();
        this.webDriver.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        final WebElement formAddMoreData = this.webDriver.findElement(By.xpath("//div[@id=\"addmoredata\"]"));
        final Select selectType = new Select(formAddMoreData.findElement(By.name("type")));
        selectType.selectByVisibleText("E-mail");
        formAddMoreData.findElement(By.name("contact")).sendKeys("thiagopaschoal076@gmail.com");
        formAddMoreData.findElement(By.linkText("SAVE")).click();
    }

    @Test
    public void shouldTestAddMoreDataAboutYou() {
        final WebElement toast = this.webDriver.findElement(By.id("toast-container"));
        final String toastMessage = toast.getText();
        Assert.assertThat(toastMessage, CoreMatchers.is("Your contact has been added!"));
    }

    @Test
    public void shouldTestRemoveSpecificEmail() {

        final WebElement toastContactSave = this.webDriver.findElement(By.id("toast-container"));
        final WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 5000);
        webDriverWait.until(ExpectedConditions.stalenessOf(toastContactSave));

        final String emailToBeDeleted = "thiagopaschoal076@gmail.com";
        final WebElement linkToDelete = this.webDriver.findElement(By.xpath("//div[@id=\"moredata\"]//li/span[text()=\"" + emailToBeDeleted + "\"]//following-sibling::a"));
        linkToDelete.click();

        this.webDriver.switchTo().alert().accept();

        final WebElement toast = this.webDriver.findElement(By.id("toast-container"));
        final String toastMessage = toast.getText();
        Assert.assertThat(toastMessage, CoreMatchers.is("Rest in peace, dear email!"));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}
