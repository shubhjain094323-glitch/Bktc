package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.LogInPage;

public class BaseTestUpdated {

	public WebDriver driver;
	public LogInPage login;
	public Logger logger;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "os", "headless" })
	public void SetUp(String br, String os, boolean headless) throws Exception {

		logger = LogManager.getLogger(this.getClass());

		// --- AUTO LOOP LOGIC FOR BOTH LOCAL + JENKINS ---
		String finalBrowser = br;
		if (br.equalsIgnoreCase("auto")) {
			finalBrowser = DriverFactory.getBrowserLoopWise(); // chrome -> edge -> firefox loop
		}

		DriverFactory.initDriver(finalBrowser, os, headless);

		// DriverFactory.initDriver(br, os, headless);

		driver = DriverFactory.getDriver();

		if (driver == null) {
			throw new IllegalStateException("Driver is NULL after DriverFactory.initDriver()");
		}

		login = new LogInPage(driver);

		login.openURL();
		login.performLogin();
		login.entityselection();

		System.out.println("Login completed for: " + this.getClass().getSimpleName());
	}

	// PARALLEL CLASS CLEANUP

	@AfterClass(alwaysRun = true)
	public void TearDown() {

		System.out.println("Closing browser for: " + this.getClass().getSimpleName());

		DriverFactory.quitDriver();
	}

}