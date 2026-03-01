package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	
	private WebDriver driver;
	private ElementUtil eleutil;
	//webElements
	private final By email=By.id("input-email");
	private final By password=By.id("input-password");
	private final By forgotpasswordlink=By.linkText("Forgotten Password");
	private final By loginbtn=By.xpath("//input[@value='Login']");
	private final By header=By.tagName("h2");
	private final By registerLink =By.linkText("Register");

	
	//constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	
	//Actions methods
	 public boolean IsLoginPageForgotLinkExist()
	 {
		 return eleutil.isElementDisplayed(forgotpasswordlink);
		 //return driver.findElement(forgotpasswordlink).isDisplayed();
	 }
	 
	 public boolean IsLoginPageHeaderExist()
	 {
		 return eleutil.isElementDisplayed(header);
		 //return driver.findElement(header).isDisplayed();
	 }
	 
	 public String getLoginPageTitle()
	 {
		 String title=eleutil.waitFotTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		// String title=driver.getTitle();
		 System.out.println("Login page title : "+ title);
		 return title;
	 }
	 
	 public String getLoginPageURL()
	 {
		 String url=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_MEDIUM_WAIT);
		 //String url=driver.getCurrentUrl();
		 System.out.println("Login page url : "+ url);
		 return url;
	 }
	 
	 
	 public AccountsPage doLogin(String app_username,String app_password)
	 {
		 eleutil.waitForElementVisible(email, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(app_username);
		 eleutil.doSendKeys(password, app_password);
		 eleutil.doClick(loginbtn);
		 return new AccountsPage(driver);

		// driver.findElement(email).sendKeys(app_username);
//		 driver.findElement(password).sendKeys(app_password);
//		 driver.findElement(loginbtn).click();
//		 String title=driver.getTitle();
//		 return new AccountsPage(driver);
	 }
	 
	 public RegisterPage navigateToRegisterPage()
	 {
		 eleutil.waitForElementVisible(registerLink, 5).click();
		 return new RegisterPage(driver);
	 }
	
	
	
}
