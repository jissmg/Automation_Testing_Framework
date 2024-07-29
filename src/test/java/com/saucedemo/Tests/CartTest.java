package com.saucedemo.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.saucedemo.Pages.CartPage;
import com.saucedemo.Pages.LoginPage;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.base.TestBase;

import java.util.List;

public class CartTest extends TestBase {
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        initialise();
        loginPage = new LoginPage();
    }

    @Test
    public void testCartItems() {
        productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.sortByPriceLowToHigh();
        productsPage.addSecondProductFromEachColumn();
        cartPage = productsPage.goToCart();
        List<String> cartItems = cartPage.getCartItems();
        Assert.assertEquals(cartItems.size(), 2, "Two products should be in the cart.");
    }

    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}

