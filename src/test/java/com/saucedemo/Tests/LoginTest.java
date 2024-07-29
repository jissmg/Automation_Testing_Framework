package com.saucedemo.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucedemo.Pages.LoginPage;
import com.saucedemo.Utils.ExcelUtils;
import com.saucedemo.base.TestBase;

public class LoginTest extends TestBase {
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        initialise();
        loginPage = new LoginPage();
    }

    @Test(dataProvider = "loginDataProvider")
    public void verifyTestLogin(String username, String password) {
        loginPage.login(username, password);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login was not successful.");
    }

    @Test
    public void verifyLoginWithNoCredentials() {
        loginPage.login("", "");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required", "Error message did not match.");
    }

    @Test(dataProvider = "loginDataProvider")
    public void verifyLogout(String username, String password) {
        loginPage.login(username, password);
        loginPage.logout();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("www.saucedemo.com"), "Logout was not successful.");
    }

    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() throws IOException {
        String path = ".\\File\\Book1.xlsx";
        ExcelUtils excelUtils = new ExcelUtils(path);
        int rowCount = excelUtils.getRowCount("Sheet1");
        int colCount = excelUtils.getColumnCount("Sheet1", 0);

        String[][] loginData = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                loginData[i - 1][j] = excelUtils.getCellValue("Sheet1", i, j);
            }
        }
        return loginData;
    }
}
