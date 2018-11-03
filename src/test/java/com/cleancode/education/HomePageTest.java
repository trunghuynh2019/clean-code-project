package com.cleancode.education;

import org.junit.Test;

public class HomePageTest extends BaseTest {
	
	 @Test
	 public void testGotoHomePage () throws InterruptedException {
		 HomePage homePage = new HomePage(driver, wait);
		 homePage.gotoHomePage();
	 }
	

}
