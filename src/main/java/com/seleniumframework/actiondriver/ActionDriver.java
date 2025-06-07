package com.seleniumframework.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private void waitForTheElementToBeClickable(By by) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The element is not clickable" + e.getMessage());
		}
	}

	public void click(By by) {

		try {
			waitForTheElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("the element is not clickable " + e.getMessage());
		}
	}

	public void sendKey(By by, String str) {
		try {
			waitForTheVisibilityOfTheElement(by);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(str);
		} catch (Exception e) {
			System.out.println("Unable to send text to the input box" + e.getMessage());
		}
	}

	public String getText(By by) {
		String string = "";
		try {
			waitForTheVisibilityOfTheElement(by);
			string = driver.findElement(by).getText();

		} catch (Exception e) {
			System.out.println("Unable to get text" + e.getMessage());
		}
		return string;

	}

	public void compareText(By by, String actualString) {

		if (actualString.equals(getText(by))) {
			System.out.println(" the text is matching");
		}else {
			System.out.println(" the text is not matching");
		}
	}

	public boolean isDisplayed(By by) {
		
		boolean bool =false;
		try {
			waitForTheVisibilityOfTheElement(by);
			bool=driver.findElement(by).isDisplayed();
			if(bool) System.out.println("the element is displayed");
			else System.out.println(" the element is not displayed");
		}catch(Exception e) {
			System.out.println("Unable to check if the element is displayed"+e.getMessage());
			
		}
		return bool;
			
	}
	public void scrollToView(By by) {
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		
		try {
			jsExecutor.executeScript("arguments[0].scrollIntoView({behaviour:'smooth',block:'center'})", driver.findElement(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void waitForTheVisibilityOfTheElement(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The element is not visible" + e.getMessage());
		}
	}
}
