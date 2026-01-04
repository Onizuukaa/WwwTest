package pl.softra.www.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    // ThreadLocal pozwala na bezpieczne uruchamianie testów równolegle w przyszłości
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Domyślnie uruchamiamy Chrome, możesz to zmienić lub pobierać z configu
            initializeDriver();
        }
        return driver.get();
    }

    public static void initializeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--headless=new"); // Opcjonalnie
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}