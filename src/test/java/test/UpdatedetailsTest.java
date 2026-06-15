package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.BaseTest;
import pages.Updatedetails;

public class UpdatedetailsTest extends BaseTest {

	Updatedetails ud;
	SoftAssert soft;

	@BeforeClass(alwaysRun = true)
	public void init() {
		try {
			ud = new Updatedetails(driver);
			soft = new SoftAssert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "sanity" , "regression" })
	public void update_accountdetails() throws InterruptedException {
		ud.updateaccountdetails();
		String actualupdate = ud.getToastMessage();
		System.out.println("Account update toast " + actualupdate);
		soft.assertTrue(actualupdate.contains("Account Updated"), actualupdate);

	}

	@Test(dependsOnMethods = "update_accountdetails" , groups = { "regression" })
	public void update_entity() throws InterruptedException {
		ud.entityupdate();
		String actualentityupdate = ud.getToastMessage();
		System.out.println("Entity update toast " + actualentityupdate);
		soft.assertTrue(actualentityupdate.contains("Entity updated"), "Entity is not updated please check once");

	}

	@Test(dependsOnMethods = "update_entity", groups = { "regression" })
	public void update_user() throws InterruptedException {
		ud.userupdate();
		String actualuserupdate = ud.getToastMessage();
		System.out.println("Entity update toast " + actualuserupdate);
		soft.assertTrue(actualuserupdate.contains("User Updated"), "User is not updated please check once");

	}

	@Test(groups = { "regression" })
	public void Create_Entity() throws InterruptedException {

		ud.Create_entity();
		String actulentitycreate = ud.getToastMessage();
		System.out.println("Create Entity toast " + actulentitycreate);
		soft.assertTrue(actulentitycreate.contains("Entity Added"), "Entity is not created successfully");
		soft.assertAll();
	}

	@Test (groups = { "regression" })
	public void Delete_Entity() throws InterruptedException {

		ud.delete_entity();
		String actulentitydelete = ud.getToastMessage();
		System.out.println("Delete Entity toast " + actulentitydelete);
		soft.assertTrue(actulentitydelete.contains("Entity Deleted"), "Entity is not Deleted successfully");
		soft.assertAll();

	}

	@Test (groups = { "regression" })
	public void Create_user() throws InterruptedException {
		ud.Create_user();
		String actulusercreate = ud.getToastMessage();
		System.out.println("Create User toast " + actulusercreate);
		soft.assertTrue(actulusercreate.contains("User Added"), "User is not created successfully");
		soft.assertAll();
	}

}
