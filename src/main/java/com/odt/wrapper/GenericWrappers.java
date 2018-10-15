/**
 * 
 */
package com.odt.wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.odt.utils.Reporter;


/**
 * @author david
 *@12-Oct-2018
 */
public class GenericWrappers {
	
	Logger logger = Logger.getRootLogger();
	
	public static RemoteWebDriver driver;
	protected static Properties prop;
	public static String textGetFromElement="";
	public static WebDriverWait wait;
	public static String path = "config.properties";
	public static WebDriverWait expwait;
	
	

	
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./config.properties")));
	
	}
	
	
	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author David
	 * @param url - The url with http or https
	 * 
	 */
	public boolean invokeApp(String browser) {
		boolean bReturn = false;
		try {

			if(browser.equalsIgnoreCase("chrome"))
			{

				System.setProperty("webdriver.chrome.driver", "./Dependencies/chromedriver");
				driver = new ChromeDriver();
		
			//driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(getDataFromPropertiesFile("config.properties", "appUrl"));
		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;
			}
			else
			{
				System.out.println("Invalid Browser...");
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	
	
	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author David
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(WebElement idValue, String data) {
		boolean bReturn = false;
		try {
			idValue.clear();
			idValue.sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		}
		return bReturn;
	}
	
	
	/**
	 * This method will enter the value to the text field using xpath attribute to locate
	 * 
	 * @param xpathValue - xpath of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author David
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	
	public boolean enterByXpath(WebElement xpathValue, String data) {
		boolean bReturn = false;
		try {
			System.out.println("Xpath : "+xpathValue);
			System.out.println("Data : "+data);
			xpathValue.clear();
			xpathValue.sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+xpathValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+xpathValue, "FAIL");
		}
		return bReturn;
	}


	public boolean enterByname(String name, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.name(name)).clear();
			driver.findElement(By.name(name)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+name, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+name, "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author David
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author David
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}


		return bReturn;
	}
	
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author David
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL");
		}


		return bReturn;
	}

	/**
	 * This method will close all the browsers
	 * @author David
	 */
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author David
	 */
	public boolean clickById(WebElement id) {
		boolean bReturn = false;
		try{
			id.click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author David
	 */
	public boolean clickByClassName(String classVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author David
	 */
	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author David
	 */
	public boolean clickByLink(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author David
	 */
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author David
	 */
	public boolean mouseOverByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author David
	 */
	public boolean mouseOverByLinkText(String linkName) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}

	public String getTextByXpath_to_Write(String xpathVal) throws Exception
	{

		try {
			textGetFromElement = driver.findElement(By.xpath(xpathVal)).getText();
			//System.out.println("In gettext method : " + textGetFromElement);
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return textGetFromElement;
	}

	
	public void write_The_Gettext(String xpathVal)
	{
		try {
			//System.out.println("In writetext method : " + textGetFromElement);
			driver.findElement(By.xpath(xpathVal)).sendKeys(textGetFromElement);
		} catch (Exception e) {
			Reporter.reportStep("The Text get using getTextByXpath_to_Write() method is could not entered into the element with Xpath : " +xpathVal, "FAIL");
		}
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author David
	 */
	public boolean selectById(String id, String value) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
		return bReturn;
	}
	
	
	
	
	
	/**
	 * This method will sleep for particular given time
	 * 
	 * @param time
	 */
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method will wait for the presence of the element to be located
	 * 
	 * @param locator
	 */
	public void waitForpresenceOfElementLocated(By locator) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	

	/**
	 * This method will wait for the invisibility of the Element located
	 * 
	 * @param locator
	 */
	public void waitForinvisiblityOfElementLocated(By locator) {
		wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}


	/**
	 * This method will wait for the visibility of the given cssSelector web
	 * Element
	 * 
	 * @param locator
	 */
	public void waitForvisiblityOfGivenCssselector(String locator) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("." + locator))));

	}

	
	/**
	 * This method will wait for the visibility of the given WebElement
	 * 
	 * @param ele
	 */
	public void waitForvisiblityOfGivenElement(WebElement ele) {
		wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOf(ele));

	}	
	
	
	public void specificImplicitWaitInSeconds(long specify_in_seconds) {
		driver.manage().timeouts().implicitlyWait(specify_in_seconds, TimeUnit.SECONDS);
	}

	/**
	 * This method will wait for the given WebElement to be clickable
	 * 
	 * @param ele
	 */
	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 360);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	
	public void assertTextValueOfElementIsNotNull(WebElement ele) {
		Assert.assertTrue(ele.getText() != null);
	}

	public void assertValueOfTheElementIsSame(WebElement ele, String value) {
		String actualValue = ele.getText();
		Assert.assertTrue(actualValue.equalsIgnoreCase(value));
	}

	public static long randomNumberGenerator() {
		return (long) Math.floor(Math.random() * 900000000L) + 10000000L;
	}

	
	
	
	
	
	
	/**
	 *  This Method Will Set The Data In The Property
	 * File For A Particular Variable
	 * 
	 * @param key,
	 *            value
	 */
	public void setDataToPropertiesFile(String path, String key, String value) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration(path);
			conf.setProperty(key, value);
			conf.save();
		} catch (ConfigurationException e) {
			System.out.println("Application Reference Number Not Stored...");
		}
	}

	/**
	 *  This Method Will Get The Data From The Property
	 * File For A Particular Variable
	 * 
	 * @param key,
	 *            value
	 * @return
	 */
	public String getDataFromPropertiesFile(String path, String key) {
		String keyVal = null;
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration(path);
			keyVal = (String) conf.getProperty(key);
		} catch (ConfigurationException e) {
			System.out.println("Application Reference Number Not Stored...");
		}
		return keyVal;
	}

	
	
	
	
	
	/**
	 * This method will switch to new window
	 * 
	 * @author David
	 * @param data
	 */
	public void windowSwitch() {
		sleep(2000);
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			driver.switchTo().window(handle);

		}
	}

	/**
	 * This method will close the browser
	 * 
	 * @author David
	 * @param data
	 */
	public void closeBrowser() throws InterruptedException {

		driver.quit();
	}

	/**
	 * 
	 *  
	 * 
	 * @param ele
	 * 
	 *            This method will Assert whether the element is disabled
	 * @return
	 */
	public boolean assertFalseElementIsEnabled(WebElement ele) {
		boolean checkElementState = ele.isEnabled();
		Assert.assertFalse(ele.isEnabled());
		return checkElementState;

	}

	/**
	 * 
	 * 
	 * 
	 * @param ele
	 * 
	 *            This method will Assert whether the element is enabled
	 */

	public void assertTrueElementIsEnabled(WebElement ele) {
		Assert.assertTrue(ele.isEnabled());

	}

	/**
	 * 
	 *
	 * 
	 * @param secondsValue
	 * @param elementLocator
	 * @throws Throwable
	 */
	public void fluentlyWaitForElementToBeClickableAndClick(long secondsValue, WebElement elementLocator)
			throws Throwable {
		expwait = new WebDriverWait(driver, secondsValue);
		WebElement buttonToClick = expwait.until(ExpectedConditions.elementToBeClickable(elementLocator));
		buttonToClick.click();
	}

	
	
	
	public void jsClickOnGivenElement(String element) {
		((JavascriptExecutor) driver).executeScript(element);
	}

	public void jsScrollForGivenElement(String str, WebElement element) {
		((JavascriptExecutor) driver).executeScript(str, element);
	}

	public void loggerInfo(String info) {
		logger.info(info);
	}

	/**
	 * This Method will MouseOver the given Element
	 * 
	 * @param ele
	 * @author david 
	 */
	public void mouseOverElement(WebElement ele) {

		System.err.println("Warning : Mouse Hover in progress...Manual interaction will fail the TestCase");
		sleep(3000);
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).build().perform();
	}
	
	
	public void scrollToElement(WebElement ele){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		sleep(1000);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean alertAccept(){
		boolean bReturn = false;

		try {
			driver.switchTo().alert().accept();
			bReturn = true;
		} catch (Exception e) {

		}
		return bReturn;

	}

	public String getTextAndAcceptAlert(){
		String sText = "";
		try{
			sText = driver.switchTo().alert().getText();
			alertAccept();
		} catch(Exception e){

		}
		return sText;
	}
	
	public boolean switchToLastWindow() {


		boolean bReturn = false;
		try {

			Set<String> allWindows = driver.getWindowHandles();

			for (String str : allWindows) {
				driver.switchTo().window(str);
			}

			bReturn = true;
		} catch (Exception e) {

		}

		return bReturn;

	}

	public boolean switchToPrimary() {
		boolean bReturn = false;
		try {

			
			Set<String> allWindows = driver.getWindowHandles();

			for (String str : allWindows) {
				driver.switchTo().window(str);
				break;
			}
			/*String primaryWindowHandle = driver.getWindowHandle();
			driver.switchTo().window(primaryWindowHandle);*/

			bReturn = true;

		} catch (Exception e) {

		}
		return bReturn;
	}


}
