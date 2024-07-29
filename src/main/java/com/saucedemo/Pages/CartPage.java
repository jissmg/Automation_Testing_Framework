package com.saucedemo.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.TestBase;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends TestBase {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public List<String> getCartItems() {
    	List<String> itemTexts = new ArrayList<>();
        for (WebElement item : cartItems) {
            itemTexts.add(item.getText());
        }
        return itemTexts;
    }

    public CheckoutPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutPage();
    }
}
