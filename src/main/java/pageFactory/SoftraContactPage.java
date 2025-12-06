package pageFactory;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SoftraContactPage {

    private final WebDriver driver;
    private final WebDriverWait wait; // 1. Deklarujemy Wafera


    @FindBy(xpath = "//a[contains(@href,'mailto') and contains(text(),'serwis@softra.pl')]")
    private WebElement emailSerwis;
    @FindBy(xpath = "//a[normalize-space()='Kontakt']")
    private WebElement contactTab;


    public SoftraContactPage(final WebDriver driver) {
        this.driver = driver;
        // 2. Inicjalizujemy Wafera (czekaj max 10 sekund)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickContactTab() {
        // 3. NAJLEPSZY STANDARD: Czekamy aż element będzie klikalny
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(contactTab));

        // Klikamy JavaScriptem (bo wiemy, że w Headless to pomaga)
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public boolean isEmailSerwisDisplayed() {
        try {
            // Czekamy aż będzie widoczny (nie tylko obecny w kodzie, ale widoczny dla oka)
            wait.until(ExpectedConditions.visibilityOf(emailSerwis));
            System.out.println("Jaki wynik: "+emailSerwis.isDisplayed());
            return emailSerwis.isDisplayed();
        } catch (Exception e) {
            System.out.println("Jaki wynik: "+emailSerwis.isDisplayed());
            return false;
        }
    }
}


