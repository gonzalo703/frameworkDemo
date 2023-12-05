package org.Gonzalo.Manzano.Tests;


import org.Gonzalo.Manzano.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() {

        landingPage.loginApplication("mail@example.com", "Ab123+");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }


}
