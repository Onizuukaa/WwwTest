package pl.softra.www.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        String browser = ConfigReader.getProperty("browser").toLowerCase();

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
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                tempDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--headless=new");
                tempDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Invalid browser name in config file: " + browser);
        }

        tempDriver.manage().window().setSize(new Dimension(1920, 1080));
        // Poniższe zakomentowane dla headless. Powyższe lepiej zakomentować dla GUI.
        //tempDriver.manage().window().maximize();
        tempDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(globalWait));

        // Na końcu wkładamy gotowego drivera do "pudełka" ThreadLocal
        driver.set(tempDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Ważne: czyścimy pamięć wątku po zakończeniu
        }
    }
}