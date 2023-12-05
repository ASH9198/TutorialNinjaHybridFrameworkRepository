package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base{
	public SearchTest() {
		super();
	}
	
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}
	
	@Test(priority = 1)
	public void VerifySearchWithAnExistingProduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataprop.getProperty("productName"),Keys.ENTER);
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataprop.getProperty("invalidProductName"),Keys.ENTER);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed());
	}
	
	@Test(priority = 3)
	public void VerifySearchWithOutProductName() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
