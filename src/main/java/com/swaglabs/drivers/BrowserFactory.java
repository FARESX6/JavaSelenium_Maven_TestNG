package com.swaglabs.drivers;

import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    public static WebDriver getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions   options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-info-bars");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-notifications");
                options.addArguments("--remote-allow-origins=*");
                Map<String, Object> prefs = Map.of("credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //options.addArguments("--headless");
                return new ChromeDriver(options);
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--start-maximized");   
                ffOptions.addArguments("--disable-info-bars");
                ffOptions.addArguments("--disable-extensions");
                ffOptions.addArguments("--disable-notifications");
                ffOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //ffOptions.addArguments("--headless");
                ffOptions.addArguments("--remote-allow-origins=*");
                ffOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                ffOptions.setAcceptInsecureCerts(true);
                return new FirefoxDriver(ffOptions);
            default:
                EdgeOptions  edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-info-bars");
                edgeOptions.addArguments("--disable-extensions");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--remote-allow-origins=*");
                Map<String, Object> edgePrefs = Map.of("credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
        }
    }

}
