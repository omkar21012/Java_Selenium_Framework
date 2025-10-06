package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By homePage = By.cssSelector("div [class='oxd-layout orangehrm-upgrade-layout']");
    private final By profileIcon = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private final By searchField = By.cssSelector("input[placeholder='Search']");
    private final By searchResults = By.xpath("//a[@class='oxd-main-menu-item']");


    public boolean IsOnHomePage() {
        return isElementVisible(homePage);
    }

    public void clickOnProfileIcon() {
        click(profileIcon);
    }

    public void Logout(String text) {
        click(profileIcon);
        WebElement options = driver.findElement(By.xpath("//a[normalize-space(text())='" + text + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(options)).click();
    }

    public void SearchInLeftPanel(String value) {

        wait.until(ExpectedConditions.elementToBeClickable(searchField)).sendKeys(value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults)).click();

    }

    // In HomePage
    public List<String> getSearchOptions() {
        return driver.findElements(
                        By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
                ).stream()
                .map(WebElement::getText)
                .toList();
    }


    public void  search(String value)
    {
      WebElement sear=  driver.findElement(By.cssSelector("input[placeholder='Search']"));
      sear.sendKeys(value);
    }



}