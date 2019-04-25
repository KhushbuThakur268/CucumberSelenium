package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class ObjectLocatorDemo 
{
	WebDriver driver;
	@BeforeMethod
	public void beforeMethod() 
	{
		driver = DriverUtility.getDriver("ie");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod()
	{
		//driver.close();
	}

	@Test
	public void testDemoWebShop() 
	{
		
		driver.get("http://demowebshop.tricentis.com/login");
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("webshop"));
		WebElement email = driver.findElement(By.id("Email"));
		email.sendKeys("askmail@gmail.com");

		driver.findElement(By.id("Password")).sendKeys("abc123");
		driver.findElement(By.cssSelector("input[value = 'Log in']")).click();
		
		/*
		driver.get("https://www.google.com/");
		String url = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("a[title = 'Google apps']")).click();
		//driver.findElement(By.xpath(//div[aria-lable='G))*/
		
	
	}
}