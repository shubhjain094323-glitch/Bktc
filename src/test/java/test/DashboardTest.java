package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import base.DriverFactory;
import pages.DashboardPage;

public class DashboardTest extends BaseTestUpdated {

	DashboardPage db;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void init() throws FileNotFoundException, AWTException {
		
		db = new DashboardPage(driver);
		
		wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));

	}

	@Test(groups = { "sanity", "regression" })
	public void dashboard_module() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		db.dashboard();
		soft.assertTrue(true);
		soft.assertAll();

	}

}
