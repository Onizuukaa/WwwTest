package pl.softra.www.steps;

import io.cucumber.java.en.*;

import pl.softra.www.pages.SoftraContactPage;
import pl.softra.www.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class SoftraContactSteps {

    WebDriver driver = DriverFactory.getDriver(); // Pobierasz szofera
    private SoftraContactPage softraContactPage;

    @Given("user is on Softra home page")
    public void user_is_on_softra_home_page() {
        softraContactPage = new SoftraContactPage(driver);
        driver.navigate().to("https://www.softra.pl/");
    }

    @And("user accepts privacy prompt")
    public void user_accepts_privacy_prompt() {
        softraContactPage.acceptCookies();
    }

    @When("user click contact tab")
    public void user_click_contact_tab() {
        softraContactPage.clickContactTab();
    }

    @And("user expands support section")
    public void user_expands_support_section() {
        // Cała "brzydka" logika (JS, scroll) siedzi teraz w tej metodzie
        softraContactPage.expandSupportSection();
    }

    @Then("contact email should be visible")
    public void contactEmailShouldBeVisible() {
        assertTrue("Email serwisowy nie jest widoczny!", softraContactPage.isEmailSerwisDisplayed());
    }
}
