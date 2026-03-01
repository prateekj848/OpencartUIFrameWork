package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.AppConstants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void AccPageSetup()
	{
		accPage=lt.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test
	public void AccPageHeadersTest()
	{
		List<String> actual_headers = accPage.getAccPageHeaders();
		Assert.assertEquals(actual_headers.size(),AppConstants.APP_PAGE_HEADER_COUNT);
		Assert.assertEquals(actual_headers, AppConstants.expected_acc_page_header_list);
	}
	
	@Test
	public void AccountPageLogoutLink()
	{
		Assert.assertTrue(accPage.IsAccountPageLogoutLink());
	}
	
}
