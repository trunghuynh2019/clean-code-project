package com.cleancode.education;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class SearchResultPage extends HomePage {
	
	
	public SearchResultPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="/html/body/p[1]/table[2]/tbody/tr/td[1]/table[1]/tbody/tr/td/table[1]/tbody/tr[1]/td[1]/font")
	WebElement numberOfGamesContainer;
	
	public void checkNumberOfGames() {
		assertThat(numberOfGamesContainer.getText(), containsString("2,386"));
		System.out.println("OK, this is Kasparov");
	}
	
	
}
