package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.LoginPage;

import java.time.Duration;

public class LoginPageStepDefinition {

    private WebDriver driver = Hooks.driver;

    @Given("the user is on GitHub login page")
    public void the_user_is_on_git_hub_login_page() {

        driver = Hooks.driver;
        driver.get("https://github.com/login");
        Assert.assertTrue(driver.getTitle().contains("GitHub"));
    }


    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String email, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        user.clear();
        user.sendKeys(email);

        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        pass.clear();
        pass.sendKeys(password);
    }


    @When("the user clicks the Sign In button")
    public void the_user_clicks_the_sign_in_button() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(By.name("commit"))).click();
    }


    @Then("an authentication error should be displayed")
    public void authentication_error_should_be_displayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        By errorBanner = By.cssSelector("div.flash-error");

        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner)).getText();

        Assert.assertTrue(msg != null && !msg.trim().isEmpty(),
                "Expected an error message, but the message text was empty");
    }


    @Then("an error message should be displayed")
    public void error_message_should_be_displayed() {

        // If required fields block submission, GitHub keeps you on /login
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Expected to remain on login page due to required field validation"
        );
    }
}
