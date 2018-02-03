package com.github.automation.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GitHubUserHomeWithRepos extends GitHubUserHome {

	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Your repositories')]")
	private WebElement userReposTitle;

	@FindBy(how = How.XPATH, using = "//div[@id='your_repos']//a[contains(text(),'New repository')]")
	private WebElement createRepoBtn;

	public GitHubUserHomeWithRepos(WebDriver webDriver) {
		super(webDriver);
	}
	
}
