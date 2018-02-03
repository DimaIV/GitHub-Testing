package com.github.automation.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public abstract class GitHubUserHome {
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//header[contains(@class, 'Header')]")
	private WebElement header;
	
	@FindBy(how = How.XPATH, using = "//summary[contains(@class, 'HeaderNavlink')][1]")
	private WebElement userPlusMenu;

	@FindBy(how = How.XPATH, using = "//img[contains(@class, 'avatar')]")
	private WebElement userAvatar;

	@FindBy(how = How.LINK_TEXT, using = "Read the guide")
	private WebElement readGuideBtn;

	@FindBy(how = How.LINK_TEXT, using = "Start a project")
	private WebElement startProjectBtn;

	public GitHubUserHome(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public String getTitle() {
		WaitForSomeElementCondition.waitForPageTitle(driver, Constants.USER_HOME_TITLE, Constants.TIMEOUT_SECONDS);
		return driver.getTitle();
	}

	public String getLoggedInUsername() {
		clickOnAvatarMenu();
		String username = WaitForSomeElementCondition
				.waitForElement(driver, By.xpath("//strong[@class='css-truncate-target']"), Constants.TIMEOUT_SECONDS).getText();
		header.click();
		return username;
	}

	private void clickOnAvatarMenu() {
		userAvatar.click();
	}

	public void createRepoPlusMenu() {
		clickOnPlusMenu();
		WebElement createRepoBtn = WaitForSomeElementCondition
				.waitForElement(driver,
						By.xpath("//a[contains(text(), 'New repository') or @class='dropdown-item']"),
						Constants.TIMEOUT_SECONDS);
		createRepoBtn.click();
	}

	private void clickOnPlusMenu() {
		userPlusMenu.click();
		
	}

}
