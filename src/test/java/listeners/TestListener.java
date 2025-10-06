package listeners;

import Utils.DriverFactory;
import Utils.ExtentManager;
import Utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.driver();

        if (driver != null) {
            String testName = result.getMethod().getMethodName();
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);

            testThread.get().log(Status.FAIL, result.getThrowable());
            testThread.get().addScreenCaptureFromPath(screenshotPath);
        } else {
            testThread.get().log(Status.FAIL, "Driver is null, cannot capture screenshot.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped");
    }
}
