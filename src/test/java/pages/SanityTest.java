package pages;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SanityTest {

	WebDriver driver;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();

	LogIn login;
	Dashboard db;
	Snapshot sp;
	Banking bk;
	Moneylink ml;
	Reports rp;
	Updatedetails ud;

	@BeforeClass
	public void setup() throws FileNotFoundException, AWTException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Initialize page objects
		login = new LogIn(driver);
		db = new Dashboard(driver);
		sp = new Snapshot(driver);
		bk = new Banking(driver);
		ml = new Moneylink(driver);
		rp = new Reports(driver);
		ud = new Updatedetails(driver);

		try {
			login.performLogin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@Test(priority = 1)
	public void entity_selection() throws InterruptedException {

		login.entityselection();

	}

//	@Test(priority = 2, enabled = false)
//	public void Addbank() throws InterruptedException {
//
//		bk.openbankingmodule();
//		bk.addbank();
//	}

	@Test(priority = 3)
	public void ManuallyCreatesnapshot() throws InterruptedException, TimeoutException {
		sp.createSnapshot();
	}
//
//	@Test(priority = 4)
//	public void DeleteSnapshot() throws InterruptedException, TimeoutException {
//		sp.deleteSnapshot();
//	}
//
//	@Test(priority = 5)
//	public void BulkUploadSnapshot() throws InterruptedException, TimeoutException {
//		sp.bulkuploadsnapshot();
//	}
//
//	@Test(priority = 6)
//	public void DeleteSelectedSnapshot() throws InterruptedException, TimeoutException {
//		sp.deleteselectedsnapshot();
//	}
//
//	@Test(priority = 7)
//	public void ExcelImport() throws InterruptedException, TimeoutException {
//		sp.ExcelImport();
//	}

//	@Test(priority = 9)
//	public void BulkAction() throws InterruptedException, TimeoutException {
//		sp.bulkaction();
//	}
//
//	@Test(priority = 10)
//	public void ExportList() throws InterruptedException, TimeoutException {
//		sp.ExportList();
//	}

//	@Test(priority = 11)
//	public void snapshotquickentry() {
//		sp.snapshotquickentry();
//		String actual = sp.getToastMessage("bank ledger");
//		soft.assertTrue(actual.contains("ledger entries saved "));
//
//		soft.assertAll();
//	}
//
//	@Test(priority = 12)
//	public void open_moneylinkmodule() throws InterruptedException {
//		ml.openMoneylinkPage();
//		ml.setupMoneylink();
//	}
//
//	@Test(priority = 13)
//	public void reset_moneylink_transaction() throws InterruptedException {
//		ml.transactionreset();
//	}
//
//	@Test(priority = 14)
//	public void Sigle_transaction() throws InterruptedException {
//		ml.moneylinksingletransaction();
//	}
//
//	@Test(priority = 15, dependsOnMethods = "open_moneylinkmodule")
//	public void moneylink_quickentry() throws InterruptedException {
//		ml.moneylinkquickentry();
//	}
//
//	@Test(priority = 16)
//	public void CR_bulkEntries() throws InterruptedException {
//		ml.crbulkentries();
//	}
//
//	@Test(priority = 17)
//	public void DR_bulkEntries() throws InterruptedException {
//		ml.drbulkentries();
//	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
