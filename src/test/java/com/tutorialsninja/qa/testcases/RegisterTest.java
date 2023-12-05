package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	public RegisterTest() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")).click();
	}
	
	@Test(priority = 1)
	public void VerifyRegisteringAnAccountWithMandatoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("amotooricap"+Utilities.generateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("phoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("password"));
		driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("password"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String accountCreatedActualMessage = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")).getText();
		String accountCreatedExpectedMessage = dataprop.getProperty("accountCreateSuccessMessage");
		Assert.assertEquals(accountCreatedActualMessage, accountCreatedExpectedMessage);

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
