package pl.softra.www.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber") // Mówimy: "Uruchom to silnikiem Cucumbera"
@SelectClasspathResource("features") // Gdzie są pliki .feature (w folderze resources)
// Wskazujemy, gdzie są kroki (steps) i hooki
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pl.softra.www.steps,pl.softra.www.hooks")
// TUTAJ JEST NOWA KONFIGURACJA PLUGINÓW:
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/report.html, json:target/cucumber-reports/Cucumber.json"
)
public class TestRunner {
}