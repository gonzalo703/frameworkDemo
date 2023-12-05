package org.Gonzalo.Manzano.Tests;


import org.Gonzalo.Manzano.pageObjects.*;
import org.Gonzalo.Manzano.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;


public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";



    @Test
    public void submitOrder() {


        ProductCatalogue productCatalogue = landingPage.loginApplication("mail@example.com", "Abcd123+");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("argentina");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));


    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("mail@example.com", "Abcd123+");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }


}
