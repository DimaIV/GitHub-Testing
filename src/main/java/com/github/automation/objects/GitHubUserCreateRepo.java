package com.github.automation.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public class GitHubUserCreateRepo {
	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//button[@type='button']")
	private WebElement ownerDropDown;

	@FindBy(how = How.XPATH, using = "//input[@id='repository_name']")
	private WebElement repoNameField;

	@FindBy(how = How.XPATH, using = "//input[@id='repository_description']")
	private WebElement repoDetailsField;

	@FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(text(),'Create repository')]")
	private WebElement createRepoBtn;

	public GitHubUserCreateRepo(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public String getTitle() {
		WaitForSomeElementCondition.waitForPageTitle(driver, Constants.CREATE_REPO_TITLE, Constants.TIMEOUT_SECONDS);
		return driver.getTitle();
	}

	public String getCreateRepoPageSubtitle(){
		return WaitForSomeElementCondition.waitForElement(driver, 
				By.xpath("//h2[contains(text(),'Create a new repository')]"), Constants.TIMEOUT_SECONDS).getText();
	}

	public void createRepo(String repoName, String repoDetails){
		this.setRepoNameField(repoName);
		this.setRepoDetailsField(repoDetails);
		clickOnCreateRepoBtn();
	}

	private void setRepoNameField(String repoNameField) {
		this.repoNameField.sendKeys(repoNameField);
	}

	private void setRepoDetailsField(String repoDetailsField) {
		this.repoDetailsField.sendKeys(repoDetailsField);
	}

	private void clickOnCreateRepoBtn() {
	 WaitForSomeElementCondition.waitForElementToBEClickable(driver, createRepoBtn, Constants.TIMEOUT_SECONDS);
		createRepoBtn.click();
	}

}
