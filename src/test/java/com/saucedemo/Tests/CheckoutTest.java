package com.saucedemo.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.saucedemo.Pages.CartPage;
import com.saucedemo.Pages.CheckoutPage;
import com.saucedemo.Pages.LoginPage;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.base.TestBase;

public class CheckoutTest extends TestBase {
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        initialise();
        loginPage = new LoginPage();
    }

    @Test
    public void testCheckoutProcess() {
        productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.sortByPriceLowToHigh();
        productsPage.addSecondProductFromEachColumn();
        cartPage = productsPage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.enterCheckoutInformation("Jis", "George", "P6A 1V5");

        String paymentInfo = checkoutPage.getPaymentInfo();
        String totalPrice = checkoutPage.getTotalPrice();
        Assert.assertNotNull(paymentInfo, "Payment information should be displayed.");
        Assert.assertTrue(totalPrice.startsWith("Total: $"), "Total price should be displayed correctly.");

        checkoutPage.completeOrder();
        String completionMessage = checkoutPage.getCompletionMessage();
        Assert.assertEquals(completionMessage, "Thank you for your order!");

        loginPage.logout(); // Ensure logout after checkout process
    }

    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}
