/**
 * 
 */
package com.odt.pages;

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
	
	@FindBy(id="email")
	WebElement userName;
	
	@FindBy(id="pass")
	WebElement passWord;
	
	@FindBy(xpath="//form[@id='login_form']/table/tbody/tr[2]/td[3]/label/input")
	WebElement loginButton;
	
	
	
	public Facebook_login_page enterUserName(String userNameText)
	{	
		enterById(userName, userNameText);
		
		return this;
	}
	
	public Facebook_login_page enterPassword(String passwordText)
	{	
		enterById(passWord, passwordText);
		
		return this;
	}

	
	public Facebook_login_page clickLogin()
	{	
		clickById(loginButton);
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
