package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void doProductSetup()
	{
		accPage=lt.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getProduct()
	{
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
		};
	}
	
	
	@Test(dataProvider="getProduct")
	public void ProductHeader(String searchKey,String productName)
	{
		searchPage= accPage.doSearch(searchKey);
		productpage =searchPage.doProductSelect(productName);
		String header=productpage.getProductHeader();
		Assert.assertEquals(header, productName);
		
	}
	
	
	@DataProvider
	public Object[][] getProductImage()
	{
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3}
		};
	}
	
	
	@Test(dataProvider="getProductImage")
	public void ProductImageCountTest(String searchKey,String productName,int imgcount)
	{
		searchPage= accPage.doSearch(searchKey);
		productpage =searchPage.doProductSelect(productName);
		int img=productpage.getImageCount();
		Assert.assertEquals(img, imgcount);
	}
	
	
	@Test
	public void TotalImageCount()
	{
		int img=productpage.getImageCount();
		Assert.assertEquals(img, 3);
	}
	
	
	@DataProvider
	public Object[][] getProductCSVData() {
		return CsvUtil.csvData("product");
	}
	
	@Test(dataProvider = "getProductCSVData")
	public void productImageCountTest(String searchKey, String productName, String expectedImageCount) {
		searchPage =  accPage.doSearch(searchKey);
		productpage = searchPage.doProductSelect(productName);
		int actImageCount = productpage.getImageCount();
		Assert.assertEquals(String.valueOf(actImageCount), expectedImageCount);
	}
	
	
	@Test
	public void productInfoTest() {
		searchPage =  accPage.doSearch("macbook");
		productpage = searchPage.doProductSelect("MacBook Pro");
		Map<String, String> actualProductDetailsMap = productpage.getProductDetailsMap();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");//P
		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");//P
		softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");//P
		softAssert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");//P
		softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");//P
		
		softAssert.assertAll();
	}
	
	
}
