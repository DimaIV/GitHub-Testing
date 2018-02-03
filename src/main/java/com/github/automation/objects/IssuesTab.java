package com.github.automation.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public class IssuesTab {

	private WebDriver driver;
	@FindBy(how = How.XPATH, using = "//*[@id='js-repo-pjax-container']//a[contains(text(), 'New issue')]")
	private WebElement newIssueBtn;

	public IssuesTab(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

public String getIssuesPageHeading(){
		return WaitForSomeElementCondition.waitForElement(driver,
				By.xpath("//*[@id='js-repo-pjax-container']//h4"), Constants.TIMEOUT_SECONDS).getText();
	}

public void clickOnNewIssueBtn(){
	WaitForSomeElementCondition.waitForElementToBEClickable(driver, newIssueBtn,Constants.TIMEOUT_SECONDS);
	newIssueBtn.click();
}

}

