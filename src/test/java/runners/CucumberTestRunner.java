package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features="src/test/resources/features",
                 glue = {"stepDefinitions"},
                 monochrome = true,
                 //tags
                 plugin= {
                            "pretty",
                            "html:target/cucumber.html",
                            "json:target/cucumber.json",
                            "junit:target/cucumber.xml",
                            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                            "rerun:target/failed_scenarios.txt"})

public class CucumberTestRunner extends AbstractTestNGCucumberTests{

    @DataProvider(parallel = false)
    @Override
    public Object[][]scenarios() {
        return super.scenarios();
    }
}
