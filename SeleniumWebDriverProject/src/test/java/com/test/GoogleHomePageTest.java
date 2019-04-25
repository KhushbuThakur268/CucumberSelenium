package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class GoogleHomePageTest
{
	@Test
	static public void testGoogleHomePage()
	{
		/* //System.setProperty("webdriver.chrome.driver","C:\\Software\\chromedriver.exe" ); 
		//WebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.ie.driver","C:\\Software\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();

		//System.setProperty("webdriver.gecko.driver","C:\\Software\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google"); */
		
		WebDriver driver = DriverUtility.getDriver("ie");
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google");
	}
}
