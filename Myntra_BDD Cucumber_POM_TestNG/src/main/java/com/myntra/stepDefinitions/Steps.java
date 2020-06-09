package com.myntra.stepDefinitions;

import com.myntra.generics.GenericMethods;
import com.myntra.pageObjects.Address;
import com.myntra.pageObjects.CheckOut;
import com.myntra.pageObjects.Home;
import com.myntra.pageObjects.Login;
import com.myntra.pageObjects.Products;
import com.myntra.pageObjects.Profile;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps extends GenericMethods {

	Home home = new Home(driver);
	Login login = new Login(driver);
	Address address = new Address(driver);
	Products product =  new Products(driver);
	CheckOut checkOut = new CheckOut(driver);
	Profile profile = new Profile(driver);

	/**
	 * Steps added for Default Address feature
	 */

	@Given("^Browser is opened$")
	public void browser_is_opened() {
		System.out.println(driver.getTitle());
	}

	@When("^I enter URL$")
	public void i_enter_URL() throws Throwable {
		// Get URL from Excel Sheet
		String URL = getExcelData(parameters, appDetails, 6, 0);
		driver.get(URL);
	}

	@Then("^LOG IN page should be loaded$")
	public void log_IN_page_should_be_loaded() throws Throwable {
		login.validateLoginPage();
	}

	@When("^I LOG IN with valid credentials$")
	public void log_IN_with_valid_credentials() throws Throwable {
		login.loginWithTestData();
	}

	@Then("^Home Page should be loaded$")
	public void home_Page_should_be_loaded() throws Throwable {
		home.validateHomePage();
	}

	@When("^I Place cursor on Profile icon$")
	public void i_Place_cursor_on_Profile_icon() throws Throwable {
		home.moveToProfileIcon();
	}

	@When("^I click on Saved Addresses$")
	public void i_click_on_Saved_Addresses() throws Throwable {
		home.openAddressPage();
	}

	@Then("^Address page should be loaded$")
	public void address_page_should_be_loaded() throws Throwable {
		address.validateAddressPage();
	}

	@When("^I click on Add Address button$")
	public void i_click_on_Add_Address_button() throws Throwable {
		address.clickAddAddressButton();
	}

	@When("^Enter \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" details$")
	public void enter_details(String Name, String Mobile, String Pincode, String Address) throws Throwable {
		address.enterDetails(Name, Mobile, Pincode, Address);
	}

	@When("^Select Locality, Type of Address$")
	public void select_Locality_Type_of_Address() throws Throwable {
		address.selectLocalityAndTypeOfAddress();
	}

	@When("^Click on Make this as my default address checkBox and Click on Save$")
	public void click_on_Make_this_as_my_default_address_checkBox_and_Click_on_Save() throws Throwable {
		address.addAsDefaultAddressAndSave();
	}

	@Then("^Added \"([^\"]*)\" address should be displayed as default address$")
	public void added_address_should_be_displayed_as_default_address(String addedName) throws Throwable {
		address.validateDefaultAddress(addedName);
	}

	@When("^I click on Make this default button of Other Address$")
	public void i_click_on_Make_this_default_button_of_Other_Address() throws Throwable {
		address.clickOnMakeThisDefault();
	}

	@Then("^the Other Address should be made as Default Address$")
	public void the_Other_Address_should_be_made_as_Default_Address() throws Throwable {
		address.validateMakeThisDefaultFuntion();
	}
	
	@After("@MakeThisDefaultAddress")
	public void removeAddedAddress(Scenario scenario) {
		address.removeAddedAddress(scenario);
	}
	
	/**
	 * Added steps for Items Count feature
	 */

	@When("^I enter Home Page URL \"([^\"]*)\"$")
	public void i_enter_Home_Page_URL(String URL) throws Throwable {
		driver.get(URL);
	}

	@When("^I Search for a product and click on Search Button$")
	public void i_Search_for_a_product_and_click_on_Search_Button() throws Throwable {
		home.searchUsingTestData();
	}

	@Then("^Products page should be loaded$")
	public void products_page_should_be_loaded() throws Throwable {
		product.validateProductPage();
	}

	@When("^I count all items from search results$")
	public void i_count_all_items_from_search_results() throws Throwable {
		product.countResultProducts();
	}

	@When("^validate with Items count displayed$")
	public void validate_with_Items_count_displayed() throws Throwable {
		product.itemCountdisplayed();
	}

	@Then("^Both Items count should be same$")
	public void both_Items_count_should_be_same() throws Throwable {
		product.validateDisplayedAndActualItemCount();
	}

	/**
	 * Added Steps for Ordering Product feature
	 */

	@When("^I set Sort By: price - low to high$")
	public void i_set_Sort_By_price_low_to_high() throws Throwable {
		product.sortByPriceLowToHigh();
	}

	@Then("^products should be displayed in ascending order of the prices$")
	public void products_should_be_displayed_in_ascending_order_of_the_prices() throws Throwable {
		product.validateIfPricesLowToHigh();
	}

	@When("^I add the first product to Wishlist$")
	public void i_add_the_first_product_to_Wishlist() throws Throwable {
		product.addFirstProductToWishist();
	}

	@When("^Click on Wishlist button in navigation bar$")
	public void click_on_Wishlist_button_in_navigation_bar() throws Throwable {
		home.openWishlist();
	}

	@Then("^added product should be in wishlist$")
	public void added_product_should_be_in_wishlist() throws Throwable {
		product.waitForAddedProductWishlist();
	}

	@When("^I click on Move to Bag and select size$")
	public void i_click_on_Move_to_Bag_and_select_size() throws Throwable {
		product.selectSizeAndMoveProductToBag();
	}

	@Then("^product should be moved to Bag$")
	public void product_should_be_moved_to_Bag() throws Throwable {
		product.validateIfProductMovedToBagAndWishlistIsEmpty();
	}

	@When("^I click on Bag in Navigation Bar$")
	public void i_click_on_Bag_in_Navigation_Bar() throws Throwable {
		home.openBag();
	}

	@Then("^Shopping Bag Page should be open$")
	public void shopping_Bag_Page_should_be_open() throws Throwable {
		checkOut.validateBagPage();
	}

	@When("^I click on Place order$")
	public void i_click_on_Place_order() {
		checkOut.placeOrder();
	}

	@Then("^Address Page should be open$")
	public void address_Page_should_be_open() throws Throwable {
		checkOut.validateAddressPage();
	}

	@When("^I click on Continue$")
	public void i_click_on_Continue() throws Throwable {
		checkOut.clickContinue();
	}

	@Then("^Payment Page should be open$")
	public void payment_Page_should_be_open() throws Throwable {
		checkOut.validatePaymentPage();
	}

	@When("^I choose Phone pe and Click on Pay Now$")
	public void i_choose_Phone_pe_and_Click_on_Pay_Now() throws Throwable {
		checkOut.payNowWithPhonePe();
	}

	@Then("^Phone pe payments page should be open$")
	public void phone_pe_payments_page_should_be_open() throws Throwable {
		checkOut.validatePhonePePaymentsPage();
	}

	/**
	 * Added Steps for Update Email feature
	 */

	@When("^I click on Edit profile$")
	public void i_click_on_Edit_profile() throws Throwable {
		home.openProfilePage();
	}

	@Then("^Profile page should be loaded$")
	public void profile_page_should_be_loaded() throws Throwable {
		profile.validateProfilePage();
	}

	@Then("^Email Id in Email field should be same with Log IN credentials$")
	public void email_Id_in_Email_field_should_be_same_with_Log_IN_credentials() throws Throwable {
		profile.validateLogINEmailID();
	}
	
	@When("^I enter a new Email Id and Click on Save details$")
	public void i_enter_a_new_Email_Id_and_Click_on_Save_details() throws Throwable {
		profile.changeEmailId();
	}

	@Then("^portal should ask to check verification Email$")
	public void portal_should_ask_to_check_verification_Email() throws Throwable {
		profile.checkForVerificationEmail();
	}

	@But("^details are saved without verification$")
	public void details_are_saved_without_verification() throws Throwable {
		profile.validateEmailID();
	}
	
	@After("@UpdateEmail")
	public void resetEmail(Scenario scenario) throws InterruptedException {
		profile.resetEmailIfUpdateEmailIsFailed(scenario);
	}

}
