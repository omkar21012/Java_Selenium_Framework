package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestRD extends BaseTest{

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }


    @Test(priority = 1)
    public void validateUserNameField() {
        homePage.search("PIM");
        List<String> menuItems= homePage.getSearchOptions();

        Assert.assertTrue(menuItems.contains("PIMo"),"Test case failed");

    }









}
