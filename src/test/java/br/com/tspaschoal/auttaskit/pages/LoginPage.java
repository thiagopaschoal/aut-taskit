package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver navigator) {
        super(navigator);
    }

    public LoginPage typeLogin(String login) {
        this.getFormSignInBox().findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password) {
        this.getFormSignInBox().findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public DashboardPage clickSignIn() {
        this.getFormSignInBox().findElement(By.linkText("SIGN IN")).click();
        return new DashboardPage(this.navigator);
    }

    private WebElement getFormSignInBox() {
        return this.navigator.findElement(By.id("signinbox"));
    }
}
