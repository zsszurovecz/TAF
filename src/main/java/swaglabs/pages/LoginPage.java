package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    /*
    LOCATORS
     */
    private final By usernameInputBy = By.name("user-name");
    private final By passwordInputBy = By.name("password");
    private final By loginButtonBy = By.name("login-button");
    private final WebDriver webDriver;

    /*
    KONSTRUKTOR
     */
    public LoginPage(WebDriver driver) {
        webDriver = driver;
    }

    /*
    MŰVELETEK AZ OLDALON A LOCATOR-OKKAL
     */
    public void fillUserNameInput(String userName) {
        WebElement userNameInput = webDriver.findElement(usernameInputBy);
        userNameInput.sendKeys(userName);
    }

    public void fillPasswordInput(String password) {
        WebElement passwordInput = webDriver.findElement(passwordInputBy);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = webDriver.findElement(loginButtonBy);
        loginButton.click();
    }

    public void loginWithUserAndPassword(String user, String password) {
        // when user types valid auth data
        WebElement userNameInput = webDriver.findElement(By.name("user-name"));
        userNameInput.sendKeys(user);

        WebElement passwordInput = webDriver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = webDriver.findElement(By.name("login-button"));
        loginButton.click();
    }

}
