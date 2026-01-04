package pl.softra.www.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
    @FindBy(xpath = "//a[normalize-space()='Kkontakt']")
    private WebElement contactTab;
    @FindBy(css = ".wb-accordion-wrapper > .wb-accordion-item:nth-child(4) .wb-accordion-item-title")
    private WebElement supportSectionWrapper;
    @FindBy(xpath = "//button[contains(., 'Akceptuj wszystko')]")
    private WebElement acceptCookiesBtn;


    public SoftraContactPage(final WebDriver driver) {
        this.driver = driver;
        // 2. Inicjalizujemy Wafera (czekaj max 10 sekund)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickContactTab() {
        wait.until(ExpectedConditions.elementToBeClickable(contactTab)).click();
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
        } catch (TimeoutException e) {
            System.out.println("Baner cookies się nie pojawił lub został już zamknięty.");
        }
    }

    public void expandSupportSection() {
        // Przeniesiona logika scrollowania (jeśli jest konieczna)
        // W Selenium 4 często wystarczy:
        // new Actions(driver).scrollToElement(emailSectionWrapper).perform();

        // Ale zostańmy przy Twoim JS, tylko ukrytym tutaj:
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", supportSectionWrapper);

        wait.until(ExpectedConditions.elementToBeClickable(supportSectionWrapper)).click();
    }

    public boolean isEmailSerwisDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(emailSerwis)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


