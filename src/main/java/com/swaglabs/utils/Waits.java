package com.swaglabs.utils;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//we import this to do excpected condition import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
public class Waits {

    private Waits(){
        //private constructor to prevent instantiation
    }
// present - visible - clickable

//wait for element to be present

public static WebElement waitForElementToBePresent(WebDriver driver, By locator){

    return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> driver1.findElement(locator));
}
//wait for element to be visible
public static WebElement waitForElementToBeVisible(WebDriver driver, By locator){

    return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementToBePresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                
                });
}
//wait for element to be clickable
public static WebElement waitForElementToBeClickable(WebDriver driver, By locator){
        
    return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementToBeVisible(driver, locator);
                    return element.isEnabled() ? element : null;
                
                });
}

}
