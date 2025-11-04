package Tests.OrangeHrm;

import Pages.OrangeHrm.HomePage;
import Pages.OrangeHrm.LoginPage;
import Utils.readExcel;
import Utils.readJson;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.util.*;

public class TestRD extends ExcelRead {

    @BeforeMethod
    public void initPages() {


    }


    @Test(priority = 1)
    public void validateUserNameField() throws FileNotFoundException {

        readExcel excel = new readExcel("src/test/resources/TestData.xlsx");
        readJson rjson = new readJson();
        String a = rjson.getValueByKey("username");
        System.out.println(a);

        String username = excel.getValue("username");
        System.out.println(username);


    }


//    @Test(priority = 2)
//    public void test() {
//
//        int []a= {1,3,4};
//        int []b={5,6,7};
//
//        int []murge={a.length+b.length};
//
//        for(int i:a)
//        {
//            murge[i]=a[i];
//
//        }
//
//        for(int j:b)
//        {}







    //}




        }



























