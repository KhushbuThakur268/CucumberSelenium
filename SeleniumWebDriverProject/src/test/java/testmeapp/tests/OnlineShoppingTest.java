package testmeapp.tests;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import testmeapp.utility.Drivers;


public class OnlineShoppingTest
{
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest logger;

	@BeforeTest
	public void startReportBeforeTest()
	{
		driver = Drivers.getDriver("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String path = System.getProperty("user.dir")+ "/Extent_Test_Report/"+ sdf.format(new Date())+ ".html";
		htmlReporter = new ExtentHtmlReporter(path);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("UserName", "Khushbu Thakur");
		htmlReporter.config().setReportName("Test_Me_App Report");
		
	}

		@Test(priority = 1)
	public void testRegistration() 
	{
		logger = reports.createTest("testRegistration");		
		driver.findElement(By.partialLinkText("SignUp")).click();
		driver.findElement(By.id("userName")).sendKeys("imbffu1");
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
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		Select sQues = new Select(driver.findElement(By.id("securityQuestion")));
		sQues.selectByIndex(1);
		driver.findElement(By.id("answer")).sendKeys("White");
		driver.findElement(By.cssSelector("input[value = 'Register']")).click();

		String result = driver.findElement(By.xpath("//div[@id='errormsg'][4]")).getText();
		System.out.println("The result = " +result);
		Assert.assertTrue(result.contains("User Registered Succesfully!!! Please login"));
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	 	
	@Test(priority = 2)
	public void testLogin() 
	{
		logger = reports.createTest("testLogin");
		//driver.findElement(By.partialLinkText("SignIn")).click();		//have to remove
		driver.findElement(By.id("userName")).sendKeys("sgimbff");
		driver.findElement(By.id("password")).sendKeys("123456789");
		driver.findElement(By.name("Login")).click();
	}
	@Test(priority = 3)
	public void testCart()
	{
		logger = reports.createTest("testCart");
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.partialLinkText("All Categories"))).perform();
		act.moveToElement(driver.findElement(By.partialLinkText("Electronics"))).click().perform();

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Head Phone"))).click();

		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
		driver.findElement(By.cssSelector("input[value = 'Proceed to Pay'] ")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

	}

	@Test(priority = 4)
	public void testPayment() 
	{
		logger = reports.createTest("testPayment");
    	 //driver.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();
		driver.findElement(By.xpath("//div[@id='swit']/div/div/label/i")).click();
		driver.findElement(By.id("btn")).click();

		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");
		driver.findElement(By.cssSelector("input[value = 'LOGIN']")).click();
		driver.findElement(By.cssSelector("input[value = 'PASSWORD']")).sendKeys("Trans@456");
		driver.findElement(By.cssSelector("input[value = 'PayNow']")).click();

		String result1 = driver.findElement(By.xpath("/html/body/b/section/div/div/div/table[1]/tbody/tr/td[1]")).getText();
		System.out.println("The text = " +result1 );
		Assert.assertNotNull(result1);

	}

	@AfterMethod
	public void getResultAfterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "This Test is Successful");
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			String imgPath = System.getProperty("user.dir")+ "/Extent_Test_Report/Images/"+ result.getMethod().getMethodName()+".png";
			
			try {
				FileUtils.copyFile(srcFile, new File(imgPath));
				logger.addScreenCaptureFromPath(imgPath, "Failed test image");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, "This Test is Failed");
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			String imgPath = System.getProperty("user.dir")+ "/Extent_Test_Report/Images/"+ result.getMethod().getMethodName()+".png";
			
			try {
				FileUtils.copyFile(srcFile, new File(imgPath));
				logger.log(Status.INFO, result.getThrowable());
				logger.addScreenCaptureFromPath(imgPath, "Failed test image");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(result.getStatus()==ITestResult.SKIP) 
		{
			logger.log(Status.SKIP, "This Test is Skiped");
		}
	}
	
	@AfterTest
	public void endReportafterTest()
	{
		driver.findElement(By.partialLinkText("SignOut")).click();
		reports.flush();
	}


}
