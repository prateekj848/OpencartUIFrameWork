package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	protected WebDriver driver;
	private ElementUtil eleutil;

	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);

	}
	
	protected final By headers = By.xpath("//div[@id='content']/h2");
	protected final By logoutlink = By.linkText("Logout");
	protected final By search=By.name("search");
	protected final By searchIcon=By.cssSelector("div#search button");
	
	public boolean IsAccountPageLogoutLink()
	{
		return eleutil.isElementDisplayed(logoutlink);
		//return driver.findElement(logoutlink).isDisplayed();
	}
	
	public List<String> getAccPageHeaders()
	{
		List<WebElement> ele=eleutil.waitForAllElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
		//List<WebElement> ele=driver.findElements(headers);
		System.out.println("Total Headers : "+ ele.size());
		List<String> head= new ArrayList<String>();
		
	    for(WebElement e:ele)
	    {
	    	String name=e.getText();
	    	head.add(name);
	    }
	    return head;
	}
	
	
	public SearchResultsPage doSearch(String searchitem)
	{
		
		WebElement item=eleutil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_WAIT);
		item.clear();
		System.out.println("Search item===>"+searchitem);
		item.sendKeys(searchitem);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
//		driver.findElement(search).sendKeys(searchitem);
//		driver.findElement(searchIcon).click();
	}
	
}
