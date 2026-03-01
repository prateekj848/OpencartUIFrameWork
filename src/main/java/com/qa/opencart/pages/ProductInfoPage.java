package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	protected WebDriver driver;
	protected ElementUtil ele;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		ele=new ElementUtil(driver);
	}
	
	
	private final By SearchHeader=By.tagName("h1");
	private final By Images=By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap;

	public String getProductHeader()
	{
		String header=ele.waitForElementVisible(SearchHeader, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println("Headers name====> "+header);
		return header;
	}
	
	
	public int getImageCount()
	{
		int count=ele.waitForAllElementsPresence(Images, AppConstants.DEFAULT_SHORT_WAIT).size();
		return count;
	}
	
	
	
	public Map<String, String> getProductDetailsMap() {
		//productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		productMap = new TreeMap<String, String>();
		
		productMap.put("productheader", getProductHeader());
		productMap.put("productimages", String.valueOf(getImageCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Full product details: " + productMap);
		return productMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> MetaList = ele.waitForAllElementsVisible(productMetaData, AppConstants.DEFAULT_SHORT_WAIT);
		for(WebElement e : MetaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList = ele.waitForAllElementsVisible(productPriceData, AppConstants.DEFAULT_SHORT_WAIT);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", exTaxPrice);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
