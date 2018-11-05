package com.flipkart.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyBook {
	
	public WebDriver driver = null;
	public static WebDriverWait wait = null;
	
	public void PreConditions() {		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		
	}

}
