/**
 * 
 */
package com.odt.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.odt.wrapper.ApplicationWarppers;

/**
 * @author david
 *@12-Oct-2018
 */
public class BasePage extends ApplicationWarppers {
	
	public BasePage(RemoteWebDriver remoteDriver) {
		driver = remoteDriver;
		PageFactory.initElements(driver, this);
	}

}
