package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features/SoftraContact.feature",
glue= {"StepDefinitions"},
plugin = {
				"pretty",
		"html:taget/cucumber-reports/report.html",
		"json:target/cucumber-reports/Cucumber.json" // <--- TO JEST KLUCZOWE!
		},
		monochrome= true
		)
public class TestRunner {

}
