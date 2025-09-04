package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private ElementActions() {
        // Private constructor to prevent instantiation
    }

    public static void sendData(WebDriver driver, By locator, String Data) {
        // I need to wait for element to be visible
        // then i need to scroll to the element
        // then i send data to the element
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(Data);

    }

    public static void clickElement(WebDriver driver, By locator) {
        // I need to wait for element to be visible
        // then i need to scroll to the element
        // then i click the element
        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver ,locator).click();

    }

    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return findElement(driver, locator).getText();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator);
    }

}
