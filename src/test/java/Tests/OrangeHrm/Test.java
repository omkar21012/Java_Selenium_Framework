package Tests.OrangeHrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.*;

public class Test {



    public static void main(String[]args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.findElement(By.xpath("")).click();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");



    }



}
