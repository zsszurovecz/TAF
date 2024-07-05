package swaglabs.nonfunctional.visualtests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.LoginPage;
import swaglabs.pages.ProductListPage;

public class VisualTests extends DriverFactory {

    @Test(priority = 1)
    @Description("Prerequisite test case for visual tests which login and open up the product list screen.")
    public void loginWithVisualUserTest() {
        // given the product is loaded to browser
        driver.get("https://www.saucedemo.com");  //weboldal betöltése
        LoginPage loginPage = new LoginPage(driver);

        // when user types valid auth data
        loginPage.fillUserNameInput("visual_user");
        loginPage.fillPasswordInput("secret_sauce");
        loginPage.clickLoginButton();

        // then login happens and products loaded
        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertNotNull(productListPage.getPageTitle());
        Assert.assertTrue(productListPage.getPageTitle().isDisplayed());   //true ha a webelemt megjelent a felületen
    }

    @Test(dependsOnMethods = {"loginWithVisualUserTest"})
    @Description("Create new base image after login.")
    public void navigateToProductDetailsVisualBaseSnapshotTest() {
        takeScreenshot(true);
    }

    @Test(dependsOnMethods = {"loginWithVisualUserTest"})
    @Description("Visual regression test on the base image after login.")
    public void navigateToProductDetailsVisualSnapshotTest() {
        Assert.assertTrue(compareLatestSnapshot(), "Current screen is mismatch with the base snapshot image.");
    }
}
