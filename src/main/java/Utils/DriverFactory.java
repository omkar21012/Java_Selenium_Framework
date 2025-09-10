package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // Thread-safe WebDriver (each test thread gets its own instance)
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver for the given browser.
     * Each thread gets its own driver instance.
     */
    public static WebDriver initDriver(String browser) {
        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
                break;
        }

        localDriver.manage().window().maximize();
        driver.set(localDriver); // bind driver to current thread
        return getDriver();
    }

    /**
     * Get current thread’s WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit and remove WebDriver for the current thread.
     */
    public static void quitDriver() {
        WebDriver localDriver = driver.get();
        if (localDriver != null) {
            localDriver.quit();
            driver.remove(); // avoid memory leaks
        }
    }
}
