package Core;

import Tests.OrangeHrm.SearchTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class aa {


public static void main(String[] args) throws IOException {
    WebDriverManager.chromedriver().setup();

    WebDriver driver =new ChromeDriver();


    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    driver.switchTo().newWindow(WindowType.TAB);

    driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");

    //driver.findElement(By.cssSelector("[class='orangehrm-login-forgot']")).click();
    driver.switchTo().newWindow(WindowType.TAB);
    Set<String> all_handles= driver.getWindowHandles();
    System.out.println(all_handles);



    ArrayList<String> number_window= new ArrayList<>(all_handles);

    driver.switchTo().window(number_window.get(1));

    driver.findElement(By.cssSelector("[class='oxd-input oxd-input--active']")).sendKeys("omkar");









    System.out.println("aaaa");

   }

}
