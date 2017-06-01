package com.pragmatic.qa8.Selenium_exam.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

	public static WebDriver driver;
	
	public static void init() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium_drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	public static void shutDown() {
		driver.quit();
	}
	
	public static void open(String url) {
		init();
		Browser.driver.get(url);
	}
	
	

}
