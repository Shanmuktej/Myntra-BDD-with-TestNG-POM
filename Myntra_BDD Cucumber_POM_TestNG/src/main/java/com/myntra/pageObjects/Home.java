package com.myntra.pageObjects;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.generics.GenericMethods;


/**
 * @author Anem Shanmuka Chandra Teja
 */
public class Home extends GenericMethods {

	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "desktop-user")
	private WebElement profileIcon;

	@FindBy(xpath = "//span[.='Bag']")
	private WebElement bagIcon;
	
	@FindBy(xpath = "//span[.='Wishlist']")
	private WebElement wishListIcon;

	@FindBy(xpath = "//a[contains(text(),'login')]")
	private WebElement loginPageButton;

	@FindBy(xpath = "//a/div[@class='desktop-accInfoSection']")
	private WebElement editProfileButton;

	@FindBy(xpath = "//div[.='Saved Addresses']")
	private WebElement savedAddress;

	@FindBy(xpath = "//div[@class='desktop-accInfoSection' and .=' Logout ']")
	private WebElement logOutButton;

	@FindBy(xpath = "//input[@class='desktop-searchBar']")
	private WebElement searchBar;

	@FindBy(xpath = "//a[@class='desktop-submit']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//main[@class='shop-base']")
	private WebElement mainBase;
	
	public void validateHomePage() {
		waitFor(mainBase);
		pageToLoad("Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra");
	}

	public void moveToProfileIcon() {
		moveTo(profileIcon);
	}

	public void openLoginPage() throws InterruptedException {
		click(loginPageButton);
		waitInSeconds(3);
	}

	public void openBag() throws InterruptedException {
		click(bagIcon);
		waitInSeconds(3);
	}

	public void openProfilePage() throws InterruptedException {
		click(editProfileButton);
		waitInSeconds(3);
	}

	public void openAddressPage() throws InterruptedException {
		click(savedAddress);
		waitInSeconds(3);
	}
	
	public void openWishlist() throws InterruptedException {
		click(wishListIcon);
		waitInSeconds(3);
	}
	
	public void searchUsingTestData() throws EncryptedDocumentException, IOException {
		String productName = getExcelData(parameters, appDetails, 12, 0);
		type(searchBar, productName);
		click(searchButton);
	}

	public void logOut() {
		click(logOutButton);
	}
}