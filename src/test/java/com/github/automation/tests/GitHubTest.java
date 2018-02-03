package com.github.automation.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.automation.objects.CreateIssue;
import com.github.automation.objects.GitHubHome;
import com.github.automation.objects.GitHubLogin;
import com.github.automation.objects.GitHubUserCreateRepo;
import com.github.automation.objects.GitHubUserHomeNoRepos;
import com.github.automation.objects.GitHubUserRepository;
import com.github.automation.objects.IssuesTab;
import com.github.automation.objects.ParticularIssue;
import com.github.automation.utils.Constants;
import com.github.automation.utils.Password;
import com.github.automation.utils.Username;

/**
 * GitHub tests
 */
public class GitHubTest {
	private Random randomNumber;
	private String repoName = null;
	private WebDriver driver;
	private GitHubHome home;
	private GitHubLogin login;
	private GitHubUserHomeNoRepos userHomeWithNoRepos;
	private GitHubUserCreateRepo userCreateRepo;
	private GitHubUserRepository userRepository;
	private IssuesTab issueTab;
	private CreateIssue createIssuePage;
	private ParticularIssue particularIssuePage;

	/**
	 * This method aims to perform cross browser check
	 * 
	 * @param browser
	 *            Parameter passed for different browsers
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		/* Check if parameter passed from TestNG is 'firefox' */
		if (browser.equalsIgnoreCase("firefox")) {
			/* create firefox instance */
			System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		/* Check if parameter passed as 'chrome' */
		else if (browser.equalsIgnoreCase("chrome")) {
			/* create chrome instance */
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			throw new Exception("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testGitHubLoggedInUserCreatingRepositoryAndNewIssue() throws InterruptedException {
		/* Browse to GitHub home page: https://github.com/ */
		home = new GitHubHome(driver);
		home.open();

		/* Click the "Sign in" link */
		home.login();

		/* Verify the title of Login page */
		login = new GitHubLogin(driver);
		Assert.assertEquals(login.getTitle(), Constants.GITHUB_LOGIN_TITLE);

		/* Sign in with user credential */
		login.signIn(Username.USER1, Password.USER1);

		/* Verify the title of User home page */
		userHomeWithNoRepos = new GitHubUserHomeNoRepos(driver);
		Assert.assertEquals(userHomeWithNoRepos.getTitle(), Constants.USER_HOME_TITLE);

		/* Verify username */
		Assert.assertEquals(userHomeWithNoRepos.getLoggedInUsername(), Username.USER1.getUsername());

		/* Click "New repository" button */
		userHomeWithNoRepos.createRepoPlusMenu();

		/* Verify the title of User Create Repo page */
		userCreateRepo = new GitHubUserCreateRepo(driver);
		Assert.assertEquals(userCreateRepo.getTitle(), Constants.CREATE_REPO_TITLE);

		/* Verify the heading of User Create Repo page */
		Assert.assertEquals(userCreateRepo.getCreateRepoPageSubtitle(), "Create a new repository");

		/* "Create repository" */
		randomNumber = new Random();
		repoName = Constants.REPOSITORY_NAME + randomNumber.nextInt(Integer.MAX_VALUE);
		userCreateRepo.createRepo(repoName, Constants.REPOSITORY_DETAILS + randomNumber.nextInt(100));

		/* Verify Repository name link */
		userRepository = new GitHubUserRepository(driver);
		Assert.assertEquals(userRepository.getRepositoryName(), repoName);

		/* Click "Issues" tab */
		userRepository.clickOnIssuesTab();

		/* Verify the heading of Issues tab */
		issueTab = new IssuesTab(driver);
		Assert.assertEquals(issueTab.getIssuesPageHeading(), Constants.ISSUES_SUBTITLE);

		/* Click "New issue" button */
		issueTab.clickOnNewIssueBtn();

		/* Verify Create Issue Page title */
		createIssuePage = new CreateIssue(driver);
		Assert.assertTrue(createIssuePage.getTitle().contains(Constants.CREATE_ISSUE_PAGE_TITLE));

		/* Create issue */
		createIssuePage.createNewIssue(Constants.ISSUE_NAME, Constants.ISSUE_DETAILS);

		/* Verify Particular issue page title */
		particularIssuePage = new ParticularIssue(driver);
		Assert.assertTrue(particularIssuePage.getTitle().contains(Constants.ISSUE_NAME));
		Assert.assertEquals(particularIssuePage.getIssuesPageHeading(),Constants.ISSUE_NAME );
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
