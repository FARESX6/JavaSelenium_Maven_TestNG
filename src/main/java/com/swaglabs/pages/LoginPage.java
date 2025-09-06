package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.PropertiesUtils;
import com.swaglabs.utils.Validations;
import com.swaglabs.utils.Waits;

import io.qameta.allure.Step;


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
    @Step("Navigate to the loginPage")
    public void navigateToLoginPage() {
        BrowserActions.NavigateToURL(driver, PropertiesUtils.getPropertyValue("baseURL"));
        Waits.waitForElementToBeVisible(driver,username);
    }

    // actions to be done > wait - scroll - click - find - sendkeys
    @Step("entering username")
    public LoginPage enterUsername(String username) {
        ElementActions.sendData(driver, this.username, username);
        return this;
    }
    @Step("entering password")
    public LoginPage enterPassword(String password) {
        LogsUtil.debug("Entering password");
        ElementActions.sendData(driver, this.password, password);
        return this;
    }
    
    @Step("clicking login button")
    public LoginPage clickLoginButton() {
        LogsUtil.debug("Clicking on login button");
        ElementActions.clickElement(driver, loginButton);
        return this;
    }
    @Step("getting error message")
    public String getErrorMessage() {

        return ElementActions.getText(driver, errorMessage);

    }

    // validations
    public LoginPage asserLoginPageURL() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver),PropertiesUtils.getPropertyValue("homeURL"), "URL is not matching");
        return this;
    }

    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver), PropertiesUtils.getPropertyValue("appTitle"),"Title is not matching");
        return this;
    }

    public LoginPage assertSuccessfulLoginSoft() {
        asserLoginPageURL().assertLoginPageTitle();
        return this;

    }

    public LoginPage assertSuccessfulLogin() {
        Validations.validatePageUrl(driver,PropertiesUtils.getPropertyValue("homeURL"));
        return this;

    }

    public LoginPage assertUnsuccessfulLogin() {
        Validations.validateEquals(getErrorMessage(), PropertiesUtils.getPropertyValue("errorMSG"), "Error message does not match");
        return this;
    }

}
