package com.cleancode.education;

import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	String baseURL = "http://www.chessgames.com/";
	
	@FindBy(name="search")
	WebElement search;
	
	@FindBy(css="input[type='submit']")
	WebElement submitButton;
	

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	
	public void gotoHomePage () throws InterruptedException{
        
		driver.get(baseURL);
        search.sendKeys("Kasparov");
        submitButton.click();
        wait.withTimeout(Duration.ofMillis(5000));
        
        SearchResultPage searchResultPage = new SearchResultPage(driver, wait);
        searchResultPage.checkNumberOfGames();
        
    }

}
