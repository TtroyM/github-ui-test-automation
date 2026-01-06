package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.HomePage;

import java.time.Duration;

public class HomePageStepDefinition {
    private WebDriver driver;
    private HomePage homePage;

    @Given("the user is on GitHub Home Page")
    public void the_user_is_on_git_hub_home_page() {
        driver = Hooks.driver;
        homePage = new HomePage(driver);

        homePage.open();
        Assert.assertTrue(homePage.isAt(), "User is not on GitHub Home Page");
    }
    @When("the user searches for {string}")
    public void the_user_searches_for_result(String search) {

        homePage.searchFor(search);
    }

    @Then("the correct {string} will be in search results")
    public void the_correct_search_will_be_in_results(String searchResult) {

        // searchResult example: "cucumber/docs"
        By repoLink = By.cssSelector("a[href='/" + searchResult + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the results page has loaded and the link appears
        wait.until(ExpectedConditions.presenceOfElementLocated(repoLink));

        Assert.assertTrue(driver.findElements(repoLink).size() > 0,
                "Expected search results to contain repo link: /" + searchResult);
    }
}
