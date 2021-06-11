package DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ApllicationLayer.AddUserPage;
import ApllicationLayer.LoginPage;
import ApllicationLayer.LogoutPage;
import Utilities.ExcelFileUtil;

public class DataDrivenFramework {
WebDriver driver;
Properties p;
FileInputStream fi;
ExtentReports report;
ExtentTest logger;
String inputpath="E:\\930AMBatch\\Automation_Frameworks\\TestInput\\userCreation.xlsx";
String outputpath="E:\\930AMBatch\\Automation_Frameworks\\TestoutPut\\DataDrivenResults.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	report= new ExtentReports("./ExtentReports/datadriven.html");
	p=new Properties();
	fi= new FileInputStream("E:\\930AMBatch\\Automation_Frameworks\\PropertyFile\\Environment.properties");
	p.load(fi);
	if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "E:\\930AMBatch\\Automation_Frameworks\\Drivers\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "E:\\930AMBatch\\Automation_Frameworks\\Drivers\\geckodriver.exe");
		driver =new FirefoxDriver();
		driver.get(p.getProperty("Url"));
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	else
	{
		System.out.println("Browser value is not matching");
	}
}
@Test
public void usercre()throws Throwable
{
	AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
	//accessing excel methods
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	//count no of rows
	int rc=xl.rowCount("AddUser");
	//count no of cells in a row
	int cc=xl.cellCount("AddUser");
	Reporter.log("No of rows are::"+rc+"  "+"No of cells in first row are::"+cc,true);
	for(int i=1; i<=rc;i++)
	{
	logger=report.startTest("DataDriven");	
	String ename=xl.getCellData("AddUser", i, 0);
	String username=xl.getCellData("AddUser", i, 1);
	String password=xl.getCellData("AddUser", i, 2);
	String cpassword=xl.getCellData("AddUser", i, 3);
	user.verifyAdduser(ename, username, password, cpassword);
	String expected="viewSystemUsers";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("User Added Success",true);
		//write a s pass into results cell
		xl.setCelldata("AddUser", i, 4, "Pass", outputpath);
		logger.log(LogStatus.PASS, "User Added Success");
	}
	else
	{
		Reporter.log("User Added UnSuccess",true);
		File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("./ScreenShot/"+"_"+i+"userpage.png"));
		logger.log(LogStatus.FAIL, "Fail To Create New User");
		String image=logger.addScreenCapture("./ScreenShot/"+"_"+i+"userpage.png");
		logger.log(LogStatus.FAIL, image);
		xl.setCelldata("AddUser", i, 4, "Fail", outputpath);
	}
	report.endTest(logger);
	report.flush();
	}
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}












