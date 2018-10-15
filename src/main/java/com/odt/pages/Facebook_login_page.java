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
public class Facebook_login_page extends BasePage{
	
	
	public Facebook_login_page() {
		
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='email']")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='pass']")
	WebElement passWord;
	
	@FindBy(xpath="//form[@id='login_form']/table/tbody/tr[2]/td[3]/label/input")
	WebElement loginButton;
	
	
	
	public Facebook_login_page enterUserName(String userNameText)
	{	
		
		System.out.println("In username Method");
		//sleep(2000);
		enterByXpath(userName, userNameText);
		
		
		return this;
	}
	
	public Facebook_login_page enterPassword(String passwordText)
	{	
		//sleep(2000);
		enterByXpath(passWord, passwordText);
		
		return this;
	}

	
	public Facebook_login_page clickLogin()
	{	
		//sleep(2000);
		clickById(loginButton);
		sleep(2000);
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
