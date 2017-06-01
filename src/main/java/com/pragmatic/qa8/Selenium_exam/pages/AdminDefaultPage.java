package com.pragmatic.qa8.Selenium_exam.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pragmatic.qa8.Selenium_exam.utils.Browser;

public class AdminDefaultPage {

	@FindBy(css = "div.div3")
	private static WebElement greetingText; // top right corner text

	public static void init() {
		PageFactory.initElements(Browser.driver, AdminDefaultPage.class);
	}

	public static void assertLoggedInAs(String user) {

		init();
		Assert.assertEquals("This is not the expected greeting text", " You are logged in as " + user,
							greetingText.getText());
	}

}
