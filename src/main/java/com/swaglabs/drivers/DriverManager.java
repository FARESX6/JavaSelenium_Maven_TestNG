package com.swaglabs.drivers;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.LogsUtil;

import static org.testng.Assert.fail;
public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }
   
    public static WebDriver createInstance(String BrowserName) {

        WebDriver driver = BrowserFactory.getBrowser(BrowserName);
        LogsUtil.info("Driver created is:", BrowserName);
        setDriver(driver);
        return getDriver();
    }
   
   
    public static WebDriver getDriver() {
        if(driverThreadLocal.get() == null) {
            fail("Driver is null.");
        }
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

}
