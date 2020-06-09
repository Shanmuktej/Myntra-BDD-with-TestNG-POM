package com.myntra.testrunner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.myntra.generics.GenericMethods;

import cucumber.api.CucumberOptions;

/**
 * @author Anem Shanmuka Chandra Teja
 */

@CucumberOptions(
		features = "src/main/java/com/myntra/features/orderingProduct.feature", 
		glue = { "com.myntra.stepDefinitions" }, 
		dryRun = false, 
		monochrome = true,  
		strict= true,
		plugin = { "pretty",
				"html:cucumber-reports/ordering-product",
				"rerun:target/cucumber-reports/rerun.txt" })
public class OrderingProduct extends GenericMethods {

	@BeforeTest
	public void openBrowser() throws Exception {

		if (config.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else if (config.equals("chrome")) {
			driver = new ChromeDriver();
		}
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}