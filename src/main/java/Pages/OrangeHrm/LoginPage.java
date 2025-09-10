package Pages.OrangeHrm;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage {


    private final By userID = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@type='password']");
    private final By login_button = By.xpath("//button[@type='submit']");
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void Login(String username,String pass)
    {
       //click(userID);
       type(userID,username);
       type(password,pass);
        click(login_button);
    }
    public boolean isDashboardVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }















}


