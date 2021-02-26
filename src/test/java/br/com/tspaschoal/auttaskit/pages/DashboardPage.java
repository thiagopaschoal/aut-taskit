package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver navigator) {
        super(navigator);
    }

    public WebElement getUserLoggedFromHeader() {
        return this.navigator.findElement(By.className("me"));
    }

    public ProfilePage clickUserProfile() {
        this.navigator.findElement(By.className("me")).click();
        return new ProfilePage(this.navigator);
    }
}
