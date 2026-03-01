package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	protected WebDriver driver;
	protected ElementUtil ele;
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		ele=new ElementUtil(driver);
	}
	
	
	private final By SearchResults=By.cssSelector("div.product-thumb h4");
	private final By SearchHeader=By.cssSelector("div#content h1");
	
	public int getSearchResults()
	{
		List<WebElement> allresult=ele.waitForAllElementsPresence(SearchResults, AppConstants.DEFAULT_SHORT_WAIT);
		for(WebElement e:allresult)
		{
			String name=e.getText();
			System.out.println(name);
		}
		
		int count=allresult.size();
		System.out.println("Total Product :"+count);
		return count;
		
		
	}
	
	
	public String getSearchHeader()
	{
		String header=ele.waitForElementVisible(SearchHeader, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Headers name====> "+header);
		return header;
	}
	
	public ProductInfoPage doProductSelect(String name)
	{
		//ele.waitForElementPresence(SearchHeader, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("Product name===>"+name);
		ele.doClick(By.linkText(name));
		return new ProductInfoPage(driver);
	}
	
	
}
