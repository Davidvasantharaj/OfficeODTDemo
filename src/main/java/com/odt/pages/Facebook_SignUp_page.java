/**
 * 
 */
package com.odt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author david
 *@13-Oct-2018
 */
public class Facebook_SignUp_page extends BasePage{
	
	
	public Facebook_SignUp_page() {
		
		super(driver);
		
	}
	
	@FindBy(xpath="(//a[text()='Sign Up'])[1]")
	WebElement signUp;
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement firstName;
	
	
	public Facebook_SignUp_page clickSignUp()
	{	
		
		//sleep(2000);
		clickByXpath(signUp);
		
		
		return this;
	}
	
	public Facebook_SignUp_page enterFirstName(String firstNam)
	{	
		
		System.out.println("firstNam : "+firstNam);
		sleep(2000);
		enterByXpath(firstName, firstNam);
		sleep(2000);
		
		return this;
	}
	
	
	public Facebook_SignUp_page demo()
	{
		
		System.out.println("*********Demo_TC_TC003***********");
		System.out.println("*********Demo_FIGHT FOR WHAT YOU WANT***********");
		System.out.println("*********IT'S NOT EASY BUT IT'S WORTH IT***********");
		
		return this;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
