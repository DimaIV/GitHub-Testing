package com.github.automation.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;

public class GitHubHome {
	
	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//a[contains(@href, '/login')]")
	private WebElement signInBtn;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign up")
	private WebElement signUPBtn;

	public GitHubHome(WebDriver webDriver) {
		driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	public void open() {
		driver.get(Constants.HOME_URL);
	}
	/* Click on login button. */
	public void login(){
        signInBtn.click();
	}
}
