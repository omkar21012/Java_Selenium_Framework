package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import Utils.DriverFactory;
import Utils.ScreenshotUtil;
import Utils.readJson;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected JSONObject config;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        // Clear screenshots once at the start
        ScreenshotUtil.clearScreenshotsFolder();
        System.out.println("✅ Cleared screenshots folder before test suite.");
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        // Load config from JSON (index comes from system property, default 0)
        String indexProp = System.getProperty("configIndex", "0");
        int index = Integer.parseInt(indexProp);
        config = readJson.getConfigByIndex(index);

        // Init driver & open base URL
        WebDriver driver = DriverFactory.initDriver(browser);
        driver.get(config.getString("baseUrl"));

        // Init page objects for this thread
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Login before each test method (parallel-safe)
        loginPage.Login(config.getString("username"), config.getString("password"));
        System.out.println("✅ Logged in on thread: " + Thread.currentThread().getId());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            homePage.Logout("Logout");
            System.out.println("✅ Logged out on thread: " + Thread.currentThread().getId());
        } catch (Exception e) {
            System.out.println("⚠️ Logout skipped or already logged out: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
            System.out.println("✅ Browser closed on thread: " + Thread.currentThread().getId());
        }
    }

    // Getter for current driver
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
