package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListPage {
    private final WebDriver driver;
    private final By oensieProductTitleBy = By.xpath("//*[contains(text(),'Sauce Labs Onesie')]");
    private final By tshirtCartButtonBy = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By bikelLightAddButtonBy = By.id("add-to-cart-sauce-labs-bike-light");
    private final By cartButtonBy = By.className("shopping_cart_link");
    private final By pageTitleBy = By.className("title");
    private final By backpackCartButtonBy = By.name("add-to-cart-sauce-labs-backpack");

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnOensieProduct() {
        WebElement oensieProductTitle = driver.findElement(oensieProductTitleBy);
        oensieProductTitle.click();
    }

    public void clickTShirtCartButton() {
        WebElement tShirtCartButton = driver.findElement(tshirtCartButtonBy);
        tShirtCartButton.click();
    }

    public void clickBikeLightAddButton() {
        WebElement bikeLightAddButton = driver.findElement(bikelLightAddButtonBy);
        bikeLightAddButton.click();
    }

    public void clickBackbackCartButton() {
        WebElement backpackCartButton = driver.findElement(backpackCartButtonBy);
        backpackCartButton.click();
    }

    public void clickOnCartButton() {
        WebElement cartButton = driver.findElement(cartButtonBy);
        cartButton.click();
    }

    public WebElement getPageTitle() {
        WebElement productsTitle = driver.findElement(pageTitleBy);
        return productsTitle;
    }
}
