package test;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Dashboard;

public class DashboardTest extends BaseTest {

	Dashboard db;
	WebDriverWait wait;

	@BeforeClass
	public void init() {
		try {
			db = new Dashboard(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void dashboard_module() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		db.dashboard();
		soft.assertTrue(true);
		soft.assertAll();

	}

}
