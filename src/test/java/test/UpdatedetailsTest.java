package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Updatedetails;

public class UpdatedetailsTest extends BaseTest {

	Updatedetails ud;
	SoftAssert soft;

	@BeforeClass
	public void init() {
		try {
			ud = new Updatedetails(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void update_accountdetails() throws InterruptedException {
		ud.updateaccountdetails();
		String actualupdate = ud.getToastMessage();
		System.out.println("Account update toast " + actualupdate);
		soft.assertTrue(actualupdate.contains("Account Updated"), actualupdate);

	}

}
