/**
 * 
 */
package com.odt.testcases;

import org.testng.annotations.Test;

import com.odt.pages.Facebook_SignUp_page;
import com.odt.pages.Facebook_login_page;
import com.odt.wrapper.ApplicationWarppers;


/**
 * @author david
 *@13-Oct-2018
 */
public class UI_Validation_Testcases extends ApplicationWarppers
{
	
	
	@Test(dataProvider="fetchTestData")
	public void facebook_Login_TC001(String userName, String passWord)
	{	
		
		System.out.println("UserName : "+userName);
		System.out.println("Password : "+passWord);
		new Facebook_login_page()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin();
	}
	
	
	@Test(dataProvider="fetchTestData")
	public void facebook_Login_TC002(String userName, String passWord,String firstName)
	{	
		
		System.out.println("In TC002 METHOD");
		new Facebook_login_page()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickSignUp()
		.enterFirstName(firstName);
	}
	
	@Test
	public void Demo_TC_TC003()
	{	
		new Facebook_SignUp_page()
		.demo();
	}
	
	
	
	
	
	

}
