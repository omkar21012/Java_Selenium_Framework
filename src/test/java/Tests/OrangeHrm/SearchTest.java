package Tests.OrangeHrm;

import Pages.OrangeHrm.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestListener.class)
public class SearchTest extends BaseTest{

@Test
    public void isdashboad()
    {
        LoginPage loginPage= new LoginPage(getDriver());
      boolean value=   loginPage.isDashboardVisible();
        Assert.assertTrue(value,"not presenrt");

    }









}
