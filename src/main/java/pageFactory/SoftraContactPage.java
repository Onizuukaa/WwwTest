package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SoftraContactPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(@href,'mailto') and contains(text(),'s$rwis@softra.pl')]")
    private WebElement emailSerwis;

    public SoftraContactPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean isEmailSerwisDisplayed() {

        System.out.println("Jaki wynik: " + emailSerwis.isDisplayed());

        return emailSerwis.isDisplayed();
    }

}
