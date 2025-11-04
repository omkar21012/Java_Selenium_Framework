package Tests.OrangeHrm;


import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.PersonalDetailsPage;
import Pages.OrangeHrm.PimPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateUserTest extends BaseTest {
    private PimPage pimPage;
    private PersonalDetailsPage personalDetails;
    private static String USER_NAME = "Omkar";
    private static String LAST_NAME = "Mule";
    private static String MID_NAME = "Datta";
    private static String EMP_ID = "1234";


    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(getDriver());
        pimPage = new PimPage(getDriver());
        personalDetails = new PersonalDetailsPage(getDriver());

    }


    @Test(priority = 1)
    public void CreateUser() {
        homePage.SearchInLeftPanel("PIM");
        pimPage.clickOnAdd();
        pimPage.enterFirstName(USER_NAME);
        pimPage.enterLastName(LAST_NAME);
        pimPage.enterMiddleName(MID_NAME);
        pimPage.enterEmpID(EMP_ID);
        pimPage.clickOnButtons("submit");

        Boolean value = personalDetails.isOnPersonalDetailsPage();

        Assert.assertTrue(value, "Not navigated to the personal details page");

    }


}
