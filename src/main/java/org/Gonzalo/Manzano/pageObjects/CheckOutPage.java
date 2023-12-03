package org.Gonzalo.Manzano.pageObjects;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        // Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".action__submit")
    WebElement submit;
    @FindBy(xpath = "//button[contains(@class,'ta-item')])[1]")
    WebElement selectCountry;
    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
