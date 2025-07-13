package Tests;

import Pages.EbayHomePage;
import Pages.ProductDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

public class EbayCartCounter {
    private static String SEARCH_INPUT;


    static {
        SEARCH_INPUT = "BOOK";
    }

    WebDriver driver;
    EbayHomePage ebayHomePage;
    ProductDetailsPage productDetailsPage;


    public EbayCartCounter() {

        ebayHomePage = new EbayHomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
    }
    @Test(priority = 1)
    public void isSerachBoxEnabled()
    {
        boolean isEnabled=  ebayHomePage.isSearchBoxEnabled();
        Assert.assertTrue(isEnabled,"Search box is not enabled");

    }




    @Test
    public void cartCounterUpdate() {


        EbayHomePage ebayHomePage = new EbayHomePage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

        ebayHomePage.SearchText(SEARCH_INPUT);
        ebayHomePage.ClickOnSearchButton();
        String Result1 = productDetailsPage.GetAddToCartCount();

        ebayHomePage.SelectFirstResults(1);

        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(windowHandles);
        driver.switchTo().window(tabs.get(1));


        productDetailsPage.ClickOnaddcartButton();
        String Result2 = productDetailsPage.GetAddToCartCount();


        Assert.assertTrue(Result2.contains("1"), "cart is not updated");

    }

    @AfterClass
    public void after() {

        driver.quit();
    }

}
