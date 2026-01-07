package stepDefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RepoApiStepDefinition {

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private int getRepoStatus(String repo) {

        // repo example: "cucumber/docs"
        String url = "https://api.github.com/repos/" + repo;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .header("Accept", "application/vnd.github+json")
                    // User-Agent is recommended by GitHub APIs
                    .header("User-Agent", "Github-UI-Test-Automation")
                    .GET()
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            return response.statusCode();

        } catch (Exception e) {
            throw new RuntimeException("API request failed for: " + url, e);
        }
    }

    @Given("the GitHub repository {string} exists")
    public void the_github_repository_exists(String repo) {
        int status = getRepoStatus(repo);
        System.out.println("[API] GET /repos/" + repo + " " + status);
        Assert.assertEquals(status, 200, "Expected repo to exist but got HTTP " + status + " for " + repo);
    }

    @Given("the GitHub repository {string} does not exist")
    public void the_github_repository_does_not_exist(String repo) {
        int status = getRepoStatus(repo);
        System.out.println("[API] GET /repos/" + repo + " " + status);
        Assert.assertEquals(status, 404, "Expected repo NOT to exist but got HTTP " + status + " for " + repo);
    }
}
