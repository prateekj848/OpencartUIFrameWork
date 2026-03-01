package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageURLTest()
	{
		String act_url=lt.getLoginPageURL();
		Assert.assertTrue(act_url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	
	@Test
	public void loginPageTitleTest()
	{
	    String act_title=lt.getLoginPageTitle();
		ChainTestListener.log("checking login page title: "+ act_title);

	    Assert.assertEquals(act_title, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void isLoginPageHeaderExistTest()
	{
		boolean header=lt.IsLoginPageHeaderExist();
		Assert.assertTrue(header);
	}
	
	@Test
	public void isForgotPwdLinkExistTest()
	{
		boolean forgot_link=lt.IsLoginPageForgotLinkExist();
		Assert.assertTrue(forgot_link);
	}
	
	@Test
	public void loginTest()
	{
		accPage=lt.doLogin(prop.getProperty("username"),prop.getProperty("password"));
         Assert.assertTrue(accPage.IsAccountPageLogoutLink());
		//		System.out.println(st);
//		Assert.assertEquals(st, "My Account");
	}
	
	
	
	
}
