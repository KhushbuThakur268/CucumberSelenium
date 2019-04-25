package com.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.spi.ResolveResult;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.util.DriverUtility;

public class TestExtentReport
{
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void beforeClass()
	{ 	
		driver = DriverUtility.getDriver("Chrome");
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ms");

		String path = System.getProperty("user.dir")+ "/Extent-Report/"+sdf.format(new Date())+".html";
		htmlReporter = new ExtentHtmlReporter(path);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("username", "Khushbu.a.thakur");
		reports.setSystemInfo("hostname", "localhost");
		reports.setSystemInfo("Environment", "Test Environment");
		htmlReporter.config().setReportName("Test Me App Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Test
	public void passTest() 
	{
		logger = reports.createTest("passTest");
		logger.log(Status.INFO, "Pass test started");
		Assert.assertTrue(true);
	}

	@Test
	public void failTest() 
	{
		logger = reports.createTest("failTest");
		logger.log(Status.INFO, "Fail test started");
		driver.findElement(By.id("Email")).sendKeys("askmail@email.com");
		driver.findElement(By.id("Pass")).sendKeys("askmail@email.com");
		//Assert.assertTrue(false);
	}

	@Test
	public void skipTest()
	{
		logger = reports.createTest("skipTest");
		logger.log(Status.INFO, "Skip test started");
		throw new SkipException("Skip");
	}

	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, "This test is Failed");

			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			String imagepath = System.getProperty("user.dir")+ "/Extent-Report/Images/"+ result.getMethod().getMethodName()+".png";

			try {
				FileUtils.copyFile(srcFile, new File(imagepath));
				logger.log(Status.INFO, result.getThrowable());
				logger.addScreenCaptureFromPath(imagepath, "Failed test image");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "This test is Passed");
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "This test is Skiped");
		}
	}

	@AfterClass
	public void afterClass()
	{
		reports.flush();
	}
}
