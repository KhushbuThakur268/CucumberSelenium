package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class testMeAppHover
{
	@Test
	public void testChennaiLoc()
	{
		WebDriver driver = DriverUtility.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		Actions act = new Actions(driver);
		
		//act.moveToElement(driver.findElement(By.xpath("//a[@style = 'width: 132px; height: 35px; line-height: 20px;']"))).perform();
		//driver.findElement(By.xpath("//a[@style = 'width: 132px; height: 35px; line-height: 20px;']"));
		
		act.moveToElement(driver.findElement(By.partialLinkText("AboutUs"))).perform();
		//driver.findElement(By.partialLinkText("About Us"))
		
		act.moveToElement(driver.findElement(By.partialLinkText("Our Offices"))).perform();
		
		act.moveToElement(driver.findElement(By.partialLinkText("Chennai"))).click().perform();
		
		
	}
}
