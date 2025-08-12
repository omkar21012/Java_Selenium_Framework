package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(listeners.TestListener.class)
public class Login extends BaseTest {

    @Test
    public void loginTestValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
         HomePage homePage= new HomePage(driver);
        loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
         boolean o = homePage.IsOnHomePage();
        Assert.assertTrue(o,"Not logged in into app");
    }
}
