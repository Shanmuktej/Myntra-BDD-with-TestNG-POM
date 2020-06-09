package com.myntra.pageObjects;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.generics.GenericMethods;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Anem Shanmuka Chandra Teja
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Login extends GenericMethods {

	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='tel']")
	private WebElement mobileField;

	@FindBy(xpath = "//div[.='CONTINUE']")
	WebElement continueButton;

	@FindBy(xpath = "//span[.=' Password ']")
	WebElement continueWithPasswordButton;

	@FindBy(id = "mobileNumberPass")
	WebElement emailOrMobileField;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[.='LOGIN']")
	WebElement loginButton;
	
	public void validateLoginPage() {
		type(mobileField, mobileNumber());
		click(continueButton);
		click(continueWithPasswordButton);
		waitForEither(continueWithPasswordButton, passwordField);
		pageToLoad("Myntra");
	}

	
	public void loginWithTestData() throws EncryptedDocumentException, IOException, InterruptedException {

		String emailID = getExcelData(parameters, appDetails, 1, 0);
		String password = getExcelData(parameters, appDetails, 1, 1);
		
		type(emailOrMobileField, emailID);
		type(passwordField, password);
		
		click(loginButton);
		waitInSeconds(3);
	}
}
