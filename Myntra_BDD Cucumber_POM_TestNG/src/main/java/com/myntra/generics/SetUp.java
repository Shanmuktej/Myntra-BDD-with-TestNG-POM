package com.myntra.generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author Anem Shanmuka Chandra Teja
 */
public class SetUp extends AbstractTestNGCucumberTests{

	public static String config = "firefox";
	public static String parameters = "./src/main/resources/parameters.xlsx"; // Parameters WorkBook path
	public static String appDetails = "URL & Login details"; // SheetName where Parameters exists
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions action;

	static {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
	}
}
