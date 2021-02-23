package br.com.tspaschoal.auttaskit.tests;

import br.com.tspaschoal.auttaskit.support.DataUtils;
import br.com.tspaschoal.auttaskit.support.ImageScreenshot;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    @Rule
    public TestName testName = new TestName();

    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        this.webDriver = new FirefoxDriver();
        this.webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void shouldTestLoginSuccess() {
        this.webDriver.findElement(By.linkText("Sign in")).click();

        final WebElement formSignInBox = this.webDriver.findElement(By.id("signinbox"));
        formSignInBox.findElement(By.name("login")).sendKeys("thiago");
        formSignInBox.findElement(By.name("password")).sendKeys("123456");
        formSignInBox.findElement(By.linkText("SIGN IN")).click();

        final String meLabel = this.webDriver.findElement(By.className("me")).getText();
        ImageScreenshot.takePicture(this.webDriver, DataUtils.getTimestamp() + "_" + testName.getMethodName() + ".png");
        Assert.assertThat(meLabel, CoreMatchers.is("Hi, Thiago Paschoal"));
    }

    @Test
    public void shouldTestShowToastMessageWhenLoginAndPasswordAreInvalid() {
        this.webDriver.findElement(By.linkText("Sign in")).click();

        final WebElement formSignInBox = this.webDriver.findElement(By.id("signinbox"));
        formSignInBox.findElement(By.name("login")).sendKeys("thiago@#$");
        formSignInBox.findElement(By.name("password")).sendKeys("1236789");
        formSignInBox.findElement(By.linkText("SIGN IN")).click();

        ImageScreenshot.takePicture(this.webDriver, DataUtils.getTimestamp() + "_" + testName.getMethodName() + ".png");

        final String toastMessage = this.webDriver.findElement(By.id("toast-container")).getText();
        Assert.assertThat(toastMessage, CoreMatchers.is("Maybe you brain dropped the password or login in some place!"));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}
