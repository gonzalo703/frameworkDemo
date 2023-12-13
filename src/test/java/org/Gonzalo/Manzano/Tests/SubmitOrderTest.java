package org.Gonzalo.Manzano.Tests;


import org.Gonzalo.Manzano.TestComponents.BaseTest;
import org.Gonzalo.Manzano.pageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";


    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) {


        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(input.get("product"));
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

    public File getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "/src/main/java/org/Gonzalo/Manzano/Reports"
                + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return file;
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        // Third method read data from jason
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +
                "\\src\\test\\java\\org\\Gonzalo\\Manzano\\data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0), data.get(1)}};


        // First method
        //return new Object[][]{{"mail@example.com","Abcd123+","ADIDAS ORIGINAL"}, {"mail2@example.com","Abcd123+","iphone

        // Second method hash map
        //        /*HashMap<String,String> map = new HashMap<>();
        //        map.put("email","mail@example.com" );
        //        map.put("password", "Abcd123+");
        //        map.put("product", "ADIDAS ORIGINAL");
        //
        //        HashMap<String,String> map1 = new HashMap<>();
        //        map1.put("email","mail2@example.com" );
        //        map1.put("password", "Abcd123+");
        //        map1.put("product", "iphone 13 pro");
        //
        //
        //        return new Object[][]{{map}, {map1}};*/ 13 pro"}};
    }
}