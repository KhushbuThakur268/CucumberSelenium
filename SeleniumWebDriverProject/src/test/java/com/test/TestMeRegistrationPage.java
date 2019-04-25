package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class TestMeRegistrationPage 
{
	@Test
	public void testRegistration() 
	{
		WebDriver driver = DriverUtility.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		
		driver.findElement(By.partialLinkText("SignUp")).click();
		
		driver.findElement(By.id("userName")).sendKeys("jd");
		driver.findElement(By.id("err")).getText();
		Assert.assertEquals(driver.findElement(By.id("err")).getText(), "New");
		
		driver.findElement(By.id("firstName")).sendKeys("Khushbu");
		driver.findElement(By.id("lastName")).sendKeys("Thakur");
		
		driver.findElement(By.id("password")).sendKeys("123456789");
		driver.findElement(By.id("pass_confirmation")).sendKeys("123456789");
		
		driver.findElement(By.cssSelector("input[value = 'Female']")).click();
		
		driver.findElement(By.id("emailAddress")).sendKeys("Khushbu@email.com");
		
		driver.findElement(By.id("mobileNumber")).sendKeys("9874563210");
		
		driver.findElement(By.className("ui-datepicker-trigger")).click();
		Select bMonth = new Select(driver.findElement(By.className("ui-datepicker-month")));
		bMonth.selectByIndex(1);
		
	
		Select bYear = new Select(driver.findElement(By.className("ui-datepicker-year")));
		bYear.selectByValue("1995");
		
		driver.findElement(By.linkText("23")).click();
		
		driver.findElement(By.id("address")).sendKeys("32/12 House No- 13, 5th floor, near spice garden, Bangalore, Karnataka.");
		
		Select sQues = new Select(driver.findElement(By.id("securityQuestion")));
		sQues.selectByIndex(1);
		
		driver.findElement(By.id("answer")).sendKeys("White");
		
		driver.findElement(By.cssSelector("input[value = 'Register']")).click();
		
		String result = driver.findElement(By.xpath("//div[@id='errormsg'][4]")).getText();
		System.out.println("The result = " +result);
		Assert.assertTrue(result.contains("User Registered Succesfully!!! Please login"));
	}
}
