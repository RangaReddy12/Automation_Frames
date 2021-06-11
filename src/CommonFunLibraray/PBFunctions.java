package CommonFunLibraray;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constant.PBConstant;

public class PBFunctions extends PBConstant {
//method for admin login
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
		driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys(username);
		driver.findElement(By.xpath(p.getProperty("ObjPassword"))).sendKeys(username);
		driver.findElement(By.xpath(p.getProperty("Objloginbtn"))).click();
		Thread.sleep(5000);
		String expected="adminflow";
		String actual=driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+expected+"   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"   "+actual,true);
			return false;
		}
	}
	//method for click branches
	public static void clickBranches()
	{
		driver.findElement(By.xpath(p.getProperty("ObjClickbrances"))).click();
	}
	//method for new branch creation
public static boolean verifyNewBranch(String bname,String address1,String address2,
	String address3,String area,String zipcode,String country,String state,String city)	throws Throwable
{
driver.findElement(By.xpath(p.getProperty("ObjClicknewBranch"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("ObjBname"))).sendKeys(bname);
driver.findElement(By.xpath(p.getProperty("ObjAddress1"))).sendKeys(address1);
driver.findElement(By.xpath(p.getProperty("ObjAddress2"))).sendKeys(address2);
driver.findElement(By.xpath(p.getProperty("ObjAddress3"))).sendKeys(address3);
driver.findElement(By.xpath(p.getProperty("ObjArea"))).sendKeys(area);
driver.findElement(By.xpath(p.getProperty("ObjZipcode"))).sendKeys(zipcode);
new Select(driver.findElement(By.xpath(p.getProperty("ObjCountry")))).selectByVisibleText(country);
Thread.sleep(2000);
new Select(driver.findElement(By.xpath(p.getProperty("ObjState")))).selectByVisibleText(state);
Thread.sleep(2000);
new Select(driver.findElement(By.xpath(p.getProperty("ObjCity")))).selectByVisibleText(city);
Thread.sleep(2000);
driver.findElement(By.xpath(p.getProperty("ObjSubmitbtn"))).click();
Thread.sleep(2000);
String branchalert=driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().accept();
String actualalert="New Branch";
if(branchalert.toLowerCase().contains(actualalert.toLowerCase()))
{
	Reporter.log("New Branch Created Success::"+branchalert+"    "+actualalert,true);
	return true;
}
else
{
	Reporter.log("New Branch Created Fail::"+branchalert+"    "+actualalert,true);
	return false;
}
}
//method 
//method for branch updation
public static boolean verifyUpdateBranch(String bname,String address,String zipcode)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("ObjEdit"))).click();
	Thread.sleep(3000);
	WebElement branchname=driver.findElement(By.xpath(p.getProperty("ObjbranchN")));
	branchname.clear();
	branchname.sendKeys(bname);
	Thread.sleep(3000);
	WebElement branchAddress=driver.findElement(By.xpath(p.getProperty("ObjAddress")));
	branchAddress.clear();
	branchAddress.sendKeys(address);
	Thread.sleep(3000);
	WebElement branchzip=driver.findElement(By.xpath(p.getProperty("ObjZip")));
	branchzip.clear();
	branchzip.sendKeys(zipcode);
	Thread.sleep(3000);
	driver.findElement(By.xpath(p.getProperty("Objupdatebtn"))).click();
	Thread.sleep(3000);
	String updatealert=driver.switchTo().alert().getText();
	Thread.sleep(3000);
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	String actualalert="Branch up";
	if(updatealert.toLowerCase().contains(actualalert.toLowerCase()))
	{
		Reporter.log("Branch Update Success::"+updatealert+"   "+actualalert,true);
		return true;
	}
	else
		Reporter.log("Branch Update Fail::"+updatealert+"   "+actualalert,true);
	return false;
	
}
//method for logout
public static boolean verifylogout()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("ObjLogout"))).click();
	Thread.sleep(5000);
	if(driver.findElement(By.xpath(p.getProperty("Objloginbtn"))).isDisplayed())
	{
		Reporter.log("Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Success",true);
		return false;
	}
}
public static String generateDate()
{
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
	return sdf.format(d);
}
}








