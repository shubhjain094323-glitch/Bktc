package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pages.LogInPage;

public class BaseTestUpdated2 {

	public static WebDriver driver;

	public static LogInPage login;

	public static Logger logger;

	@BeforeSuite(alwaysRun = true)
//	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "os" })
	public void setUp(String br, String os) throws Exception {

		System.out.println("Selected Browser " + br);
		System.out.println("Selected OS " + os);

		logger = LogManager.getLogger(this.getClass());

	//	DriverFactory.initDriver(br, os);

		driver = DriverFactory.getDriver();

		if (driver == null) {
			throw new RuntimeException("Driver is NULL");
		}

		login = new LogInPage(driver);

		login.openURL();

		login.performLogin();

		login.entityselection();

	}

	// @AfterSuite(alwaysRun = true)
	@AfterClass(alwaysRun = true)
	public void tearDown() {

		DriverFactory.quitDriver();

	}

}
