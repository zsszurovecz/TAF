package swaglabs.nonfunctional.performance;

import common.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.LoginPage;

public class SlowLoginTests extends DriverFactory {

    @Test(timeOut = 2000, enabled = true)
    public void loginWithPerformanceGlitchUserDataTest() {
        // given the product is loaded to browser
        driver.get("https://www.saucedemo.com");  //weboldal betöltése

        // when user types in valid auth data
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUserNameInput("performance_glitch_user");
        loginPage.fillPasswordInput("secret_sauce");
        loginPage.clickLoginButton();

        // then login happens and products loaded
        WebElement productsTitle = driver.findElement(By.className("title"));
        Assert.assertNotNull(productsTitle);
        Assert.assertEquals(productsTitle.getText(), "Products");
    }
}
