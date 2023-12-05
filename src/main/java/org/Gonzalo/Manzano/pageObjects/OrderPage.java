package org.Gonzalo.Manzano.pageObjects;

import org.Gonzalo.Manzano.AbstractObjects.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
    public List<WebElement> productNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        // Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }


}
