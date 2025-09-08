package com.swaglabs.pages;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //locators
    
    //variables
    private WebDriver driver;

    //constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //actions

    @Step("Add specific product to cart")
    public HomePage addSpecificProductToCart(String productName) {
        //this function is using relative Locator
        LogsUtil.info("Adding " + productName + " to cart");
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        ElementActions.clickElement(driver, addToCartButton);
        return this;
    }

   
}
