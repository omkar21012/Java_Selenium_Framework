package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PimPage extends BasePage {

    private final By addButton = By.cssSelector("i[class='oxd-icon bi-plus oxd-button-icon']");

    @FindBy (css = "input[name='firstName']")
    WebElement firstName;

    @FindBy (css = "input[name='lastName']")
    WebElement lastName;

    @FindBy (css="input[name='middleName']")
    WebElement middleName;

    @FindBy(xpath = "//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div//input[@class='oxd-input oxd-input--active']")
    WebElement empID;

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAdd() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
    }

    public void  enterFirstName(String Name)
    {
        wait.until(ExpectedConditions.elementToBeClickable(firstName)).sendKeys(Name);
    }
    public void  enterLastName(String LastName)
    {

        wait.until(ExpectedConditions.elementToBeClickable(lastName)).sendKeys(LastName);

    }
    public void  enterMiddleName(String MiddleName)
    {
        wait.until(ExpectedConditions.elementToBeClickable(middleName)).sendKeys(MiddleName);
    }
    public void  enterEmpID(String id)
    {
        wait.until(ExpectedConditions.elementToBeClickable(empID)).sendKeys(id);
    }




}
