package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil ele;
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		this.ele=new ElementUtil(driver);
	}
	

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");


	public boolean userRegisteration(String firstName, String lastName, 
			String telephone, String password, String subscribe) {

		ele.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
		ele.doSendKeys(this.lastName, lastName);
		ele.doSendKeys(this.email, StringUtils.getRandomEmailId());
		ele.doSendKeys(this.telephone, telephone);
		ele.doSendKeys(this.password, password);
		ele.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			ele.doClick(subscribeYes);
		} else {
			ele.doClick(subscribeNo);
		}
		ele.doClick(agreeCheckBox);
		ele.doClick(continueButton);

		
		if (ele.waitForElementVisible(successMessg, AppConstants.DEFAULT_MEDIUM_WAIT).getText().contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
			ele.doClick(logoutLink);
			ele.doClick(registerLink);
			return true;
		}
		return false;

	}
	
	
}
