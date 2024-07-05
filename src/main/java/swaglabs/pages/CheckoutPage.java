package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstNameBy = By.id("first-name");
    private final By lastNameBy = By.id("last-name");
    private final By zipCodeBy = By.id("postal-code");
    private final By ContinueButtonBy = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstNameInput(String firstName) {
        WebElement firstNameInput = driver.findElement(firstNameBy);
        firstNameInput.sendKeys(firstName);
    }

    public void fillLastNameInput(String lastName) {
        WebElement lastNameInput = driver.findElement(lastNameBy);
        lastNameInput.sendKeys(lastName);
    }

    public void fillZipCodeInput(String zipCode) {
        WebElement zipCodeInput = driver.findElement(zipCodeBy);
        zipCodeInput.sendKeys(zipCode);
    }

    public void clickContinueButton() {
        WebElement continueButton = driver.findElement(ContinueButtonBy);
        continueButton.click();
    }

    public String getFirstNameInput() {
        WebElement firstNameInput = driver.findElement(firstNameBy);
        return firstNameInput.getAttribute("value");
    }

    public String getLastNameInput() {
        WebElement lastNameInput = driver.findElement(lastNameBy);
        return lastNameInput.getAttribute("value");
    }

    public String getZipCodeInput() {
        WebElement zipCodeInput = driver.findElement(zipCodeBy);
        return zipCodeInput.getAttribute("value");
    }

    /*  Nem ajánlott, mert robusztusabb lesz a kódbázis, nem lehet rugalamasan teszteket összeállítani
    public void filloutCheckoutFormAndClickContinue(){
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("Elek");
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("Mekk");
        WebElement zipCodeInput = driver.findElement(By.id("postal-code"));
        zipCodeInput.sendKeys("1111");
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    */

}
