package com.swaglabs.Listeners;

import com.swaglabs.utils.*;
import org.testng.*;
import java.io.File;


public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    File allure_results = new File(System.getProperty("user.dir") + "/test-outputs/allure-results/");
    File logs = new File(System.getProperty("user.dir") +"/test-outputs/Logs");
    File screenshots = new File(System.getProperty("user.dir") + "/test-outputs/screenshots");
    File  allure_report=new File (System.getProperty("user.dir") + "/test-outputs/allure-report");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test Execution started");
        FilesUtils.deleteFiles(allure_results);
       // FilesUtils.deleteFiles(allure_report);
        FilesUtils.deleteFiles(logs);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(screenshots);
        FilesUtils.createDirectory(allure_results);
        FilesUtils.createDirectory(logs);
        FilesUtils.createDirectory(screenshots);
    }
       @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test Execution finished");
        AllureUtils.generateAllureReport();
        String reportName = "index.html";
        AllureUtils.openReport(reportName);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS ->
                        SreenshotsUtils.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE ->
                        SreenshotsUtils.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP ->
                        SreenshotsUtils.takeScreenshot("skipped-" + testResult.getName());
            }
            AllureUtils.attachLogToAllureReport();
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test case", result.getName(), "passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case", result.getName(), "failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case", result.getName(), "skipped");
    }

}
