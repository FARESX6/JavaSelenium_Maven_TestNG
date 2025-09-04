package com.swaglabs.utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    //thid class is for hard assertions

    private Validations() {
        // private constructor to prevent instantiation
    }

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition);
    }

    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public static void validateEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected);
    }

    public static void validateNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void validateNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    public static void validatePageUrl(WebDriver driver, String expectedUrl) {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expectedUrl);
    }

    public static void validatePageTitle(WebDriver driver, String expectedTitle) {
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expectedTitle);
    }

}
