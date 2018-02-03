package com.github.automation.utils.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForSomeElementCondition {

	/**
	 * This method aims to wait for an element to be present and returns the
	 * first WebElement.
	 * 
	 * @param WebDriver
	 *            The driver object to be used
	 * @param By
	 *            selector to find the element
	 * @param int
	 *            The time in seconds to wait until returning a failure
	 * 
	 * @return WebElement 
	 * 			  The first WebElement or null if timeout is reached
	 */
	public static WebElement waitForElement(WebDriver driver, final By by, int timeOutInSeconds) {

		WebElement element;

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void waitForPageTitle(WebDriver driver, String pageTitle, int timeOutInSeconds){
		try {
			 WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			  wait.until(ExpectedConditions.titleContains(pageTitle));
		}catch (Exception e) {
			e.printStackTrace();
		}		 
	}
	
	public static WebElement waitForElementToBEClickable(WebDriver driver, WebElement element, int timeOutInSeconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
