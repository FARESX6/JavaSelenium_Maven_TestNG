package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

import org.openqa.selenium.JavascriptExecutor;

public class Scrolling {
    private Scrolling() {
        // Private constructor to prevent instantiation
    }
    @Step("Scrolling to {locator}")
    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator); // Use driver directly to avoid recursion
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}