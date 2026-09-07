package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import base.DriverFactory;
import pages.BankingPage;

public class BankTest extends BaseTestUpdated {

	BankingPage bk;
	SoftAssert soft;

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {

		System.out.println("Driver from BaseTest: " + driver);

		if (driver == null) {
			throw new IllegalStateException("Driver is NULL in BankTest.init()");
		}

		bk = new BankingPage(driver);

		soft = new SoftAssert();

		System.out.println("BankingPage initialized successfully");
	}

	@Test(groups = { "sanity", "regression" })
	public void OpenBankingModule() {
		logger.info("Banking Module Open");
		bk.gotobaningmodule();
	}

	@Test(dependsOnMethods = "OpenBankingModule", groups = { "sanity", "regression" })
	public void Addbank() throws InterruptedException {
		bk.addbank();
		String actualaddbank = bk.getToastmessage();
		String expectedaddbank = "Financial Institute Created";
		soft.assertEquals(actualaddbank, expectedaddbank);
		soft.assertAll();

	}

	@Test(dependsOnMethods = "Addbank", groups = { "sanity" })
	public void update_Bank() throws InterruptedException {
		bk.update_Bank();
		String actualupdarebank = bk.getToastmessage();
		String expectedupdatebank = "Financial Institute Updated";
		soft.assertEquals(actualupdarebank, expectedupdatebank);
		soft.assertAll();

	}

	@Test(dependsOnMethods = "update_Bank", alwaysRun = true, groups = { "sanity", "regression" })
	public void deleteBank() throws InterruptedException {
		bk.deletebank();
		String actualdelete = bk.getToastmessage();
		String expecteddelete = "Financial Institute Deleted";
		soft.assertEquals(actualdelete, expecteddelete);
		soft.assertAll();

	}
}
