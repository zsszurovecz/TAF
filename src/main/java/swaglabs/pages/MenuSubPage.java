package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuSubPage {
    private final WebDriver driver;
    private final By menuBy = By.xpath("//*[@class='bm-burger-button']/button");
    private final By logoutMenuBy = By.xpath("//*[contains(text(),'Logout')]");

    public MenuSubPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logoutFromMenu() {
        WebElement menu = driver.findElement(menuBy);
        menu.click();
        WebElement logoutMenu = driver.findElement(logoutMenuBy);
        logoutMenu.click();
    }
}
