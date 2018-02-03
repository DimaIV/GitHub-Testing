package com.github.automation.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public class ParticularIssue {

	private WebDriver driver;
	@FindBy(how = How.XPATH, using = "//*[@id='partial-discussion-header']//button[.='Edit']")
	private WebElement editIssueBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='partial-discussion-header']//a[contains(text(), 'New issue')]")
	private WebElement newIssueBtn;

	public ParticularIssue(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public String getTitle() {
		WaitForSomeElementCondition.waitForPageTitle(driver, Constants.PARTICULAR_ISSUE_PAGE_TITLE, Constants.TIMEOUT_SECONDS);
		return driver.getTitle();
	}

	public String getIssuesPageHeading() {
		return WaitForSomeElementCondition
				.waitForElement(driver, By.xpath("//h1/span[contains(@class, 'js-issue-title')]"), Constants.TIMEOUT_SECONDS)
				.getText();
	}

}
