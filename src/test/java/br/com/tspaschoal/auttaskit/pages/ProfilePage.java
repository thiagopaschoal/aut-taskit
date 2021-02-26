package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver navigator) {
        super(navigator);
    }

    public ProfilePage clickTabMoreDataAboutYou() {
        this.navigator.findElement(By.xpath("//a[@href=\"#moredata\"]")).click();
        return this;
    }

    public AddMoreDataModalPage clickButtonAddMoreData() {
        this.navigator.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddMoreDataModalPage(this.navigator);
    }

    public void removeContact(String contact) {
        final WebElement contactToDelete =
                this.navigator.findElement(By.xpath("//div[@id=\"moredata\"]//li/span[text()=\"" + contact + "\"]//following-sibling::a"));
        contactToDelete.click();
    }
}
