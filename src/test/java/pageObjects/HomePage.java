package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchButton = By.cssSelector("button[aria-label='Search or jump to…']");
    private final By searchInput = By.cssSelector("input[type='text'][role='combobox']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void open() {
        driver.get("https://github.com");
        wait.until(ExpectedConditions.titleContains("GitHub"));
    }

    public boolean isAt() {
        return driver.getTitle().contains("GitHub");
    }

    public void searchFor(String term) {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(term, Keys.ENTER);

    }
}
