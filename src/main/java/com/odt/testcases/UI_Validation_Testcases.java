/**
 * 
 */
package com.odt.testcases;

import org.testng.annotations.Test;

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
		new Facebook_login_page()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin();
	}
	
	
	
	
	
	
	
	
	

}
