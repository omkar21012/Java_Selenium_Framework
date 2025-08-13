package Tests.OrangeHrm;

import Utils.ConfigReader;
import Utils.DriverFactory;
import Utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties prop;
    @BeforeSuite
    public void cleanScreenshotsBeforeSuite() {
        ScreenshotUtil.clearScreenshotsFolder();
        System.out.println("Cleared screenshots folder before test suite.");
    }
    @Parameters("browser")
    @BeforeClass
    public void setUp() {
        prop = ConfigReader.initProperties();
        driver = DriverFactory.initDriver(prop.getProperty("browser"));
        driver.get(prop.getProperty("baseUrl"));
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
