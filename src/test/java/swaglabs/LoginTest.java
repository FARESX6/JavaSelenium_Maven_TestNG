package swaglabs;

import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
//import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.FilesUtils;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.SreenshotsUtils;


@Listeners(TestNGListeners.class)
public class LoginTest {

    JsonUtils testData;

    // Tests
    @Test
    public void testValidLogin() {
        
        new LoginPage(DriverManager.getDriver()).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin();
        SreenshotsUtils.takeScreenshot("SuccesfullLogin");
    }


    // Configuration
    @BeforeSuite
    public void beforeSuite() {
        FilesUtils.deleteFiles(new File(System.getProperty("user.dir") + "/test-outputs/allure-results"));
        testData = new JsonUtils("test-data");
    }

    @BeforeMethod
    public void setUp() {
               
        DriverManager.createInstance();
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();

    }

 
    @AfterMethod
    public void tearDown() {
       
        BrowserActions.closeBrowser(DriverManager.getDriver());
        // CustomSoftAssertion.CustomAssertAll(); // need to see the problem of the soft
        // assertin ASAP

    }

}

