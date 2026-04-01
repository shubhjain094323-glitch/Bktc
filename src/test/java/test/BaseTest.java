package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LogIn;

public class BaseTest {

	protected static WebDriver driver;
	protected static LogIn login;

	@BeforeSuite
	public void setupSuite() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {
			login = new LogIn(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}

		login.performLogin();
		login.entityselection();
	}

	@AfterSuite
	public void tearDownSuite() {
		if (driver != null) {
			driver.quit();
		}
	}
}
