package com.myntra.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.generics.GenericMethods;

/**
 * @author Anem Shanmuka Chandra Teja
 */
public class CheckOut extends GenericMethods {

	public CheckOut(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class= 'button-base-button emptyCart-base-wishlistButton']")
	private WebElement addFromWishListButton;


	@FindBy(xpath = "//a/div[@class='button-base-button ']")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//button[@class='inlinebuttonV2-base-actionButton itemContainer-base-inlineButton removeButton']")
	private List<WebElement> removeButtons;

	@FindBy(xpath = "//button[@class='inlinebutton-base-actionButton itemComponents-base-move itemComponents-base-inlineButton']")
	private WebElement confirmRemoveButton;

	@FindBy(id = "213939626")
	private WebElement activeAddress;

	@FindBy(xpath = "//div[@id='placeOrderButton' and .='continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//span[.='PHONEPE/GOOGLE PAY/BHIM UPI']")
	private WebElement paymentWithUPI;

	@FindBy(xpath = "//span[.='PhonePe']")
	private WebElement paymentWithPhonePe;

	@FindBy(xpath = "//button[.='PAY NOW']")
	private WebElement payNowButton;

	@FindBy(xpath = "//h1[.='Login to PhonePe']")
	private WebElement phonePeLoginForm;
	
	public void validateBagPage() {
		waitForEither(removeButtons.get(0), addFromWishListButton);
		pageToLoad("SHOPPING BAG");
	}
	
	public void placeOrder() {
		click(placeOrderButton);
	}
	
	public void validateAddressPage() {
		waitFor(continueButton);
		pageToLoad("ADDRESS");
	}

	public void clickContinue() {
		click(continueButton);
	}

	public void validatePaymentPage() {
		waitFor(paymentWithUPI);
		pageToLoad("PAYMENT");
	}

	public void payNowWithPhonePe() {
		click(paymentWithUPI);
		click(paymentWithPhonePe);
		click(payNowButton);
	}

	public void validatePhonePePaymentsPage() {
		waitFor(phonePeLoginForm);
		pageToLoad("PhonePe | India's Payments App");
	}

}
