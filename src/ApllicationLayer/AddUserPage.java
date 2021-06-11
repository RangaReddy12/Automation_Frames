package ApllicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUserPage {
WebDriver driver;
Actions ac;
public AddUserPage(WebDriver driver)
{
	this.driver=driver;
}
//store locators for add user
@FindBy(id="menu_admin_viewAdminModule")
WebElement clickAdmin;
@FindBy(id="menu_admin_UserManagement")
WebElement clickusermgnt;
@FindBy(id="menu_admin_viewSystemUsers")
WebElement clickUsers;
@FindBy(id="btnAdd")
WebElement clickAddbtn;
@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
WebElement ename;
@FindBy(name="systemUser[userName]")
WebElement user;
@FindBy(name="systemUser[password]")
WebElement pass;
@FindBy(name="systemUser[confirmPassword]")
WebElement cpass;
@FindBy(name="btnSave")
WebElement clicksavebtn;
public void verifyAdduser(String ename,String username,String password,String cpassword)
throws Throwable{
	ac= new Actions(driver);
	ac.moveToElement(clickAdmin).perform();
	Thread.sleep(3000);
	ac.moveToElement(clickusermgnt).perform();
	Thread.sleep(3000);
	ac.moveToElement(clickUsers).click().perform();
	Thread.sleep(3000);
	ac.moveToElement(clickAddbtn).click().perform();
	Thread.sleep(3000);
	this.ename.sendKeys(ename);
	this.user.sendKeys(username);
	this.pass.sendKeys(password);
	Thread.sleep(4000);
	this.cpass.sendKeys(cpassword);
	Thread.sleep(3000);
	this.clicksavebtn.click();
	Thread.sleep(5000);
	
}


}
