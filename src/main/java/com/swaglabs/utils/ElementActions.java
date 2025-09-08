package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;


public class ElementActions {
    private ElementActions() {
        // Private constructor to prevent instantiation
    }
    @Step("Sending {data} to {locator}")
    public static void sendData(WebDriver driver, By locator, String Data) {
        // I need to wait for element to be visible
        // then i need to scroll to the element
        // then i send data to the element
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(Data);    
        LogsUtil.info("Data Entered is:", Data);

    }
    @Step("Clicking {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        // I need to wait for element to be visible
        // then i need to scroll to the element
        // then i click the element
        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver ,locator).click();
        LogsUtil.info("Element Clicked is:", locator.toString());

    }
    @Step("Getting Text From {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtil.info("Getting Element Text From:", locator.toString(), " Text is:", findElement(driver, locator).getText());
        return findElement(driver, locator).getText();
        
      
    }

    @Step("Finding {locator}")
    public static WebElement findElement(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtil.info("Element Found is:", locator.toString());
        return driver.findElement(locator);
        
    }

}
