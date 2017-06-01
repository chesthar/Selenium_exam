package com.pragmatic.qa8.Selenium_exam.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pragmatic.qa8.Selenium_exam.utils.Browser;

public class CatalogProductsPage {
	// main screen elements
	@FindBy(xpath = "//a[@class='top'][contains(.,'Catalog')]")
	private static WebElement catalogTopMenu;
	@FindBy(xpath = "//a[contains(@href,'catalog/product')][contains(.,'Products')]")
	private static WebElement productsSubMenu;
	@FindBy(xpath = "//h1[contains(text(),'Products')]")
	private static WebElement productsHeader;
	@FindBy(css = "#form input[name='filter_name']")
	private static WebElement productNameTb;
	@FindBy(xpath = "//*[@class='buttons']/a[@class='button'][./text()='Delete']")
	private static WebElement deleteBtn;
	@FindBy(xpath = "//*[@class='buttons']/*[@class='button'][./text()='Insert']")
	private static WebElement insertBtn;
	@FindBy(css = ".breadcrumb + .success")
	private static WebElement successMsg;
	

	// products table elements
	@FindBy(xpath = "//*[@class='list']/tbody/tr[2]/td/input[@type='checkbox']")
	private static WebElement firstRowProductCheckbox;
	@FindBy(xpath = "//*[@class='list']/tbody/tr[2]/td[3]")
	private static WebElement firstRowProductName;
	@FindBy(xpath = "//*[@class='list']/tbody/tr[2]/td[4]")
	private static WebElement firstRowModelName;
	
	
	

	// elements after clicked on insert button
	@FindBy(css = ".form input[name='product_description[1][name]']")
	private static WebElement productNameTbGeneralTab;
	@FindBy(css = "#tabs a[class='selected'][href='#tab-general']")
	private static WebElement genralTabSelected;
	@FindBy(css = "#tabs a[href='#tab-data']")
	private static WebElement dataTab;
	@FindBy(css = "#tabs a[class='selected'][href='#tab-data']")
	private static WebElement dataTabSelected;
	@FindBy(css = "#tab-data input[name='model']")
	private static WebElement modelTbDataTab;
	@FindBy(xpath = "//*[@class='buttons']/*[@class='button'][./text()='Save']")
	private static WebElement saveBtn;
	
	public static void open() {
		PageFactory.initElements(Browser.driver, CatalogProductsPage.class);
		Actions action = new Actions(Browser.driver);
		action.moveToElement(catalogTopMenu).moveToElement(productsSubMenu).click().perform();

	}

	public static void addProduct(String productName, String model) {
		insertBtn.click();;
		Assert.assertEquals("You're not on General tab", "General", genralTabSelected.getText());
		productNameTbGeneralTab.sendKeys(productName);
		dataTab.click();
		Assert.assertEquals("You're not on Data tab", "Data", dataTabSelected.getText());
		modelTbDataTab.sendKeys(model);
		saveBtn.click();

		Assert.assertEquals("First row product'sname doesn't match!",productName, firstRowProductName.getText());
		Assert.assertEquals("First row model's name doesn't match!",model, firstRowModelName.getText());

		System.out.println("Product: " + productName + " model: " + model + " was added successfully!");

	}

	public static void assertPageLoaded() {
		Assert.assertEquals("This is not the expected Products header text", "Products", productsHeader.getText());
	}

	public static void searchForProcuctByName(String product) {
		productNameTb.sendKeys(product);
		productNameTb.sendKeys(Keys.ENTER);
	}

	// delete only if products name matches exactly and delete all copied
	// products with the same name!
	public static void deleteProductByName(String product) {

		System.out.println("Trying to delete product " + product + "...");
		while (isFirstRowProductExists(product)) {
			firstRowProductCheckbox.click();
			Assert.assertEquals("Checbox is not selected!", true, firstRowProductCheckbox.isSelected());
			deleteBtn.click();
			acceptAlert();
			Assert.assertEquals("No success message appered!", "Success: You have modified products!",
					successMsg.getText());
		}
		System.out.println("All of the " + product + " products that was found are now deleted!");
	}

	public static boolean isFirstRowProductExists(String productname) {
		String firstRowProductNameText = "//*[@class='list']/tbody/tr[2]/td[3][./text()='";
		firstRowProductNameText += productname + "']";
		Boolean isElementPresent = Browser.driver.findElements(By.xpath(firstRowProductNameText)).size() > 0;
		if (isElementPresent) {
			System.out.println("Product " + productname + " exists!");
			return true;
		} else {
			System.out.println("Product " + productname + " does not exists!");
			return false;
		}
	}

	public static void acceptAlert() {

		try {

			// Check the presence of alert
			Alert alert = Browser.driver.switchTo().alert();
			// if present consume the alert
			alert.accept();

		} catch (NoAlertPresentException ex) {
			// Alert not present
			System.err.println("Alert is not present!");
			ex.printStackTrace();
		}

	}

}
