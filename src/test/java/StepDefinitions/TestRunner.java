package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/SoftraContact.feature", // lub po prostu "src/test/resources/Features"
		glue = {"StepDefinitions"},
		plugin = {
				"pretty",
				"html:target/cucumber-reports/report.html", // Poprawiono literówkę 'taget' -> 'target'
				"json:target/cucumber-reports/Cucumber.json"
		},
		monochrome = true
)
public class TestRunner {
}
