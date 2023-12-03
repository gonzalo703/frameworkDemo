package org.Gonzalo.Manzano.pageObjects;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        // Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".np-animating")
    WebElement spinner;
    By produtcsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(produtcsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product -> product
                .findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

    }

    public void addProductToCart(String productName) {
        WebElement products = getProductByName(productName);
        products.findElement(addToCart);
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
