package base;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pages.LogInPage;

public class BaseTest {

	public static WebDriver driver;
	public static LogInPage login;

	public Logger logger;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser" })
	public void setupSuite(String br) throws InterruptedException {

		// logger = LogManager.getLogger(this.getClass());

		// System.out.println("BEFORE SUITE RUNNING...");
		
		logger.info("Choosen browser from the XML"  +br);
		
	//	System.out.println("Browser from XML: " + br);

		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser");
			return;
		}

		// System.out.println("Driver created in BaseTest: " + driver);

		driver.manage().window().maximize();
		try {
			login = new LogInPage(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}

		login.openURL();
		// login.performLogin();
		login.entityselection();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		if (driver != null) {
			driver.quit();
		}
	}

}
