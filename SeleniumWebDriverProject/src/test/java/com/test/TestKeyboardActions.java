package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class TestKeyboardActions
{
	@Test
	public void testKeyboardActions()
	{
		WebDriver driver = DriverUtility.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS );
		WebElement search = driver.findElement(By.id("myInput"));
		
		Actions act = new Actions(driver);
		act.keyDown(search, Keys.SHIFT).perform();
		act.sendKeys("b").pause(3000).sendKeys("a").pause(3000).sendKeys("g").perform();
		act.moveToElement(driver.findElement(By.xpath("//div[contains(text(), 'Hand bag')]"))).click().perform();
		
		driver.findElement(By.cssSelector("input[value = 'FIND DETAILS']")).click();
		String name = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[1]/center[1]/h4")).getText();
		System.out.println(name);
		Assert.assertTrue(name.contains("Hand bag"));
		
		
	//NOT GOT THE OUTPUT
	}
}
