package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageAnnotated {

    @FindBy(name = "user-name")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "login-button")
    private WebElement loginButton;

    private final WebDriver webDriver;

    /*
    KONSTRUKTOR
     */
    public LoginPageAnnotated(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
    MŰVELETEK AZ OLDALON A LOCATOR-OKKAL
     */
    public void fillUserNameInput(String userName) {
        userNameInput.sendKeys(userName);
    }

    public void fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginWithUserAndPassword(String user, String password) {
        // when user types valid auth data
        userNameInput.sendKeys(user);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
