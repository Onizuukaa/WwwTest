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

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: '{}'", scenario.getName()); // Widać co testujemy
        //  Inicjalizacja sterownika przed każdym scenariuszem
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Zrzut ekranu");
                } catch (Exception e) {
                    //System.out.println("Nie udało się zrobić zrzutu ekranu: " + e.getMessage());
                    logger.error("Failed to take screenshot", e);
                }
            }
        }
        logger.info("Finished scenario: '{}' with status: {}", scenario.getName(), scenario.getStatus()); // DODANE
        DriverFactory.quitDriver();
    }
}