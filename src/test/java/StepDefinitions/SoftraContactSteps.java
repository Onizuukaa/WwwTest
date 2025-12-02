package StepDefinitions;

import org.openqa.selenium.*;

import io.cucumber.java.en.*;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageFactory.SoftraContactPage;
import service.driver.DriverFactory;

import static org.junit.Assert.assertTrue;

public class SoftraContactSteps {

	WebDriver driver = DriverFactory.getDriver(); // Pobierasz szofera
	private SoftraContactPage softraContactPage;

	@Given("browser window is open")
	public void browser_is_open() {

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
	}

	@And("user is on google search page")
	public void user_is_on_google_search_page() {

		
		driver.navigate().to("https://www.softra.pl/");
		softraContactPage = new SoftraContactPage(driver);
	}

	// W StepDefinitions/GoogleSearchSteps.java

	@And("user accepts privacy prompt")
	public void user_accepts_privacy_prompt() {

		// Użyj lokalizatora dla przycisku "Zaakceptuj wszystko"
		String acceptButtonXPath = "//button[contains(., 'Akceptuj wszystko')]";

		// Użyj WebDriverWait, aby poczekać, aż baner się załaduje
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptButtonXPath))).click();

		// Opcjonalnie: Poczekaj chwilę na zniknięcie baneru
		// try { Thread.sleep(1000); } catch (InterruptedException e) {}
	}

	@When("user click contact tab")

	public void user_click_contact_tab() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Kontakt']")).click();
		Thread.sleep(2000);
	}

	@And("check contact email")
	public void checkContactEmail() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = String.format("window.scrollBy(0, 200)");
		js.executeScript(script);

		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[1]/div/div/div[1]/div/div[3]/div/div[4]/div[1]")).click();
		Thread.sleep(2000);
	}

	@Then("contact email should be visible")
	public void contactEmailShouldBeVisible() {
		assertTrue(softraContactPage.isEmailSerwisDisplayed());
	}

}
