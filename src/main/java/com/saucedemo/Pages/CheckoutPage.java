package com.saucedemo.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.base.TestBase;

public class CheckoutPage extends TestBase {
    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(className = "summary_value_label")
    private WebElement paymentInfo;

    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completionMessage;

    public CheckoutPage enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        continueButton.click();
        return new CheckoutPage();
    }

    public String getPaymentInfo() {
        return paymentInfo.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void completeOrder() {
        finishButton.click();
    }

    public String getCompletionMessage() {
        return completionMessage.getText();
    }
}
