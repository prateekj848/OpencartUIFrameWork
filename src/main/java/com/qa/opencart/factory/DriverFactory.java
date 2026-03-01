package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;

	public static String highlight;
	
	public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<WebDriver>();
	/**
	 * This function is used to intialise the driver
	 * @param prop
	 * @return driver instance
	 */
	public WebDriver initDriver(Properties prop) {
		String browser=prop.getProperty("browser");
		System.out.println("Browser :" + browser);
		
		optionsManager = new OptionsManager(prop);

		highlight=prop.getProperty("highlight");
		
		
		switch (browser.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver();
			tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Invalid browser : " + AppErrors.INVALID_BROWSER_MSG);
			throw new FrameworkException("=====INVALID BROWSER========");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * This function is used initialise the prop
	 * @return prop
	 */
	
	public static WebDriver getDriver()
	{
		return tldriver.get();
	}
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

//	public void setup(String url)
//	{
//		driver.get(url);
//	}
	
	
	public static File getScreenshotMyFile() {
		
		TakesScreenshot ts= (TakesScreenshot)getDriver();
		File fs=ts.getScreenshotAs(OutputType.FILE);
		return fs;
		
	}
	
	
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
	
	
	
	
	
	
	

}
