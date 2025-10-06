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
        ScreenshotUtil.clearScreenshotsFolder();
        System.out.println("✅ Cleared screenshots folder before test suite.");
    }

    @Parameters({"browser", "headless"})
    @BeforeMethod(alwaysRun = true) // ✅ one browser per test method
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false") String headless) {

        boolean isHeadless = Boolean.parseBoolean(headless);

        // Load config JSON
        String indexProp = System.getProperty("configIndex", "0");
        int index = Integer.parseInt(indexProp);
        config = readJson.getConfigByIndex(index);

        // Initialize driver for this thread
        WebDriver driver = DriverFactory.initDriver(browser, isHeadless);

        // Open base URL
        driver.get(config.getString("baseUrl"));

        // Initialize page objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Login before each test method
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

    public WebDriver getDriver() {
        return DriverFactory.driver();
    }
}
