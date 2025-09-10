package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By homePage = By.cssSelector("div [class='oxd-layout orangehrm-upgrade-layout']");
    private final By profileIcon= By.xpath("//span[@class='oxd-userdropdown-tab']");

    public boolean IsOnHomePage() {
        return isElementVisible(homePage);
    }
    public void clickOnProfileIcon()
    {
        click(profileIcon);
    }

    public void Logout(String text)
    {
        click(profileIcon);
       WebElement options= driver.findElement(By.xpath("//a[normalize-space(text())='"+text+"']"));
        waiter.until(ExpectedConditions.elementToBeClickable(options)).click();
    }


}