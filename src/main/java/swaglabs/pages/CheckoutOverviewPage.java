package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final By finishButtonBy = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinishButton(){
        WebElement finishButton = driver.findElement(finishButtonBy);
        finishButton.click();
    }
}
