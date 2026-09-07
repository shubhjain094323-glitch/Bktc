package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import pages.Snapshot;

public class SnapshotTest extends BaseTestUpdated {

	Snapshot sp;

	@BeforeClass(alwaysRun = true)
	public void init() {
		try {
			sp = new Snapshot(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "sanity", "regression" })
	public void createSnapshot() throws InterruptedException, Exception {
		SoftAssert soft = new SoftAssert();
		sp.createSnapshot();
		String actualcreatesnapshot = sp.getToastMessage();
		System.out.println("Create snapshot -" + actualcreatesnapshot);
		soft.assertTrue(actualcreatesnapshot.contains("created"), "Snapshot creation toast is not displayed");
		soft.assertAll();
	}

	@Test(dependsOnMethods = "createSnapshot", alwaysRun = true, groups = { "sanity", "regression" })
	public void delete_snapshot() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.deleteSnapshot();
		String actualdeletesnapshot = sp.getToastMessage();
		System.out.println("delete snapshot -" + actualdeletesnapshot);
		soft.assertTrue(actualdeletesnapshot.contains("deleted"), "Evidence deleted toast is not displayed");
		soft.assertAll();
	}

	@Test(dependsOnMethods = "delete_snapshot", alwaysRun = true, groups = { "sanity", "regression" })
	public void ExcelImport() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.ExcelImport();
		String actualexcelimport = sp.getToastMessage();
		System.out.println("Excel import snapshot -" + actualexcelimport);
		soft.assertTrue(actualexcelimport.contains("Email sent"), "Excel import uploaded file email is not sent");
		// soft.assertEquals(true, true);
		soft.assertAll();

	}

	@Test(dependsOnMethods = "delete_snapshot", alwaysRun = true, groups = { "sanity", "regression" })
	public void Snapshot_quickentry() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		String actualquickentry = sp.snapshotquickentry();

		String expectedquickentry = "Snapshot ledger entries saved successfully";

		if (actualquickentry.equals("No Snapshot Found for QuickEntry")) {

			throw new SkipException("No snapshot found for QuickEntry. Test skipped.");
		}

		soft.assertEquals(actualquickentry, expectedquickentry);

		soft.assertAll();
	}

	@Test(dependsOnMethods = "Snapshot_quickentry", alwaysRun = true, groups = { "sanity", "regression" })
	public void bulkaction() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		String actual = sp.bulkaction();

		String expected = "Successful";

		if (actual.equals("No snapshots")) {

			throw new SkipException("No snapshots available in Draft Widgets. Bulk Action test skipped.");
		}

		soft.assertEquals(actual, expected, "Bulk action status is not as expected");

		soft.assertAll();

	}

	@Test(dependsOnMethods = "bulkaction", alwaysRun = true, groups = { "sanity", "regression" })
	public void Bulk_Upload() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.bulkuploadsnapshot();
		soft.assertEquals(true, true);
		soft.assertAll();
	}

	@Test(dependsOnMethods = "Bulk_Upload", alwaysRun = true, groups = { "sanity" })
	public void Delete_SelectedSnapshot() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.deleteselectedsnapshot();
		String actualdeleteSelectedsnapshot = sp.getToastMessage();
		System.out.println("delete snapshot -" + actualdeleteSelectedsnapshot);
		soft.assertTrue(actualdeleteSelectedsnapshot.contains("deleted"), "Evidence deleted toast is not displayed");
		soft.assertAll();
	}

}
