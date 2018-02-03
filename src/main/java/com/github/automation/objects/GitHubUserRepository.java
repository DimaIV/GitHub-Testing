package com.github.automation.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GitHubUserRepository {

	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//h1//a")
	private WebElement userNameLink;
	
	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//h1/strong/a")
	private WebElement repositoryNameLink;
	
	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//span[.='Code']")
	private WebElement repositoryCodeTab;
	
	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//span[.='Issues']")
	private WebElement repositoryIssuesTab;
	
	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//span[.='Pull requests']")
	private WebElement repositoryPullRequestsTab;

	public GitHubUserRepository(WebDriver webDriver) {
		driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	public String getUserName(){
		return userNameLink.getText();
	}
	
	public String getRepositoryName(){
		return repositoryNameLink.getText();
	}
	
	public void clickOnIssuesTab(){
		repositoryIssuesTab.click();
	}
	


}
