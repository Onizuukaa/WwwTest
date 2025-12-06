package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import service.driver.DriverFactory;


public class Hooks {

	@Before
	public void setUp() {
		// Inicjalizacja przed każdym scenariuszem
		DriverFactory.initializeDriver();
	}

	@After
	public void tearDown(Scenario scenario) {

		// Sprawdzamy, czy scenariusz zakończył się błędem
		if (scenario.isFailed()) {
			WebDriver driver = DriverFactory.getDriver();
			if (driver != null) {
				// Robimy zdjęcie
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				// Dołączamy zdjęcie do raportu Cucumbera
				scenario.attach(screenshot, "image/png", "Zrzut ekranu błędu");
			}
		}
		// Zamykamy przeglądarkę niezależnie od wyniku
		DriverFactory.quitDriver();
	}
}