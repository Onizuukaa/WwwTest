package pl.softra.www.steps;

import io.cucumber.java.en.*;

import pl.softra.www.pages.SoftraContactPage;
import pl.softra.www.utils.ConfigReader;
import pl.softra.www.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoftraContactSteps {

    // Pobieramy instancję sterownika z naszej fabryki
    WebDriver driver = DriverFactory.getDriver();
    private SoftraContactPage softraContactPage;

    @Given("user is on Softra home page")
    public void userIsOnSoftraHomePage() {
        softraContactPage = new SoftraContactPage(driver);

        // Pobieramy adres URL z pliku konfiguracyjnego (zamiast wpisywać na sztywno)
        driver.navigate().to(ConfigReader.getProperty("app.url"));
        softraContactPage.acceptCookies();
    }

    @When("user clicks contact tab")
    public void userClicksContactTab() {
        softraContactPage.clickContactTab();
    }

    @And("user expands support section")
    public void userExpandsSupportSection() {
        softraContactPage.expandSupportSection();
    }

    @Then("contact email should be visible")
    public void contactEmailShouldBeVisible() {
        assertTrue(softraContactPage.isEmailSerwisDisplayed(),"Support email is not visible");
    }
}
