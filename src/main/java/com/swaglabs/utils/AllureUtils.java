package com.swaglabs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import io.qameta.allure.Allure;

public class AllureUtils {
    private AllureUtils() {
        // Private constructor to prevent instantiation
    }
   
   
    public static final String ALLURE_RESULTS_DIR = System.getProperty("user.dir") + "/test-outputs/allure-results/";
    static String REPORT_PATH = "test-outputs/allure-report";
     /* 
    public static void generateAllureReport() {
        //allure, generate, //path ,-o ,//path ,--single-file
        if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
        {
            TerminalUtils.executeCommand("allure.bat", "generate", "--single-file", ALLURE_RESULTS_DIR, "--clean", "-o", REPORT_PATH);
            LogsUtil.info("Allure report generated successfully on Windows");
        } else {
            TerminalUtils.executeCommand("allure", "generate", "--single-file", ALLURE_RESULTS_DIR, "--clean", "-o", REPORT_PATH);
            LogsUtil.info("Allure report generated successfully on " + PropertiesUtils.getPropertyValue("os.name"));
        }


    }*/
    public static void generateSingleFileReport() {
        try {
            String resultsDir = "test-outputs/allure-results";
            String reportDir = "test-outputs/allure-report";

            String[] command;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                // Windows : via PowerShell
                command = new String[]{
                        "powershell.exe", "-Command",
                        "allure generate " + resultsDir + " -o " + reportDir + " --clean --single-file"
                };
            } else {
                // Linux / Mac
                command = new String[]{
                        "allure", "generate", resultsDir,
                        "-o", reportDir, "--clean", "--single-file"
                };
            }

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(true);

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LogsUtil.info(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                LogsUtil.info("✅ Allure single-file report generated successfully in: " + reportDir);
            } else {
                LogsUtil.error("❌ Failed to generate Allure report. Exit code: " + exitCode);
            }

        } catch (Exception e) {
            LogsUtil.error("❌ Error while generating Allure report: " + e.getMessage());
        }
    }
     
      public static void generateAllureReport() {
        //allure, generate, //path ,-o ,//path ,--single-file
            TerminalUtils.executeCommand("allure", "generate", "--single-file", ALLURE_RESULTS_DIR, "--clean", "-o", REPORT_PATH);
            LogsUtil.info("Allure report generated successfully on Windows");
    }
/* 
    public static String renameReport() {
        File newName = new File("Report_" + TimestampUtils.getTimestamp() + ".html");
        File oldName = new File(REPORT_PATH + File.separator + ".html");
        FilesUtils.renameFile(oldName, newName);
        return newName.getName();
    }
*/
/* 
    public static void openReport(String fileName) {
        //allure serve //path
        String reportPath = REPORT_PATH + File.separator + fileName;
        if (PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")) {
            if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
            {
                TerminalUtils.executeCommand("cmd.exe", "/c", "start", reportPath);
            } else //linux
            {
                TerminalUtils.executeCommand("open", reportPath);
            }
        }
    }

*/


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
