package com.swaglabs.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import io.qameta.allure.Allure;

public class AllureUtils {
    private AllureUtils() {
        // Private constructor to prevent instantiation
    }
    public static final String ALLURE_RESULTS_DIR = System.getProperty("user.dir") + "/test-outputs/allure-results/";

    public static void attachLogToAllureReport(){
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_DIR);
            if (!logFile.exists()) {
                LogsUtil.warn("Log file does not exist: " + LogsUtil.LOGS_DIR);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }



    }
       public static void attachScreenshotToAllureReport(String screenshotName , String screenshotPath){
        try {
           Allure.addAttachment(screenshotName,Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    
    }

}
