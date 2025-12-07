package pl.softra.www.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "pl.softra.www.steps",
                "pl.softra.www.hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/Cucumber.json"
        },
        monochrome = true
)
public class TestRunner {
}
