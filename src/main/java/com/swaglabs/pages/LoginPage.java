package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import com.swaglabs.utils.Waits;

public class LoginPage {

    // driver object
    private final WebDriver driver;

    // locators : if you gonna use this code for another project, you have to change
    // all locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // Constructor of the page class to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the login page
    public void navigateToLoginPage() {
        BrowserActions.NavigateToURL(driver, "https://www.saucedemo.com/");
        Waits.waitForElementToBeVisible(driver,username);
    }

    // actions to be done > wait - scroll - click - find - sendkeys
    public LoginPage enterUsername(String username) {
        ElementActions.sendData(driver, this.username, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        ElementActions.sendData(driver, this.password, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        ElementActions.clickElement(driver, loginButton);
        return this;
    }

    public String getErrorMessage() {

        return ElementActions.getText(driver, errorMessage);

    }

    // validations
    public LoginPage asserLoginPageURL() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver),"https://www.saucedemo.com/inventory.html", "URL is not matching");
        return this;
    }

    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver), "Swag Labs","Title is not matching");
        return this;
    }

    public LoginPage assertSuccessfulLoginSoft() {
        asserLoginPageURL().assertLoginPageTitle();
        return this;

    }

    public LoginPage assertSuccessfulLogin() {
        Validations.validatePageUrl(driver, "https://www.saucedemo.com/inventory.html");
        return this;

    }

    public LoginPage assertUnsuccessfulLogin(String expectedErrorMessage) {
        Validations.validateEquals(getErrorMessage(), expectedErrorMessage, "Error message does not match");
        return this;
    }

}
