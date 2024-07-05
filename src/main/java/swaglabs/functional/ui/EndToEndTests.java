package swaglabs.functional.ui;

import common.ApiUtils;
import common.DriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.*;

public class EndToEndTests extends DriverFactory {
    private String firstName;
    private String lastName;
    private String zipCode;

    @Test
    public void prepareRandomTestData() {
        String fullName = ApiUtils.getRandomFullName();
        if (fullName.contains(" ")) {
            firstName = fullName.split(" ")[0];
            lastName = fullName.split(" ")[1];
        } else {
            firstName = fullName;
            lastName = fullName;
        }
        zipCode = ApiUtils.getRandomZip();
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("zipCode: " + zipCode);
    }

    @Test(priority = 1, dependsOnMethods = {"prepareRandomTestData"})
    @Description("Standard user log in and buy multiple products with cart modification.")
    //teszteset rövid mondatszerű jellemzője
    public void standardUserMultipleProductPath() {
        // Előfeltétel
        driver.get("https://www.saucedemo.com");  //weboldal betöltése

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUserNameInput("standard_user");
        loginPage.fillPasswordInput("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickTShirtCartButton();
        productListPage.clickBackbackCartButton();
        productListPage.clickOnCartButton();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeBackpackButton();
        cartPage.clickOnCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstNameInput(firstName);
        checkoutPage.fillLastNameInput(lastName);
        checkoutPage.fillZipCodeInput(zipCode);
        checkoutPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        WebElement confirmText = checkoutCompletePage.getCompleteMessageWebElement();
        String confirmTextString = confirmText.getText();
        Assert.assertEquals(confirmTextString, "Thank you for your order!");

        MenuSubPage menuSubPage = new MenuSubPage(driver);
        menuSubPage.logoutFromMenu();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
