package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser, boolean headless) {
        WebDriver localDriver;
        String runMode = System.getProperty("runMode", "local"); // default = local
        String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");

        try {
            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("--headless=new");
                    firefoxOptions.addArguments("--no-sandbox");
                    firefoxOptions.addArguments("--disable-dev-shm-usage");

                    if (runMode.equalsIgnoreCase("grid")) {
                        localDriver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                        System.out.println("✅ Running Firefox on Docker Grid");
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        localDriver = new FirefoxDriver(firefoxOptions);
                        System.out.println("✅ Running Firefox locally");
                    }
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");

                    if (runMode.equalsIgnoreCase("grid")) {
                        localDriver = new RemoteWebDriver(new URL(gridUrl), edgeOptions);
                        System.out.println("✅ Running Edge on Docker Grid");
                    } else {
                        WebDriverManager.edgedriver().setup();
                        localDriver = new EdgeDriver(edgeOptions);
                        System.out.println("✅ Running Edge locally");
                    }
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--window-size=1920,1080");

                    if (headless) chromeOptions.addArguments("--headless=new");

                    if (runMode.equalsIgnoreCase("grid")) {
                        localDriver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                        System.out.println("✅ Running Chrome on Docker Grid");
                    } else {
                        WebDriverManager.chromedriver().setup();
                        localDriver = new ChromeDriver(chromeOptions);
                        System.out.println("✅ Running Chrome locally");
                    }
                    break;
            }

            // Maximize window if possible
            try {
                localDriver.manage().window().maximize();
            } catch (Exception e) {
                System.out.println("⚠️ Could not maximize window (likely headless mode): " + e.getMessage());
            }

            driver.set(localDriver);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to start WebDriver: " + e.getMessage(), e);
        }

        return driver();
    }

    public static WebDriver driver() {
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver localDriver = driver.get();
        if (localDriver != null) {
            localDriver.quit();
            driver.remove();
        }
    }
}
