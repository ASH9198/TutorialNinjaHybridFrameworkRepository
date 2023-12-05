package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	public LoginTest() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
	}
	
	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton(); 
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
		
		
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress("amotooricap"+Utilities.generateTimeStamp()+"1@gmail.com");
		loginPage.enterPassword(dataprop.getProperty("InvalidPassword"));
		loginPage.clickOnLoginButton(); 	
		String actualWarningMessage = loginPage.getDisplayInValidUserNamePasswordMessage();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInValidEmailInValidPasswordCredentials() {
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress("amotooricap"+Utilities.generateTimeStamp()+"1@gmail.com");
		loginPage.enterPassword(dataprop.getProperty("InvalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.getDisplayInValidUserNamePasswordMessage();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		
		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailInValidPasswordCredentials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress("amotooricap"+Utilities.generateTimeStamp()+"1@gmail.com");
		loginPage.enterPassword(dataprop.getProperty("InvalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.getDisplayInValidUserNamePasswordMessage();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress("amotooricap"+Utilities.generateTimeStamp()+"1@gmail.com");
		loginPage.enterPassword(dataprop.getProperty("InvalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.getDisplayInValidUserNamePasswordMessage();
		String expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	

}
