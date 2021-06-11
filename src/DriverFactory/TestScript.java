package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ApllicationLayer.AddUserPage;
import ApllicationLayer.LoginPage;
import ApllicationLayer.LogoutPage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void AdminLogin()throws Throwable
{
	System.setProperty("webdriver.chrome.driver", "E:\\930AMBatch\\Automation_Frameworks\\Drivers\\chromedriver.exe");
	driver =new ChromeDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void usercreation()throws Throwable
{
	AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
	user.verifyAdduser("anitha anu", "Selenium0909", "Akhilesh@12345", "Akhilesh@12345");
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout =PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
