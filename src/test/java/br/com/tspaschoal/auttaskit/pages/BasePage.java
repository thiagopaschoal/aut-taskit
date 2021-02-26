package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver navigator;
    BasePage(WebDriver navigator) {
        this.navigator = navigator;
    }

    public WebElement getToast() {
        return this.navigator.findElement(By.id("toast-container"));
    }
}
