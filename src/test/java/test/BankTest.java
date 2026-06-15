package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import pages.BankingPage;
import pages.BaseTest;

public class BankTest extends BaseTest {

//	Banking bk;
	BankingPage bk;
	SoftAssert soft;

	@BeforeClass(alwaysRun = true)
	public void init() {
		try {
			bk = new BankingPage(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "sanity" , "regression" })
	public void OpenBankingModule() {
		bk.gotobaningmodule();
	}

	@Test(dependsOnMethods = "OpenBankingModule", groups = { "sanity" , "regression" })
	public void Addbank() {
		bk.addbank();
		String actualaddbank = bk.getToastmessage();
		String expectedaddbank = "Financial Institute Created";
		soft.assertEquals(actualaddbank, expectedaddbank);
		soft.assertAll();

	}

	@Test(dependsOnMethods = "Addbank", groups = { "sanity" })
	public void update_Bank() {
		bk.update_Bank();
		String actualupdarebank = bk.getToastmessage();
		String expectedupdatebank = "Financial Institute Updated";
		soft.assertEquals(actualupdarebank, expectedupdatebank);
		soft.assertAll();

	}

	@Test(dependsOnMethods = "update_Bank", alwaysRun = true, groups = { "sanity" , "regression" })
	public void deleteBank() {
		bk.deletebank();
		String actualdelete = bk.getToastmessage();
		String expecteddelete = "Financial Institute Deleted";
		soft.assertEquals(actualdelete, expecteddelete);
		soft.assertAll();

	}
}
