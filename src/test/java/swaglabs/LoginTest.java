package swaglabs;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
//import com.swaglabs.utils.CustomSoftAssertion;

public class LoginTest {

    // Tests

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
        // Initialize WebDriver
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-maximized");
        //options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //driver = new ChromeDriver(options);
        // loginPage = new LoginPage(driver);
        // loginPage.navigateToLoginPage();
        // to navigate to the login page we are gonna use anonymos object
        DriverManager.createInstance("chrome");
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();

    }

    @AfterMethod
    public void tearDown() {
    
             DriverManager.getDriver().quit();
            // CustomSoftAssertion.CustomAssertAll(); // need to see the problem of the soft
            // assertin ASAP
       
    }

}
