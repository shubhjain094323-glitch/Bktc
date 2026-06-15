package pages;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static LogIn login;

	@BeforeSuite(alwaysRun = true)
	public void setupSuite() throws InterruptedException {

		// System.out.println("BEFORE SUITE RUNNING...");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		// System.out.println("Driver created in BaseTest: " + driver);

		driver.manage().window().maximize();
		try {
			login = new LogIn(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}

		login.openURL();
		login.performLogin();
		login.entityselection();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		if (driver != null) {
			driver.quit();
		}
	}

}
