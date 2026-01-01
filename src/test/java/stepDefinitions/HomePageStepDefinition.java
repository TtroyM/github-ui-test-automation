package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePageStepDefinition {
    private WebDriver driver = Hooks.driver;

    @Given("the user is on GitHub Home Page")
    public void the_user_is_on_git_hub_home_page() {

        driver = Hooks.driver;
        driver.get("https://github.com");
        Assert.assertTrue(driver.getTitle().contains("GitHub"));
    }
    @When("the user searches for {string}")
    public void the_user_searches_for_result(String search) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By searchButton = By.cssSelector("button[aria-label='Search or jump to…']");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        By searchInput = By.cssSelector("input[type='text'][role='combobox']");
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));

        input.sendKeys(search);
        input.sendKeys(Keys.ENTER);
    }

    @Then("the correct {string} will be in search results")
    public void the_correct_search_will_be_in_results(String searchResult) {

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains(searchResult.toLowerCase()),
                "Expected search results to contain: " + searchResult);
    }
}
