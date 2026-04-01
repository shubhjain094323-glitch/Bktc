package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.Snapshot;

public class SnapshotTest extends BaseTest {

	Snapshot sp;

	@BeforeClass
	public void init() {
		try {
			sp = new Snapshot(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createSnapshot() throws InterruptedException, TimeoutException {
		SoftAssert soft = new SoftAssert();
		sp.createSnapshot();
		String actualcreatesnapshot = sp.getToastMessage();
		System.out.println("Create snapshot -" + actualcreatesnapshot);
		soft.assertTrue(actualcreatesnapshot.contains("created"), "Snapshot creation toast is not displayed");
		soft.assertAll();
	}

	@Test(dependsOnMethods = "createSnapshot", alwaysRun = true)
	public void delete_snapshot() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.deleteSnapshot();
		String actualdeletesnapshot = sp.getToastMessage();
		System.out.println("delete snapshot -" + actualdeletesnapshot);
		soft.assertTrue(actualdeletesnapshot.contains("deleted"), "Evidence deleted toast is not displayed");
		soft.assertAll();
	}

	@Test(dependsOnMethods = "delete_snapshot", alwaysRun = true)
	public void Snapshot_quickentry() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		boolean executed = sp.snapshotquickentry();
		if (executed) {
			String actualquickentrytoast = sp.getToastMessage();
			System.out.println("Quick entry - " + actualquickentrytoast);
			soft.assertTrue(actualquickentrytoast.contains("ledger entries saved"),
					"Quick entry toast is not displyed, please check");
		} else {
			System.out.println("Quick entry not supported for this entity, please check the entity level setting");
		}
		soft.assertAll();
	}

	@Test(dependsOnMethods = "Snapshot_quickentry", alwaysRun = true)
	public void bulkaction() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.bulkaction();
		String actualbullkaction = driver.findElement(By.xpath("//p[@class='pmsg-blue-center']")).getText();
		System.out.println("Bulk action -" + actualbullkaction);
		soft.assertTrue(actualbullkaction.contains("Successful"));
		soft.assertAll();
	}

	@Test(dependsOnMethods = "bulkaction", alwaysRun = true)
	public void ExcelImport() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		sp.ExcelImport();
		String actualexcelimport = sp.getToastMessage();
		System.out.println("Excel import snapshot -" + actualexcelimport);
		soft.assertTrue(actualexcelimport.contains("Email sent"), "Excel import uploaded file email is not sent");
		soft.assertAll();

	}

	/*
	 * @Test //(dependsOnMethods = "ExcelImport", alwaysRun = true) public void
	 * Bulk_Upload() throws InterruptedException { SoftAssert soft = new
	 * SoftAssert(); sp.bulkuploadsnapshot(); WebDriverWait longWait = new
	 * WebDriverWait(driver, Duration.ofSeconds(60)); WebElement finishBtn =
	 * longWait .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Finish']")));
	 * soft.assertTrue(finishBtn.isEnabled() && finishBtn.isDisplayed(),
	 * "Finish button is NOT enabled after bulk upload"); soft.assertAll(); }
	 */

}
