package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.LoginPage;

public class LoginPageStepDefinition {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the user is on GitHub login page")
    public void the_user_is_on_git_hub_login_page() {
        driver = Hooks.driver;
        loginPage = new LoginPage(driver);

        loginPage.open();
        Assert.assertTrue(loginPage.isAt(), "User is not on GitHub Login Page");
    }


    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String email, String password) {

        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
    }


    @When("the user clicks the Sign In button")
    public void the_user_clicks_the_sign_in_button() {

        loginPage.clickSignIn();
    }


    @Then("an authentication error should be displayed")
    public void authentication_error_should_be_displayed() {

        String msg = loginPage.getAuthErrorTextIfPresent();
        Assert.assertTrue(msg != null && !msg.trim().isEmpty(),
                "Expected Auth error, but it wasn't found");
    }


    @Then("an error message should be displayed")
    public void error_message_should_be_displayed() {

        // If required fields block submission, GitHub keeps you on /login
        Assert.assertTrue(loginPage.isAt(),
                "Expected to remain on login page due to required field validation"
        );
    }
}
