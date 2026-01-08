package pl.softra.www.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {

    // ThreadLocal pozwala na bezpieczne uruchamianie testów równolegle w przyszłości
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    public static void initializeDriver() {
        // 1. Pobieramy nazwę przeglądarki z pliku config
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        // 2. Pobieramy czas oczekiwania i zamieniamy tekst na liczbę (int)
        int globalWait = Integer.parseInt(ConfigReader.getProperty("implicit.wait"));

        // Zmienna tymczasowa, żebyśmy mogli na niej operować przed włożeniem do ThreadLocal
        WebDriver tempDriver;

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--headless=new");
                tempDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                tempDriver = new FirefoxDriver();
                break;

            case "edge":
                tempDriver = new EdgeDriver();
                break;

            default:
                // Jeśli ktoś wpisze bzdury w configu, rzucamy wyjątkiem
                throw new RuntimeException("Nieprawidłowa przeglądarka w configu: " + browser);
        }

        // --- Wspólna konfiguracja ---
        // Teraz operujemy na tym jednym, konkretnym driverze utworzonym wyżej
        // Używamy Twojego pomysłu z setSize - jest lepszy dla CI/CD i headless!
        tempDriver.manage().window().setSize(new Dimension(1920, 1080));
        tempDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(globalWait));

        // Na końcu wkładamy gotowego drivera do "pudełka" ThreadLocal
        driver.set(tempDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Ważne przy ThreadLocal, żeby czyścić pamięć wątku
        }
    }
}