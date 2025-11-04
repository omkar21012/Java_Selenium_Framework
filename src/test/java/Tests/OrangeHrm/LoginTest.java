package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import Utils.dataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "loginData", dataProviderClass = dataProvider.class)
    public void validLoginTest(Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");
        homePage.Logout("Logout");

        loginPage.Login(username, password);

        boolean isLoggedIn = homePage.IsOnHomePage();
        softAssert.assertTrue(isLoggedIn, "Not logged in into app");
        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void invalidLoginTest() {

        homePage.Logout("Logout");
        loginPage.Login("omkar", "pass");
        boolean a = loginPage.isOnLoginPage();
        Assert.assertTrue(a, "Demo placeholder test");
    }
}
