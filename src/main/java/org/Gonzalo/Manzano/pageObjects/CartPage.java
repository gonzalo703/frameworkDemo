package org.Gonzalo.Manzano.pageObjects;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        // Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkOutElement;
    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    public boolean verifyProductDisplay(String productName) {
        return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckOutPage goToCheckOut() {
        checkOutElement.click();
        return new CheckOutPage(driver);
    }
}
