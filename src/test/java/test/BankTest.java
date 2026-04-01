package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Banking;

public class BankTest extends BaseTest {

	Banking bk;
	SoftAssert soft;

	@BeforeClass
	public void init() {
		try {
			bk = new Banking(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void add_bank() throws InterruptedException {
		bk.openbankingmodule();
		bk.addbank();
		String actualaddbank = bk.getToastMessage();
		System.out.println("Add Bank" + actualaddbank);
		soft.assertTrue(actualaddbank.contains("Institute Created"), "Bank is not added");
		soft.assertAll();
	}

	@Test
	public void delete_bank() throws InterruptedException {
		// bk.openbankingmodule();
		bk.deletebank();
		String actualdeletebank = bk.getToastMessage();
		System.out.println("Delete Bank" + actualdeletebank);
		soft.assertTrue(actualdeletebank.contains("Institute Deleted"), "Bank is not deleted");
		soft.assertAll();
	}

}
