package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final By productTitleBy = By.className("inventory_details_name");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        WebElement productTitle = driver.findElement(productTitleBy);
        return productTitle.getText();
    }
}
