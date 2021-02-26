package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver navigator) {
        super(navigator);
    }

    public String getTextFromLogo() {
        final WebElement logo = this.navigator.findElement(By.className("brand-logo"));
        return logo.getText();
    }

    public LoginPage clickSignIn() {
        this.navigator.findElement(By.linkText("Sign in")).click();
        return new LoginPage(this.navigator);
    }
}
