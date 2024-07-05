package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final WebDriver driver;
    private final By checkoutButtonBy = By.id("checkout");

    private final By removeBackPackButtonBy = By.cssSelector("button[data-test='remove-sauce-labs-backpack']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCheckoutButton() {
        WebElement checkoutButton = driver.findElement(checkoutButtonBy);
        checkoutButton.click();
    }

    public void removeBackpackButton(){
        WebElement removeBackbackButton = driver.findElement(removeBackPackButtonBy);
        removeBackbackButton.click();
    }
}
