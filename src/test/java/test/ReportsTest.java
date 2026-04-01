package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Banking;
import pages.Reports;

public class ReportsTest extends BaseTest {

	Reports rp;
	SoftAssert soft;

	@BeforeClass
	public void init() {
		try {
			rp = new Reports(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Export_Report() throws InterruptedException {
		rp.reports();

	}

}
