package pl.softra.www.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.softra.www.utils.ConfigReader;

import java.time.Duration;

public class SoftraContactPage {

    private static final Logger logger = LoggerFactory.getLogger(SoftraContactPage.class);

    private final WebDriver driver;
    private final WebDriverWait wait; // 1. Deklarujemy Wafera

    @FindBy(xpath = "//a[contains(@href,'mailto') and contains(text(),'sserwis@softra.pl')]")
    private WebElement emailSerwis;
    @FindBy(xpath = "//a[normalize-space()='Kontakt']")
    private WebElement contactTab;
    @FindBy(css = ".wb-accordion-wrapper > .wb-accordion-item:nth-child(4) .wb-accordion-item-title")
    private WebElement supportSectionWrapper;
    @FindBy(xpath = "//button[contains(., 'Akceptuj wszystko')]")
    private WebElement acceptCookiesBtn;


    public SoftraContactPage(final WebDriver driver) {
        this.driver = driver;
        int waitTime = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        PageFactory.initElements(driver, this);
    }

    public void clickContactTab() {
        wait.until(ExpectedConditions.elementToBeClickable(contactTab)).click();
        logger.info("Clicked on 'Contact' tab");
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
            logger.info("Cookies accepted");
        } catch (TimeoutException e) {
            logger.info("Cookies banner did not appear or was already closed");
        }
    }

    public void expandSupportSection() {
        new Actions(driver).scrollToElement(supportSectionWrapper).perform();
        wait.until(ExpectedConditions.elementToBeClickable(supportSectionWrapper)).click();
        logger.info("Support section expanded");
    }

    public boolean isEmailSerwisDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(emailSerwis)).isDisplayed();
        } catch (Exception e) {
            logger.warn("Support email element not found: {}", e.getMessage());
            return false;
        }
    }
}


