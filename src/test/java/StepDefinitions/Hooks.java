package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import service.driver.DriverFactory;


public class Hooks {

	@Before
	public void setUp() {
		// Inicjalizacja przed każdym scenariuszem
		DriverFactory.initializeDriver();
	}

	@After
	public void tearDown() {
		// Sprzątanie po każdym scenariuszu
		DriverFactory.quitDriver();
	}
}