package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(listeners.TestListener.class)
public class SearchTest extends BaseTest {


    @Test(priority = 1)
    public void isOnDashBoad() {
        LoginPage loginPage = new LoginPage(getDriver());
        boolean value = loginPage.isDashboardVisible();
        Assert.assertTrue(value, "not present");
    }

    @Test(priority = 2)
    public void VerifyList() {
        HomePage homePage = new HomePage(getDriver());
        homePage.waitForSeconds(2);
        List<String> menuItems = homePage.getSearchOptions();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(menuItems.contains("PIM"), "PIM menu is missing!");
        softAssert.assertTrue(menuItems.contains("Admin"), "Admin menu is missing!");
        softAssert.assertTrue(menuItems.contains("Leave"), "Leave menu is missing!");
        softAssert.assertTrue(menuItems.contains("Time"), "Time menu is missing!");
        softAssert.assertTrue(menuItems.contains("Recruitment"), "Recruitment menu is missing!");
        softAssert.assertTrue(menuItems.contains("My Info"), "My Info menu is missing!");
        softAssert.assertTrue(menuItems.contains("Performance"), "Performance menu is missing!");
        softAssert.assertTrue(menuItems.contains("Dashboard"), "Dashboard menu is missing!");
        softAssert.assertTrue(menuItems.contains("Directory"), "Directory menu is missing!");
        softAssert.assertTrue(menuItems.contains("Maintenance"), "Maintenance menu is missing!");
        softAssert.assertTrue(menuItems.contains("Claim"), "Claim menu is missing!");
        softAssert.assertTrue(menuItems.contains("Buzz"), "Buzz menu is missing!");

        // Collects all assertion results
        softAssert.assertAll();
    }
}
