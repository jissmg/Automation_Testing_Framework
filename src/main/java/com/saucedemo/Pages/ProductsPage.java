package com.saucedemo.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.TestBase;
import java.util.List;

public class ProductsPage extends TestBase {
    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.title")
    private static WebElement getMyTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = "select option[value='lohi']")
    private static WebElement getsortTitle;

    @FindBy(css = ".inventory_item .pricebar .btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    // Use the selectDropdownByValueOrText method from TestBase
    public void sortByPriceLowToHigh() {
        selectDropdownByValueOrText(sortDropdown, "lohi", "Price (low to high)");
    }

    public CartPage addSecondProductFromEachColumn() {
        addToCartButtons.get(1).click();
        addToCartButtons.get(3).click();
        return new CartPage();
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage();
    }

    public static String getPageTitle() {
        return getMyTitle.getText().trim();
    }

    public static String getSortedpage() {
        return getsortTitle.getText().trim();
    }
}
