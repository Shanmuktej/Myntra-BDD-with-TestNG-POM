package com.myntra.pageObjects;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.myntra.generics.GenericMethods;

import cucumber.api.Scenario;

/**
 * @author Anem Shanmuka Chandra Teja
 */
public class Profile extends GenericMethods {


	public Profile(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.profileEdit-formElement:nth-child(2) > div:nth-child(1)" + " > input:nth-child(1)")
	private WebElement email;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement existingEmail;

	@FindBy(xpath = "//tr/td[.='Email Id']/following-sibling::td")
	private WebElement newEmail;

	@FindBy(className = "profile-editButton")
	private WebElement editButton;

	@FindBy(css = "button.subcomponents-btn:nth-child(8)")
	private WebElement saveDetails;

	String logInEmailID;
	String existingEmailID;
	String newEmailID;

	private SoftAssert softAssert = new SoftAssert();

	public void validateProfilePage() {
		waitFor(email);
		pageToLoad("Profile");
	}

	public void validateLogINEmailID() throws EncryptedDocumentException, IOException {
		logInEmailID = getExcelData(parameters, appDetails, 1, 0);
		existingEmailID = existingEmail.getAttribute("value");
		softAssert.assertEquals(existingEmailID, logInEmailID, "Existing Email is not same as LogIN Email ID");
	}

	public void changeEmailId() throws InterruptedException, EncryptedDocumentException, IOException {
		newEmailID = getExcelData(parameters, appDetails, 9, 0);
		type(email, newEmailID);
		click(saveDetails);
		waitInSeconds(3);
	}

	public void checkForVerificationEmail() throws Exception {
		try {
			boolean flag = newEmail.isDisplayed() != true;
			softAssert.assertTrue(flag, "Portal is not making verification");
		} catch (Exception e) {
			Assert.fail("Portal displays \"Something Went Wrong!\" alert");
		}
	}

	public void validateEmailID() {
		newEmailID = newEmail.getText();
		softAssert.assertEquals(newEmailID, logInEmailID, "Email is changed without verification");
		softAssert.assertAll();
	}

	public void resetEmailIfUpdateEmailIsFailed(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed()) {
			click(editButton);
			type(email, existingEmailID);
			click(saveDetails);
			waitInSeconds(2);
			driver.quit();
		} else {
			driver.quit();
		}
	}
}
