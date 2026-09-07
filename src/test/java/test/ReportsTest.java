package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import pages.Reports;

public class ReportsTest extends BaseTestUpdated {

	Reports rp;
	SoftAssert soft;

	@BeforeClass(alwaysRun = true)
	public void init() {
		try {
			rp = new Reports(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "sanity" , "Regression" })
	public void Export_Report() throws InterruptedException {
		rp.reports();

	}

}
