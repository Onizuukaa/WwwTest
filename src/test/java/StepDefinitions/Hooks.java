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
		// Log diagnostyczny - sprawdzamy czy wchodzi do metody
		System.out.println("=== Zakończono scenariusz: " + scenario.getName() + " ===");

		if (scenario.isFailed()) {
			System.out.println("=== Scenariusz NIEUDANY. Próbuję zrobić zrzut ekranu... ===");

			WebDriver driver = DriverFactory.getDriver();
			if (driver != null) {
				try {
					byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/png", "Zrzut ekranu błędu");
					System.out.println("=== Zrzut ekranu dołączony do raportu! ===");
				} catch (Exception e) {
					System.out.println("=== Błąd podczas robienia zdjęcia: " + e.getMessage() + " ===");
				}
			} else {
				System.out.println("=== Błąd: Driver jest NULL, nie mogę zrobić zdjęcia! ===");
			}
		} else {
			System.out.println("=== Scenariusz UDANY. Brak potrzeby zdjęcia. ===");
		}

		DriverFactory.quitDriver();
	}
}