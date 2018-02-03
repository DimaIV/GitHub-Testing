package com.github.automation.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public class CreateIssue {
	private WebDriver driver;
	@FindBy(how = How.XPATH, using = "//*[@id='issue_title']")
	private WebElement newIssueTitleField;

	@FindBy(how = How.XPATH, using = "//*[@id='issue_body']")
	private WebElement leaveComment;

	@FindBy(how = How.XPATH, using = "//*[@id='new_issue']//button[contains(text(),'Submit new issue')]")
	private WebElement submitIssueBtn;

	public CreateIssue(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public String getTitle() {
		WaitForSomeElementCondition.waitForPageTitle(driver, Constants.CREATE_ISSUE_PAGE_TITLE, Constants.TIMEOUT_SECONDS);
		return driver.getTitle();
	}

	public void createNewIssue(String newIssueTitle, String issueComment) {
		this.setNewIssueTitleField(newIssueTitle);
		this.setLeaveComment(issueComment);
		clickOnSubmitIssueBtn();
	}

	private void clickOnSubmitIssueBtn() {
		WaitForSomeElementCondition.waitForElementToBEClickable(driver, submitIssueBtn, Constants.TIMEOUT_SECONDS);
		submitIssueBtn.click();
	}

	private void setNewIssueTitleField(String newIssueTitleField) {
		this.newIssueTitleField.sendKeys(newIssueTitleField);
	}

	private void setLeaveComment(String leaveComment) {
		this.leaveComment.sendKeys(leaveComment);
	}

}
