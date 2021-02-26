package br.com.tspaschoal.auttaskit.tests;

import br.com.tspaschoal.auttaskit.pages.HomePage;
import br.com.tspaschoal.auttaskit.support.DriverManagerFactory;
import br.com.tspaschoal.auttaskit.support.utils.DataUtils;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static br.com.tspaschoal.auttaskit.support.ImageScreenshot.takePicture;
import static br.com.tspaschoal.auttaskit.support.enums.DriverType.CHROME;
import static org.hamcrest.CoreMatchers.is;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths =  "data/LoginPageTest.csv" )
public class LoginPageTest {

    @Rule
    public TestName testName = new TestName();

    private WebDriver webDriver;

    @Before
    public void setUp() {
        this.webDriver = DriverManagerFactory.getDriver(CHROME);
        this.webDriver.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void shouldTestLoginSuccess(@Param(name="user") String user,
                                       @Param(name="password") String password,
                                       @Param(name="message") String expectedMessage) {

        final String meLabel = new HomePage(this.webDriver)
                .clickSignIn()
                .typeLogin(user)
                .typePassword(password)
                .clickSignIn()
                .getUserLoggedFromHeader()
                .getText();

        takePicture(this.webDriver, DataUtils.getTimestamp() + "_" + testName.getMethodName() + ".png");
        Assert.assertThat(meLabel, is(expectedMessage));
    }

    @Test
    public void shouldTestShowToastMessageWhenLoginAndPasswordAreInvalid(
            @Param(name="user") String user,
            @Param(name="password") String password,
            @Param(name="message") String expectedMessage
    ) {

        final String toastMessage = new HomePage(this.webDriver)
                .clickSignIn()
                .typeLogin(user)
                .typePassword(password)
                .clickSignIn()
                .getToast().getText();

        takePicture(this.webDriver, DataUtils.getTimestamp() + "_" + testName.getMethodName() + ".png");
        Assert.assertThat(toastMessage, is(expectedMessage));
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}
