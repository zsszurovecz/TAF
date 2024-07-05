package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final By backHomeButtonBy = By.id("back-to-products");
    private final By completeHeaderBy = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBackHomeButton() {
        WebElement backHomeButton = driver.findElement(backHomeButtonBy);
        backHomeButton.click();
    }

    public WebElement getCompleteMessageWebElement() {
        WebElement confirmText = driver.findElement(completeHeaderBy);
        return confirmText;
    }
}
