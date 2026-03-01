package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void SearchPageSetup()
	{
		accPage=lt.doLogin(prop.getProperty("username"),prop.getProperty("password"));

	}
	
	
	@Test
	public void doSearch()
	{
		searchPage=accPage.doSearch("macbook");
		productpage=searchPage.doProductSelect("MacBook Pro");
		String header=productpage.getProductHeader();
		Assert.assertEquals(header, "MacBook Pro");
	}
	
	@Test
	public void productCount()
	{
		int actual_products=searchPage.getSearchResults();
		Assert.assertEquals(actual_products, 3);
	}
	
	@Test
	public void getsearchHeader()
	{
		searchPage=accPage.doSearch("macbook");
		String header=searchPage.getSearchHeader();
		Assert.assertEquals(header, "Search - macbook");
	}
	
}
