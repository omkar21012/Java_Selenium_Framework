package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By homePage = By.cssSelector("div [class='oxd-layout orangehrm-upgrade-layout']");


    public boolean IsOnHomePage() {
        return isElementVisible(homePage);
    }
}