package com.github.automation.objects;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.automation.utils.Constants;
import com.github.automation.utils.Password;
import com.github.automation.utils.Username;
import com.github.automation.utils.waits.WaitForSomeElementCondition;

public class GitHubLogin {

	private WebDriver driver;

	@FindBy(how = How.ID, using = "login_field")
	private WebElement userID;

	@FindBy(how = How.ID, using = "password")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//input[@type='submit' or @value='Sign in']")
	private WebElement signInBtn;

	public GitHubLogin(WebDriver webDriver) {
		driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void setUserID(Username userID) {
		this.userID.sendKeys(userID.getUsername());
	}

	public void setPassword(Password password) {
		this.password.sendKeys(password.getPassword());
	}

	public String getTitle() {
		WaitForSomeElementCondition.waitForPageTitle(driver, Constants.GITHUB_LOGIN_TITLE, Constants.TIMEOUT_SECONDS);
		return driver.getTitle();
	}

	/**
	 * Sign in with username and password
	 **/
	public void signIn(Username userName, Password password) {
		if (getTitle().contains(Constants.GITHUB_LOGIN_TITLE)) {
			setUserID(userName);
			setPassword(password);
			this.signInBtn.click();
		}else{
			fail("Login page is not loaded!");
		}

	}

}
