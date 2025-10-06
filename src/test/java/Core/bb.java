package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class bb extends aa{

    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

// Locate the "Login" button or text
//        WebElement text = driver.findElement(By.xpath("//*[@placeholder='Username']"));
//        WebElement textpass = driver.findElement(By.xpath("//*[@placeholder='Password']"));
//
//        text.sendKeys("omkar");
//
//        Actions ac = new Actions(driver);
//
//        //ac.contextClick(text).perform();
//        ac.doubleClick();
//        text.sendKeys(Keys.CONTROL,"a");
//        text.sendKeys(Keys.CONTROL,"c");
//        textpass.sendKeys(Keys.CONTROL,"v");

        TakesScreenshot ts = (TakesScreenshot)driver;

        File source= ts.getScreenshotAs(OutputType.FILE);


        String projectPath = System.getProperty("user.dir");
        File folder = new File(projectPath + "/screenshots/avxc.png");



        FileUtils.copyFile(source,folder );






    }

    }



