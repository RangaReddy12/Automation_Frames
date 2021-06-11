package ApllicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
}
//store locators for Login
@FindBy(name="txtUsername")
WebElement username;
@FindBy(xpath="//input[@id='txtPassword']")
WebElement password;
@FindBy(name="Submit")
WebElement loginbtn;
public void verifyLogin(String username,String password)throws Throwable
{
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	loginbtn.click();
	Thread.sleep(5000);
}
}
