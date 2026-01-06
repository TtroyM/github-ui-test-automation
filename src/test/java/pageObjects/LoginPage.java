package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.id("login_field");
    private final By passwordField = By.id("password");
    private final By signInButton = By.name("commit");
    private final By errorBanner = By.cssSelector("div.flash-error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://github.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("/login");
    }

    public void enterUsername(String username) {
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        user.clear();
        user.sendKeys(username);
    }

    public void enterPassword(String pass) {
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        password.clear();
        password.sendKeys(pass);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public String getAuthErrorTextIfPresent() {
        if(driver.findElements(errorBanner).isEmpty()) return null;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner)).getText();
    }
}
