package ApllicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
WebDriver driver;
Actions ac;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="welcome")
WebElement clickwelcome;
@FindBy(linkText="Logout")
WebElement clicklogout;
public void verifyLogout()throws Throwable
{
	ac= new Actions(driver);
	ac.moveToElement(clickwelcome).click().perform();
	Thread.sleep(3000);
	ac.moveToElement(clicklogout).click().perform();
	Thread.sleep(3000);
}
}
