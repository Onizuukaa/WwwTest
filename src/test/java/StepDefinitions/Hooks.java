package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import service.driver.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

	@Before
	public void setUp() {
		DriverFactory.initializeDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			WebDriver driver = DriverFactory.getDriver();
			if (driver != null) {
				// 1. Nazwa pliku (usuwamy spacje, żeby system plików nie marudził)
				String screenshotName = scenario.getName().replaceAll(" ", "_");

				// 2. Robimy zdjęcie
				byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				try {
					// 3. Tworzymy folder 'zrzuty' w folderze projektu, jeśli nie istnieje
					Path path = Paths.get("build/zrzuty");
					if (!Files.exists(path)) {
						Files.createDirectories(path);
					}

					// 4. Zapisujemy plik na dysku
					Files.write(path.resolve(screenshotName + ".png"), sourcePath);

					// 5. Dołączamy też do raportu (nie zaszkodzi)
					scenario.attach(sourcePath, "image/png", "Zrzut_ekranu");

				} catch (IOException e) {
					System.out.println("Nie udało się zapisać zdjęcia: " + e.getMessage());
				}
			}
		}
		DriverFactory.quitDriver();
	}
}