package com.myntra.pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.generics.GenericMethods;


/**
 * @author Anem Shanmuka Chandra Teja
 */
public class Products extends GenericMethods {

	public Products(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "sort-sortBy")
	private WebElement sortBy;

	@FindBy(xpath = "//label[.='Price: Low to High']")
	private WebElement sortByPriceLowToHigh;

	@FindBy(xpath = "//div[@class='sort-sortBy']/span[.='Price: Low to High']")
	private WebElement sortByPriceLowToHighIsSelected;

	@FindBy(className = "product-base")
	private WebElement resultBase;

	@FindBy(xpath = "//li[@class='product-base'][1]")
	private WebElement firstProduct;

	@FindBy(xpath = "//ul[@class='results-base']//span[.='wishlist']")
	private WebElement addToWishList;

	@FindBy(xpath = "//div[@class='product-actions ']//span[.='Add to bag']")
	private WebElement addToBagButton;

	@FindBy(xpath = "//a[.='MOVE TO BAG']")
	private WebElement moveToBagButton;

	@FindBy(xpath = "//button[@class='sizeselect-sizeButton']")
	private WebElement productSizeButton;

	@FindBy(xpath = "//div[.='Done']")
	private WebElement doneButton;

	@FindBy(xpath = "//a[.='CONTINUE SHOPPING']")
	private WebElement continueShoppingButton;

	@FindBy(className = "title-count")
	private WebElement totalItems;

	@FindBy(xpath = "//a[@rel='next']")
	private WebElement nextPageButton;

	@FindBy(className = "product-price")
	private List<WebElement> productsPrice;

	@FindBy(xpath = "//div[@class='notify-info-message']")
	private WebElement confirmNotification;
	
	private int displayedItemsCount;
	private int actualItemsCount;

	public void validateProductPage() {
		waitFor(resultBase);
	}

	public void sortByPriceLowToHigh() {
		moveTo(sortBy);
		click(sortByPriceLowToHigh);
		waitFor(sortByPriceLowToHighIsSelected);
	}

	public void validateIfPricesLowToHigh() {
		List<Integer> prices = new ArrayList<Integer>();
		for (WebElement e : productsPrice) {
			String fullPriceString = e.getText().substring(4, 7);
			int price = Integer.parseInt(fullPriceString);
			prices.add(price);
		}
		// make a copy of the list
		List<Integer> sortedPrices = new ArrayList<Integer>(prices);
		// sort the list
		Collections.sort(sortedPrices);
		// true if the prices are sorted
		assertEquals(prices, sortedPrices, "The prices are not in Low to High Order");
	}

	public void addFirstProductToWishist() {
		moveTo(firstProduct);
		click(addToWishList);
		waitFor(confirmNotification);;
	}
	
	public void waitForAddedProductWishlist() {
		waitFor(moveToBagButton);
	}
	
	public void selectSizeAndMoveProductToBag() throws InterruptedException {
		click(moveToBagButton);
		click(productSizeButton);
		click(doneButton);
		waitInSeconds(3);
	}
	
	public void validateIfProductMovedToBagAndWishlistIsEmpty() {
		waitFor(continueShoppingButton);
	}
	
	public void countResultProducts() {
		while (true) {
			int pageItemsCount = productsPrice.size();
			actualItemsCount = pageItemsCount + actualItemsCount;
			try {
				if (nextPageButton.isDisplayed()) {
					click(nextPageButton);
					waitInSeconds(3);
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	public void itemCountdisplayed() {
		// Get total number of items shown in Products page
		String displayedItemsString = totalItems.getText();
		StringBuilder displayedItemsDigit = new StringBuilder();
		for (char c : displayedItemsString.toCharArray()) {
			if (Character.isDigit(c)) {
				displayedItemsDigit.append(c);
			}
		}
		String displayedItems = displayedItemsDigit.toString();
		displayedItemsCount = Integer.valueOf(displayedItems);
	}

	public void validateDisplayedAndActualItemCount() {
		assertEquals(actualItemsCount, displayedItemsCount);
	}

}

//	@FindBy(xpath = "//img[@class='index-loadingImage index-show']")
//	@FindBy(xpath = "//div[@class='spinner-spinner']")

//	@FindBy(xpath = "//p[contains(@class,'notify-thumbnail-text')]")
//	@FindBy(xpath = "//div[contains(@class,'notify-info-message')]")

//@FindBy(xpath = "//li[@class='product-base']//a[contains(@href,'speaker')]")
//private List<WebElement> resultSpeakers;
//
//@FindBy(xpath = "//li[@class='product-base']//a[contains(@href,'headphones')]")
//private List<WebElement> resultHeadphones;
//
//public List<WebElement> getResultSpeakers() {
//	return resultSpeakers;
//}
//
//public List<WebElement> getResultHeadphones() {
//	return resultHeadphones;
//}