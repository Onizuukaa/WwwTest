package pl.softra.www.steps;

import io.cucumber.java.en.*;

import pl.softra.www.pages.SoftraContactPage;
import pl.softra.www.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoftraContactSteps {

    WebDriver driver = DriverFactory.getDriver(); // Pobierasz szofera
    private SoftraContactPage softraContactPage;

    @Given("user is on Softra home page")
    public void userIsOnSoftraHomePage() {
        softraContactPage = new SoftraContactPage(driver);
        driver.navigate().to("https://www.softra.pl/");
        softraContactPage.acceptCookies();
    }

    @When("user click contact tab")
    public void userClickContactTab() {
        softraContactPage.clickContactTab();
    }

    @And("user expands support section")
    public void userExpandsSupportSection() {
        // Cała "brzydka" logika (JS, scroll) siedzi teraz w tej metodzie
        softraContactPage.expandSupportSection();
    }

    @Then("contact email should be visible")
    public void contactEmailShouldBeVisible() {
        assertTrue(softraContactPage.isEmailSerwisDisplayed(),"Email serwisowy nie jest widoczny!");
    }
}
