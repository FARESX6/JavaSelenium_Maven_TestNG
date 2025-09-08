package com.swaglabs.utils;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.swaglabs.drivers.DriverManager;



public class SreenshotsUtils {
    private SreenshotsUtils() {
        // Private constructor to prevent instantiation
    }

    public static final String SCREENSHOTS_DIR = System.getProperty("user.dir") + "/test-outputs/Screenshots/";
    public static void takeScreenshot(String screenshotName){


        try {   
        File screenshot = ((org.openqa.selenium.TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(SCREENSHOTS_DIR+ screenshotName + ".png");
        FileUtils.copyFile(screenshot, screenshotFile);
        AllureUtils.attachScreenshotToAllureReport(screenshotName, screenshotFile.getPath());
        } 
        
        catch (Exception e) {
            LogsUtil.error("Failed to take screenshot: " + e.getMessage());
        }
   }

}
