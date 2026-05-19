package pl.softra.www.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.softra.www.utils.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("not @API")
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: '{}'", scenario.getName());
        DriverFactory.initializeDriver();
    }

    @After("not @API")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");
                } catch (Exception e) {
                    logger.error("Failed to take screenshot", e);
                }
            }
        }
        logger.info("Finished scenario: '{}' with status: {}", scenario.getName(), scenario.getStatus());
        DriverFactory.quitDriver();
    }

    // Opcjonalnie: Możesz dodać specjalny Hook tylko dla API
    @Before("@API")
    public void setUpApi() {
        System.out.println(">>> Konfiguruję klienta API (bez przeglądarki)...");
        // Tutaj mógłbyś np. ustawić RestAssured.baseURI, jeśli nie robisz tego w Steps
    }
}