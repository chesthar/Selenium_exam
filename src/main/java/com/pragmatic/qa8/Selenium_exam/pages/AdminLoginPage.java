package com.pragmatic.qa8.Selenium_exam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pragmatic.qa8.Selenium_exam.utils.Browser;

public class AdminLoginPage {
	@FindBy(name = "username")
	private static WebElement username;
	@FindBy(name = "password")
	private static WebElement password;
	@FindBy(css = "a.button")
	private static WebElement logInBtn;
	@FindBy(css = ".box .warning")
	private static WebElement warningMessage;

	private static final String url = "http://shop.pragmatic.bg/admin/";

	public static void open() {

		Browser.open(url);
		PageFactory.initElements(Browser.driver, AdminLoginPage.class);

	}

	public static void logIn(String usernameParam, String passwordParam) {
		username.sendKeys(usernameParam);
		password.sendKeys(passwordParam);
		logInBtn.click();
	}

}
