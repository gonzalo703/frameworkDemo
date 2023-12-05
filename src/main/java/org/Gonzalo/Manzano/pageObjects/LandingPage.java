package org.Gonzalo.Manzano.pageObjects;

//import org.openqa.selenium.By;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        // Initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Old notation
    /*WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("mail@example.com");
    WebElement userPassword = driver.findElement(By.id("userPassword")).sendKeys("Abcd123+");
    WebElement buttonLogin = driver.findElement(By.id("login")).click();*/

    // PageFactory notation
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(css = "[class*='flyInOut'")
    WebElement errorMessage;

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public ProductCatalogue loginApplication(String Email, String password) {
        userEmail.sendKeys(Email);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
