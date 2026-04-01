package pages;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v137.layertree.model.SnapshotId;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBuktec {

	public static void main(String[] args)
			throws FileNotFoundException, InterruptedException, TimeoutException, AWTException {

		// Setup ChromeDriver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Banking bk = new Banking(driver);
		

		LogIn login = new LogIn(driver);
		Dashboard db = new Dashboard(driver);
		Snapshot sp = new Snapshot(driver);
		
		Moneylink ml = new Moneylink(driver);
		Reports rp = new Reports(driver);
		Updatedetails ud = new Updatedetails(driver);

		login.performLogin();
		login.entityselection();
//		db.dashboard();
		

//		sp.createSnapshot();
//		sp.deleteSnapshot();
//		sp.deleteselectedsnapshot();
//		sp.bulkuploadsnapshot();
//		sp.ExcelImport();
//		sp.bulkaction();
		
//		bk.openbankingmodule();
//		bk.addbank();
		
		
		ml.openMoneylinkPage();
		ml.setupMoneylink();
		ml.moneylinkquickentry();
//		ml.transactionreset();
//		ml.moneylinksingletransaction();
//		ml.crbulkentries();
//		ml.drbulkentries();
//		ml.transactionreset();
//		ml.exportlist();
//		ud.updateaccountdetails();
//		ud.entityupdate();
//		ud.userupdate();
//

//		login.closeBrowser();
	}
}
