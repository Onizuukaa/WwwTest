package pl.softra.www.steps;

import org.openqa.selenium.*;
import io.cucumber.java.en.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.softra.www.pages.SoftraContactPage;
import pl.softra.www.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class SoftraContactSteps {

    WebDriver driver = DriverFactory.getDriver(); // Pobierasz szofera
    private SoftraContactPage softraContactPage;

    @Given("user is on Softra home page")
    public void user_is_on_google_search_page() {
        softraContactPage = new SoftraContactPage(driver);
        driver.navigate().to("https://www.softra.pl/");
    }

    @And("user accepts privacy prompt")
    public void user_accepts_privacy_prompt() {
        // Użyj lokalizatora dla przycisku "Zaakceptuj wszystko"
        String acceptButtonXPath = "//button[contains(., 'Akceptuj wszystko')]";

        // Użyj WebDriverWait, aby poczekać, aż baner się załaduje
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptButtonXPath))).click();
    }

    @When("user click contact tab")
    public void user_click_contact_tab() {
        softraContactPage.clickContactTab();
    }

    @And("check contact email")
    public void checkContactEmail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = String.format("window.scrollBy(0, 200)");
        js.executeScript(script);

        driver.findElement(By.xpath("//*[@id='page-wrapper']/div[1]/div/div/div[1]/div/div[3]/div/div[4]/div[1]")).click();
    }

    @Then("contact email should be visible")
    public void contactEmailShouldBeVisible() {
        assertTrue(softraContactPage.isEmailSerwisDisplayed());
    }

}
