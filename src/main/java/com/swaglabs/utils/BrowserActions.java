package com.swaglabs.utils;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class BrowserActions {

    private BrowserActions() {
        // private constructor to prevent instantiation
    }
    @Step("Navigating to {url}")
    public static void NavigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }
    
       // get current url
    @Step("getting current url {url}")
    public static String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }
        //get page title
    @Step("getting page title")
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();}

        //refresh page
    @Step("refreshing page")
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }
    
    public static void closeBrowser(WebDriver driver) {
        
        driver.quit();
        LogsUtil.info("Browser Closed");
    }

}
