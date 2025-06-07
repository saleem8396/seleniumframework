package com.seleniumframework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	WebDriverWait wait;
	protected static Properties prop ;
	
	@BeforeSuite
	public void propertyLoader() throws IOException{
		prop = new Properties();
		FileInputStream fiStream = new FileInputStream("src\\main\\resources\\config.properties");
		prop.load(fiStream);
	}
	
	public void launchBrowser() {
		
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		driver.manage().window().maximize();
		driver.get(url);
	}

	@BeforeMethod
	public void setup()  {
 
		launchBrowser();

	}
	
	@AfterMethod
	public void teardown() {
		if(driver!=null)driver.quit();
		else System.out.println(" can not quit driver");
		
	}
}


