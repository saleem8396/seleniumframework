package com.seleniumframework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() throws IOException {

		Properties prop = new Properties();
		FileInputStream fiStream = new FileInputStream("src\\main\\resources\\config.properties");
		prop.load(fiStream);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("The browser is not supported");
		}

		driver.manage().window().maximize();
		driver.get(url);

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}


