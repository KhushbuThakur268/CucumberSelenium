package com.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtility
{
	public static WebDriver getDriver(String Browser)
	{
		if(Browser.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Software\\chromedriver.exe" );
			return new ChromeDriver();
		}
		else if(Browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Software\\geckodriver.exe");
			return new FirefoxDriver();
		}
		else if(Browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver","C:\\Software\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		else 
			return null;
	}
}
