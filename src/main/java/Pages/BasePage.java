package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePage {

    protected WebDriver driver;
    protected FluentWait<WebDriver> waiter;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.waiter = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class, WebDriverException.class);
    }

    // ✅ Click method with wait
    public void click(By locator) {
        waiter.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // ✅ Type method
    public void type(By locator, String text) {
        WebElement element = waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // ✅ Get text
    public String getText(By locator) {
        return waiter.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // ✅ Check if element is visible
    public boolean isElementVisible(By locator) {
        try {
            return waiter.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ✅ Get current page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // ✅ Get current page URL
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    // ✅ Scroll into view
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
