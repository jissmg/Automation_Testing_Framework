package com.saucedemo.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.saucedemo.Pages.LoginPage;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.base.TestBase;

public class ProductTest extends TestBase {
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        initialise();
        loginPage = new LoginPage();
    }

    @Test
    public void testSortProductsByPriceLowToHigh() {
        productsPage = loginPage.login("standard_user", "secret_sauce");
        String pageTitle = ProductsPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", "Page title did not match.");
        productsPage.sortByPriceLowToHigh();
        String sortedPageTitle = ProductsPage.getSortedpage();
        Assert.assertEquals(sortedPageTitle, "Price (low to high)", "Sort by price (low to high) did not work.");
    }

    @Test
    public void testAddProductsToCart() {
        productsPage = loginPage.login("standard_user", "secret_sauce");
        String pageTitle = ProductsPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", "Page title did not match.");
        productsPage.addSecondProductFromEachColumn();
        
    }

    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}
