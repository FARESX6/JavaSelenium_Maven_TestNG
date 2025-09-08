package com.swaglabs.drivers;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.PropertiesUtils;

import io.qameta.allure.Step;

import static org.testng.Assert.fail;
public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }
    @Step("Create Driver Instance on {browserName}")

    public static WebDriver createInstance() {
    String BrowserName = PropertiesUtils.getPropertyValue("browserType");
        WebDriver driver = BrowserFactory.getBrowser(BrowserName);
        LogsUtil.info("Driver created is:", BrowserName);
        setDriver(driver);
        return getDriver();
    }
   
   @Step("get driver instance")
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
