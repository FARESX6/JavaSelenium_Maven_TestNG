package com.swaglabs.utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        // private constructor to prevent instantiation
    }

    public static void NavigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

       // get current url
    
    public static String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }
        //get page title
        
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();}

        //refresh page

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

}
