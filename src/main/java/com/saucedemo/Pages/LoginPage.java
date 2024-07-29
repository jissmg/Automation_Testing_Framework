package com.saucedemo.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.base.TestBase;

public class LoginPage extends TestBase {
    
    private WebDriverWait wait;

    public LoginPage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(id = "react-burger-menu-btn") 
    private WebElement userMenu;
    
    @FindBy(id = "logout_sidebar_link") 
    private WebElement logoutOption;
    
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.getText();
    }
    private void enterEmail(String email) {
    	usernameField.sendKeys(email);
	}

	private void enterPassword(String pwd) {
		passwordField.sendKeys(pwd);
	}

	private void clickLoginButton() {
		loginButton.submit();
	}
    public ProductsPage login(String username, String password) {
    	enterEmail(username);
    	enterPassword(password);
    	clickLoginButton();
       
        return new ProductsPage();
    }

    public void logout() {
        userMenu.click();
        wait.until(ExpectedConditions.visibilityOf(logoutOption));
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption));
        logoutOption.click();
    }
}
