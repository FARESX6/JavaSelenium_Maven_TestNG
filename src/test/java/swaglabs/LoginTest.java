package swaglabs;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
//import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.BrowserActions;

public class LoginTest {

    // Tests

    /**
     * Verifies that a user can successfully login to the Swag Labs web application with a valid username and password.
     */
    @Test
    public void testValidLogin() {
        // this is a fluent pattern
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertSuccessfulLogin();
    }


    // Configuration

    @BeforeMethod
    public void setUp() {
        
        DriverManager.createInstance("chrome");
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();

    }

    /**
     * Tears down the test environment after each test. This includes closing the web
     * browser and driver.
     */
    @AfterMethod
    public void tearDown() {

        BrowserActions.closeBrowser(DriverManager.getDriver());
        // CustomSoftAssertion.CustomAssertAll(); // need to see the problem of the soft
        // assertin ASAP

    }

}
