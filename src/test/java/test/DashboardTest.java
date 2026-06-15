package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.BaseTest;
import pages.Dashboard;

public class DashboardTest extends BaseTest {

	Dashboard db;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void init() throws FileNotFoundException, AWTException {

		System.out.println("Driver in BeforeClass: " + driver);
		db = new Dashboard(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	@Test(groups = { "sanity", "regression" })
	public void dashboard_module() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		db.dashboard();
		soft.assertTrue(true);
		soft.assertAll();

	}

}
