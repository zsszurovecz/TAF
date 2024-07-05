package swaglabs.functional.ui;

import common.ApiUtils;
import common.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swaglabs.pages.LoginPage;
import swaglabs.pages.ProductListPage;

public class LoginScreenComponentTests extends DriverFactory {

    @DataProvider(name = "login-name-password-provider")
    public String[][] loginNameAndPasswordDataProvider() {
        String[] data1 = {"standard_user", "secret_sauce"};
        String[] data2 = {"error_user", "secret_sauce"};
        String[] data3 = {"visual_user", "secret_sauce"};
        String[] data4 = {"performance_glitch_user", "secret_sauce"};
        String[][] data = {data1, data2, data3, data4};
        return data;
    }

    @Test(enabled = true, dataProvider = "login-name-password-provider")
    public void loginWithDataProvider2Test(String loginName, String password) {
        // given the product is loaded to browser
        driver.get("https://www.saucedemo.com");  //weboldal betöltése
        LoginPage loginPage = new LoginPage(driver);

        // when user types valid auth data
        loginPage.fillUserNameInput(loginName);
        loginPage.fillPasswordInput(password);
        loginPage.clickLoginButton();

        // then login happens and products loaded
        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertNotNull(productListPage.getPageTitle());
        Assert.assertTrue(productListPage.getPageTitle().isDisplayed());   //true ha a webelemt megjelent a felületen
    }

    // Tries to login with 'invalidusername' username and 'invalidpassword' password and clicks on Login button
    @Test(enabled = true)
    public void loginWithInValidDataTest() {

        // given
        driver.get("https://www.saucedemo.com");  //weboldal betöltése

        // when user types valid auth data
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUserNameInput("invalidusername");
        loginPage.fillPasswordInput("invalidpassword");
        loginPage.clickLoginButton();

        // then
        WebElement errorMessageContainer = driver.findElement(By.className("error-message-container"));
        String actualErrorMessage = errorMessageContainer.getText();
        Assert.assertEquals(
                actualErrorMessage,
                "Username and password do not match any user in this service",
                "A hibaüzenet nem felel meg az követelményeknek."
        );
    }

    // Tries to login with empty username 'secret_sauce' password and clicks on Login button
    @Test(enabled = true)
    public void loginWithEmptyUsernameDataTest() {
        driver.get("https://www.saucedemo.com");  //weboldal betöltése

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUserNameInput("");
        loginPage.fillPasswordInput("secret_sauce");
        loginPage.clickLoginButton();

        WebElement errorMessageContainer = driver.findElement(By.className("error-message-container"));
        String errorMessage = errorMessageContainer.getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }
}