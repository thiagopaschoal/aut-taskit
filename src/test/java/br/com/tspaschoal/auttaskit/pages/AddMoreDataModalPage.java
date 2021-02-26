package br.com.tspaschoal.auttaskit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddMoreDataModalPage extends BasePage {

    public AddMoreDataModalPage(WebDriver navigator) {
        super(navigator);
    }

    public ProfilePage addData(String type, String contact) {
        this.chooseType(type);
        this.typeContact(contact);
        return clickSave();
    }

    private AddMoreDataModalPage chooseType(String type) {
        final WebElement formAddMoreData = this.getFormAddMoreDataBox();
        final Select selectType = new Select(formAddMoreData.findElement(By.name("type")));
        selectType.selectByVisibleText(type);
        return this;
    }

    private AddMoreDataModalPage typeContact(String contact) {
        final WebElement formAddMoreData = this.getFormAddMoreDataBox();
        formAddMoreData.findElement(By.name("contact")).sendKeys(contact);
        return this;
    }

    private ProfilePage clickSave() {
        final WebElement formAddMoreData = this.getFormAddMoreDataBox();
        formAddMoreData.findElement(By.linkText("SAVE")).click();
        return new ProfilePage(this.navigator);
    }

    private WebElement getFormAddMoreDataBox() {
        return this.navigator.findElement(By.xpath("//div[@id=\"addmoredata\"]"));
    }
}
