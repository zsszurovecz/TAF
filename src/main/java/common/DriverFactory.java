package common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.time.Duration;

public class DriverFactory {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest(alwaysRun = true)
    public void webDriverSetup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(30000)); //timeout for the page load
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000)); //timeout for wait of the WebElements to be visible
        driver.manage().window().maximize();       //full screen browser window

        //Explicit wait beállítás
        wait = new WebDriverWait(driver, Duration.ofMillis(30000)); //30mp
    }

    @AfterTest(alwaysRun = true)
    public void webDriverTearDown() throws InterruptedException {
        Thread.sleep(2000);   //wait a bit to be able to process when we check the execution
        takeScreenshot();
        driver.quit();
    }

    protected void takeScreenshot(boolean isBaseScreenshot) {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE); //screenshot created in a working dir
        ScreenshotUtils.saveScreenshot(screenshotFile, isBaseScreenshot);                        //save it to the internal folder
    }

    protected void takeScreenshot() {
        takeScreenshot(false);
    }

    protected boolean compareLatestSnapshot() {
        takeScreenshot();
        return ScreenshotUtils.compareLatestImages();
    }
}
