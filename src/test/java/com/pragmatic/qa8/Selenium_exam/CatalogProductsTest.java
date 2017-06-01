package com.pragmatic.qa8.Selenium_exam;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pragmatic.qa8.Selenium_exam.pages.AdminDefaultPage;
import com.pragmatic.qa8.Selenium_exam.pages.AdminLoginPage;
import com.pragmatic.qa8.Selenium_exam.pages.CatalogProductsPage;
import com.pragmatic.qa8.Selenium_exam.utils.Browser;

public class CatalogProductsTest {
	
	
	@Before
	public void setUp(){
		AdminLoginPage.open();
		AdminLoginPage.logIn("admin", "parola");
		AdminDefaultPage.assertLoggedInAs("admin");
		CatalogProductsPage.open();
		CatalogProductsPage.assertPageLoaded();
		
		
	}
	
	@After
	public void tearDown() {
		Browser.shutDown();
	}

	@Test
	public void addProduct() {
		String productName="Bulgarian_banichka";
		String modelName="Vit@";
		
		//delete product if exists
		CatalogProductsPage.searchForProcuctByName(productName);
		if (CatalogProductsPage.isFirstRowProductExists(productName)) {
			
			CatalogProductsPage.deleteProductByName(productName);
		}
		
		CatalogProductsPage.addProduct(productName, modelName);
		
	}

}
