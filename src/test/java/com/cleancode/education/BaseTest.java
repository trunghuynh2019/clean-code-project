package com.cleancode.education;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static final String chromePath = "/home/trung/Documents/chromedriver";

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		// Create a Chrome driver. All test and page classes use this driver.
		driver = new ChromeDriver();

		// Create a wait. All test and page classes use this wait.
		wait = new WebDriverWait(driver, 15);

		// Maximize Window
		driver.manage().window().setSize(new Dimension(1440, 900));

	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}
}
