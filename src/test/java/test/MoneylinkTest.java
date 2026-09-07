package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import pages.Moneylink;

public class MoneylinkTest extends BaseTestUpdated {

	Moneylink ml;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void init() {
		try {
			ml = new Moneylink(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "sanity", "regression" })
	public void open_moneylink() throws InterruptedException {
		logger.info("Monyelink module opening");
		ml.openMoneylinkPage();
		logger.info("Selecting bank and time period for accounting");
		ml.setupMoneylink();
	}

	@Test(dependsOnMethods = "open_moneylink", alwaysRun = true, groups = { "sanity", "regression" })
	public void reset_moneylinktransaction() throws InterruptedException {

		SoftAssert soft = new SoftAssert();

		logger.info("Selecting transaction for reseting");

		ml.transactionreset();

//		WebElement msg = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pmsg-blue-center']")));

//		String actualReset = msg.getText();
//		System.out.println("Reset Message: " + actualReset);

//		if (actualReset.contains("Successful")) {
//			soft.assertTrue(true);
//			System.out.println("Transaction reset successfully");
//		} else {
//			System.out.println("Reset message not found, skipping validation");
//		}

		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[normalize-space()='money link']")).click();
		Thread.sleep(1500);

		soft.assertAll();

		logger.info("Transaction reseted");

	}

	@Test(dependsOnMethods = "reset_moneylinktransaction", alwaysRun = true, groups = { "sanity", "regression" })
	public void single_transaction_accounting() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		ml.moneylinksingletransaction();
		String actualsingletransaction = ml.getToastMessage();
		System.out.println("Single Transaction" + actualsingletransaction);
		soft.assertTrue(actualsingletransaction.contains("Transaction saved"), "Trasnaction is not saved");

		WebElement closeBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='money link']")));

		closeBtn.click();

		Thread.sleep(1500);

		soft.assertAll();

		logger.info("Single transaction Accounted");

	}

	@Test(dependsOnMethods = "single_transaction_accounting", alwaysRun = true, groups = { "sanity", "regression" })
	public void moneylink_quickentry() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		logger.info("Selecting record for Moneylink Quick Entry");
		ml.moneylinkquickentry();
		String actualquickentry = ml.getToastMessage();
		System.out.println("Quick Entry " + actualquickentry);
		soft.assertTrue(actualquickentry.contains("Moneylink ledger entries saved"), "Quick entry is not done");
		Thread.sleep(1500);

		soft.assertAll();

		logger.info("Moneylink Quick Entry Done");
	}

	@Test(dependsOnMethods = "moneylink_quickentry", alwaysRun = true, groups = { "sanity", "regression" })
	public void CR_Bulkentries() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		ml.crbulkentries();

		String actualcrbulk = ml.getToastMessage();
		System.out.println("Cr Bulk entries" + actualcrbulk);
		soft.assertTrue(actualcrbulk.contains("Bulk ledger list saved successfully"), "CR Bulk entry is not done");
		Thread.sleep(1500);

		soft.assertAll();

		logger.info("Moneylink CR_BUlk Entries Done");
	}

	@Test(dependsOnMethods = "CR_Bulkentries", alwaysRun = true, groups = { "sanity", "regression" })
	public void DR_Bulkentries() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		ml.drbulkentries();
		String actualdrbulk = ml.getToastMessage();
		System.out.println("DR Bulk " + actualdrbulk);
		soft.assertTrue(actualdrbulk.contains("Bulk ledger list saved successfully"), "DR Bulk entry is not done");
		Thread.sleep(1500);

		soft.assertAll();
		logger.info("Moneylink DR_BUlk Entries Done");
	}

}
