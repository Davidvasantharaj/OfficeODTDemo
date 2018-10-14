/**
 * 
 */
package com.odt.wrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.odt.utils.DataInputProvider;
import com.odt.utils.Reporter;



/**
 * @author david
 *@12-Oct-2018
 */
public class ApplicationWarppers extends GenericWrappers{
	
	protected static String testCaseName;
	protected static String testDescription;
	protected static String testCaseGroup;
	protected String browserName;
	protected String excelFileName;
	protected String excelSheetName;
	
	
	
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		Reporter.startResult();
		loadObjects();
	}

	@BeforeTest
	public void beforeTest(){
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		Reporter.startTestCase();
		
		System.out.println("After StartTest case");

		invokeApp(getDataFromPropertiesFile("config.properties", "browserName"));
	}
		
	@AfterSuite
	public void afterSuite(){
		Reporter.endResult();
	}

	@AfterTest
	public void afterTest(){
		
	}
	
	@AfterMethod
	public void afterMethod(){
		quitBrowser();
	}
	
	
	
	@DataProvider(name="fetchTestData")
	public Object[][] getData(Method testName,ITestContext iTestContext)
	{
		excelFileName = getDataFromPropertiesFile("config.properties", "TestDataExcelName");
		
		System.out.println("Excel File Name : "+excelFileName);
		
		ITestNGMethod[] metodCnt = iTestContext.getAllTestMethods();
		String Env = "";
		
		for (int i = 0; i < metodCnt.length; i++) 
		{
			if (testName.getName().equalsIgnoreCase(metodCnt[i].getMethodName())) 
			{
				excelSheetName=testName.getName();
				
				System.out.println("Excel Sheet Name : "+excelSheetName);
				
				Map<String, String> k = metodCnt[i].findMethodParameters(iTestContext.getCurrentXmlTest());
				Env = k.get("Env");
				break;
			}
		}
		
		return DataInputProvider.getSheet(excelFileName,excelSheetName);		
	
		}
	
	
	
	
	
	
	


}
