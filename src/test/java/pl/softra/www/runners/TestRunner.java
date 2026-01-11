package pl.softra.www.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber") // Definiujemy silnik testowy (Cucumber)
@SelectClasspathResource("features") // Lokalizacja plików .feature (w resources)
// Wskazujemy pakiety, w których znajdują się kroki (Steps) oraz klasa Hooks
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pl.softra.www.steps,pl.softra.www.hooks")
// Konfiguracja raportowania:
// 'pretty' - ładne logi w konsoli,
// 'html' - raport dla człowieka,
// 'json' - raport dla Jenkinsa (do generowania wykresów)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/report.html, json:target/cucumber-reports/Cucumber.json"
)
public class TestRunner {
}