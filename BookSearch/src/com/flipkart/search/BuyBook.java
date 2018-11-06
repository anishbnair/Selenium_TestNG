package com.flipkart.search;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BuyBook {
	
	public WebDriver driver = null;
	public static WebDriverWait wait = null;
	
	@BeforeMethod
	public void PreConditions() {		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");		
	}
	
	@Test
	public void ShopBook() throws IOException, InterruptedException {
		
		// Close Login popup
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		// Enter Selenium in the search field
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("selenium book");
		//	Click on Search	
		driver.findElement(By.xpath("//input[@name='q']")).submit();
		// click on book
		driver.findElement(By.xpath("//img[@class='_1Nyybr  _30XEf0']")).click();
		
		// To get current window	
		String currentTab = driver.getWindowHandle();
		for (String tab : driver.getWindowHandles()) {
		    if (!tab.equals(currentTab)) {
		        driver.switchTo().window(tab); 
		    }       
		}
		
		Thread.sleep(5000);
		// Enter ZIP code
		driver.findElement(By.xpath("//input[@id='pincodeInputId']")).sendKeys("685588");
		// Click on Check
		driver.findElement(By.xpath("//span[@class='_2aK_gu']")).click();
		// Capture expected delivery
		String expectedDelivery = driver.findElement(By.xpath("//span[@class='_3nCwDW']")).getText();
		Reporter.log(expectedDelivery);
		System.out.println(expectedDelivery);
		
		// Add book to cart
		driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
		// Place order
		driver.findElement(By.xpath("//button[@class='_2AkmmA _14O7kc _7UHT_c']")).click();
		// Enter email, password and click Login
		driver.findElement(By.xpath("//input[@class='_2zrpKA _14H79F']")).sendKeys("test@gmail.com");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1poQZq _7UHT_c']")).click();
		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv _14H79F']")).sendKeys("test");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1poQZq _7UHT_c']")).click();
		Thread.sleep(5000);
		
		// Capture screenshot	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("C:\\Screenshots\\screenshot.png"));
		
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(currentTab);
		driver.close();		
			
	}

}
