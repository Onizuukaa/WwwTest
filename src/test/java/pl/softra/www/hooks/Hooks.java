package pl.softra.www.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.softra.www.utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        // Inicjalizacja sterownika przed każdym scenariuszem
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
                    System.out.println("Nie udało się zrobić zrzutu ekranu: " + e.getMessage());
                }
            }
        }

        DriverFactory.quitDriver();
    }
}