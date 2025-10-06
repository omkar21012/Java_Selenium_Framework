package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalDetailsPage extends BasePage {



private final By personalDetails= By.xpath("//a[normalize-space(text())='Personal Details']");



    public PersonalDetailsPage(WebDriver driver){
        super(driver);
    }

    public boolean isOnPersonalDetailsPage()
    {
        boolean value= wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetails)).isDisplayed();
        return value;
    }




}
