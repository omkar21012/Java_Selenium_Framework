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

    /**
     * Clear screenshots folder once before the entire test suite.
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ScreenshotUtil.clearScreenshotsFolder();
        System.out.println("✅ Cleared screenshots folder before test suite.");
    }

    /**
     * Set up WebDriver before each test method.
     * Supports headless mode via TestNG parameter.
     *
     * @param browser  Browser type (chrome, firefox, edge)
     * @param headless true to run in headless mode
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false") String headless) {

        // Convert headless string to boolean
        boolean isHeadless = Boolean.parseBoolean(headless);

        // Load config from JSON (configIndex can be passed as system property)
        String indexProp = System.getProperty("configIndex", "0");
        int index = Integer.parseInt(indexProp);
        config = readJson.getConfigByIndex(index);

        // Initialize WebDriver for this thread
        WebDriver driver = DriverFactory.initDriver(browser, isHeadless);

        // Open the base URL
        driver.get(config.getString("baseUrl"));

        // Initialize page objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Login before each test
        loginPage.Login(config.getString("username"), config.getString("password"));
        System.out.println("✅ Logged in on thread: " + Thread.currentThread().getId());
    }

    /**
     * Tear down WebDriver after each test method.
     */
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

    /**
     * Getter for current thread's WebDriver instance.
     */
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
