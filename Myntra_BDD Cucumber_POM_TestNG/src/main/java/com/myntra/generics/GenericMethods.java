package com.myntra.generics;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Anem Shanmuka Chandra Teja
 * 
 *         Super Class to TestRunner Classes
 */
public class GenericMethods extends SetUp {

	/**
	 * Reads data from specified cell in a specified WorkBook Sheet
	 * 
	 * @param path
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws  
	 */
	public String getExcelData(String path, String sheetname, int row, int cell)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
		return data;
	}

	/**
	 * Retries with try/catch for element's visibility
	 * 
	 * @param driver
	 * @param element
	 * @return 
	 */
	public void waitFor(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			System.out.println("refreshed and waited for "+element);
		}
	}

	/**
	 * To wait for either of both element's visibility
	 * 
	 * @param driver
	 * @param element1
	 * @param element2
	 */
	public void waitForEither(WebElement element1, WebElement element2) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(element1),
				ExpectedConditions.visibilityOf(element2)));
	}

	public void click(WebElement element) {
		try {
			waitFor(element);
			element.click();
		} catch (Exception e) {
			// clicks directly on the element in DOM
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			System.out.println(element +"JS click");
		}
	}

	public void moveTo(WebElement element) {
		waitFor(element);
		action.moveToElement(element).perform();
	}

	/**
	 * Enters a value into a specified text field only if it is found
	 * 
	 * @param driver
	 * @param action
	 * @param element
	 * @param value
	 */
	public void type(WebElement element, String value) {
		waitFor(element);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Generates and returns a random number of 10 or above digits
	 * 
	 * @return
	 */
	public String mobileNumber() {
		Long randomNumber = (long) (Math.random() * Math.pow(10, 12));
		return randomNumber.toString();
	}

	public void waitInSeconds(int i) throws InterruptedException {
		Thread.sleep(i * 1000);
	}

	/**
	 * To validate if expected page is loaded or not
	 * 
	 * @param driver
	 * @param expectedTitle
	 */
	public void pageToLoad(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
	}

}
