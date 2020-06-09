package com.myntra.pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.generics.GenericMethods;

import cucumber.api.Scenario;

public class Address extends GenericMethods {

	public Address(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[.=' + Add New Address ']")
	private WebElement addNewAddressButton;

	@FindBy(xpath = "//div[@class='myInput-error myInput-visible']")
	private List<WebElement> invalidInputMesage;

	@FindBy(xpath = "(//input)[2]")
	private WebElement nameTextBox;

	@FindBy(xpath = "(//input)[3]")
	private WebElement mobileTextBox;

	@FindBy(xpath = "//div[@class='addAddressModal-cardFields']")
	private WebElement popUpFrame;

	@FindBy(xpath = "(//input)[4]")
	private WebElement pinCodeTextBox;

	@FindBy(xpath = "(//input)[6]")
	private WebElement addressTextBox;

	// Both State and City Boxes will be disabled after entering valid Pin code
	@FindBy(xpath = "//input[@disabled]")
	private List<WebElement> pinCodeValidationOfStateAndCity;

	@FindBy(xpath = "//input[@name='locality']")
	private WebElement addLocality;

	@FindBy(xpath = "//div[@class='localitySelector-localities']/div[1]")
	private WebElement addLocalityCheckBox1;

	@FindBy(xpath = "(//input)[9]")
	private WebElement addLocalityOther;

	@FindBy(xpath = "(//div[@class='localitySelector-button'])[2]")
	private WebElement confirmLocality;

	@FindBy(xpath = "//span[.='Home']")
	private WebElement addAsHome;

	@FindBy(xpath = "//span[.='Office']")
	private WebElement addAsOffice;

	@FindBy(xpath = "//label[@class='addAddressModal-checkBoxLabel']")
	private WebElement addAsDefaultCheckBox;

	@FindBy(xpath = "//div[.='Save']")
	private WebElement saveAddressButton;

	@FindBy(xpath = "//div[ .=' REMOVE ']")
	private WebElement removeAddressButton;
	
	@FindBy(xpath = "//div[ .='Delete']")
	private WebElement confirmRemoveAddressButton;

	@FindBy(xpath = "(//div[@class=\"addressAccordian-button\"])[1]")
	private WebElement editAddressButton;

	@FindBy(xpath = "//span[@class='addressAccordian-makeDefaultText']")
	private WebElement makeMyAddressDefaultButton;

	@FindBy(xpath = "//span[@class='addressAccordian-name']")
	private List<WebElement> defaultAndOtherAddressUserNames;

	public void validateAddressPage() {
		pageToLoad("Address");
	}
	
	public void clickAddAddressButton() {
		click(addNewAddressButton);
	}
	
	public void enterDetails(String Name, String Mobile, String Pincode, String Address) {
		
		type(nameTextBox, Name);
		type(mobileTextBox, Mobile);
		type(pinCodeTextBox, Pincode);
		type(addressTextBox, Address);
	}
	
	public void selectLocalityAndTypeOfAddress() {
		click(addLocality);
		click(addLocalityCheckBox1);
		click(confirmLocality);
		click(addAsHome);
	}
	
	public void addAsDefaultAddressAndSave() throws InterruptedException {
		click(addAsDefaultCheckBox);
		click(saveAddressButton);
		waitInSeconds(3);
	}
	
	public void validateDefaultAddress(String addedName) {
		String defaultUserName = defaultAndOtherAddressUserNames.get(0).getText();
		assertEquals(defaultUserName, addedName, "Address is not added as default address");
	}
	
	String otherAddressUserName;
	public void clickOnMakeThisDefault() {
		
		otherAddressUserName = defaultAndOtherAddressUserNames.get(1).getText();
		click(defaultAndOtherAddressUserNames.get(1));
		click(makeMyAddressDefaultButton);
	}

	public void validateMakeThisDefaultFuntion() {
		String newUserName = defaultAndOtherAddressUserNames.get(0).getText();
		System.out.println("New " + newUserName);
		System.out.println("Other " + otherAddressUserName);
		assertEquals(otherAddressUserName, newUserName, "Make This default address button is not working");	
	}

	/**
	 * To remove the added address to avoid duplicates
	 * 
	 * @param scenario
	 */
	public void removeAddedAddress(Scenario scenario) {
		click(removeAddressButton);
		click(confirmRemoveAddressButton);
	}
}