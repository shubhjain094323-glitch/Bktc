package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Snapshot {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData random;

	public Snapshot(WebDriver driver) throws FileNotFoundException, AWTException {

		this.driver = driver;

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		random = new RandomData();
	}

	// Method for snapshot creation
	public void createSnapshot() throws InterruptedException, TimeoutException {

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement createsnapshot = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='quick-menu']//span[@class='icon-plus']")));
		createsnapshot.click();

		WebElement dateinput = driver.findElement(By.xpath("//input[contains(@name,'date')]"));
		dateinput.sendKeys(config.getsnapshotdate());

		dateinput.sendKeys(Keys.ENTER);

		// driver.findElement(By.xpath("//input[@id='invoice-control']")).sendKeys(config.getInvoiceNum());

		driver.findElement(By.xpath("//input[@id='invoice-control']")).sendKeys(random.getinvoicenum());

		driver.findElement(By.xpath("//input[@name='total_amount']")).sendKeys(random.gettotalamt());

		WebElement party = driver.findElement(By.xpath(
				"//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']"));
		party.click();
		Thread.sleep(1000);

		// Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1500);

		// driver.findElement(By.xpath("//input[@name='merchant']")).sendKeys(config.getPartyName());

		driver.findElement(By.xpath("//input[@name='merchant']")).sendKeys(random.getpartyname());

		driver.findElement(By.xpath("//input[@name='gst_no']")).sendKeys(random.getpartygstin());

		driver.findElement(By.xpath("//input[@name='pan_number']")).click();

		WebElement Igst = driver.findElement(By.xpath("//input[@name='igst_amount']"));
		WebElement Cgst = driver.findElement(By.xpath("//input[@name='cgst_amount']"));
		WebElement Sgst = driver.findElement(By.xpath("//input[@name='sgst_amount']"));

		String gstamt = random.getGSTamt();

		if (Igst.isDisplayed() && Igst.isEnabled()) {
			// Fill IGST
			Igst.clear();
			Igst.sendKeys(gstamt);
			System.out.println("IGST filled");
		} else if (Cgst.isDisplayed() && Cgst.isEnabled() && Sgst.isDisplayed() && Sgst.isEnabled()) {
			// Fill CGST & SGST
			Cgst.clear();
			Cgst.sendKeys(gstamt);
			Sgst.clear();
			Sgst.sendKeys(gstamt);
			System.out.println("CGST & SGST filled");
		} else {
			// No GST fields enabled → skip
			System.out.println("No GST applicable, skipping GST fields");
		}

		WebElement nextcheckbox = driver.findElement(By.cssSelector("#styled-checkbox-11"));
		// System.out.println(nextcheckbox.isSelected());
		if (nextcheckbox.isSelected()) {
			nextcheckbox.click();
		}

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

		// Check if duplicate PAN message exists
		List<WebElement> duplicatePanMsgList = driver
				.findElements(By.xpath("//p[contains(@class,'pmsg-blue-center')]"));

		if (!duplicatePanMsgList.isEmpty() && duplicatePanMsgList.get(0).isDisplayed()) {
			WebElement duplicatePanMsg = duplicatePanMsgList.get(0);
			System.out.println("Duplicate PAN message shown: " + duplicatePanMsg.getText());

			// Click Yes button
			WebElement yesOption = driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
			yesOption.click();
			System.out.println("Clicked on Yes");
		} else {
			System.out.println("No duplicate PAN message, skipping...");
		}

		Thread.sleep(2000);
		List<WebElement> duplicatesnapshot = driver
				.findElements(By.xpath("//p[normalize-space()='Save duplicate snapshot']"));

		if (!duplicatesnapshot.isEmpty() && duplicatesnapshot.get(0).isDisplayed()) {
			System.out.println("Duplicate Snapshot message shown: " + duplicatesnapshot.get(0).getText());

			WebElement yesoptions = driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
			yesoptions.click();

			System.out.println("Clicked on Yes");
		} else {
			System.out.println("Duplicate Snapshot message not shown");
		}

		Thread.sleep(2000);

		System.out.println("Snapshot created successfully");
	}

	public void deleteSnapshot() throws InterruptedException {
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0, 500)");
		WebElement deletesnapshot = driver
				.findElement(By.xpath("//span[@class='icon-delete blue-icon btn-bar-delete']"));
		deletesnapshot.click();
		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		Thread.sleep(2000);
		// System.out.println("Snapshot deleted successfully");
	}

	public void deleteselectedsnapshot() throws InterruptedException {

		// Open Snapshot
		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();
		Thread.sleep(2000);

		actions.moveToElement(
				driver.findElement(
						By.xpath("//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")),
				200, 0).perform();

		// Time period selection
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")));
		yearDropdown.click();

		WebElement sinceBeginning = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Since Beginning']")));
		sinceBeginning.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[contains(@class,'row widget-filter-new sec-mb')]//div[2]//div[1]")).click();

		js.executeScript("window.scrollBy(0, 200)");
		Thread.sleep(1500);

		// String snapshotpartyname = config.getSnapshotselection();

		// System.out.println(snapshotpartyname);

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
		// By.xpath("//tbody//tr[td[4]//span[contains(text(), '" + snapshotpartyname +
		// "')]]")));
		// System.out.println(element);
		// element.click();

		// Wait until snapshot table is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));

		// Select 4th snapshot row
		WebElement fourthRow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]")));
		fourthRow.click();

		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(2000);
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'modal-backdrop')]")));
		WebElement deletesnapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='icon-delete blue-icon btn-bar-delete']")));
		deletesnapshot.click();

		// Find all "Yes" buttons (confirmation popup)
		List<WebElement> confirmationMsg = driver.findElements(By.xpath("//button[normalize-space()='Yes']"));

		if (!confirmationMsg.isEmpty()) {
			// Case 1: Confirmation popup is shown
			WebElement yesBtn = confirmationMsg.get(0); // first element
			Thread.sleep(3000);
			yesBtn.click();
			System.out.println("Confirmation popup appeared, clicked YES.");
			System.out.println("Selected Snapshot is deleted successfully");
		} else {
			// Case 2: Confirmation popup not shown
			System.out.println("No confirmation popup appeared.");
			Thread.sleep(1500);
			System.out.println("Snapshot is not deleted ");
		}

	}

	public void bulkuploadsnapshot() throws InterruptedException {

		Thread.sleep(4000);
		// Open Snapshot
		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();
		Thread.sleep(2000);

		// Open menu
		WebElement contextmenu = driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']"));
		js.executeScript("arguments[0].click();", contextmenu);
		Thread.sleep(2000);

		// Keyboard selection
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		// Click on upload placeholder
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='bulk-popup-table bulk-popup-pluse']//p"))).click();
		Thread.sleep(1500);

		// Upload file via Robot
		robot.delay(1000);
		StringSelection selection = new StringSelection(config.getBulkuploadfilepath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Start upload
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start Upload']")))
				.click();

		// Wait for upload to complete
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement finishbtn = longWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Finish']")));

		Thread.sleep(1500);
		finishbtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

		System.out.println("Bulk upload successfully uploaded");

	}

	public void ExcelImport() throws InterruptedException {
		Thread.sleep(4000);

		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).click();

		Thread.sleep(2000);

		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='ie_upload_file']//label[normalize-space()='Select File']")).click();

		Thread.sleep(2000);

		// Decide which file path to use based on element availability
		String filePath = null;

		// Check for Inventory Excel
		List<WebElement> inventoryList = driver.findElements(By.xpath("//h2[normalize-space()='Excel inventory']"));
		if (!inventoryList.isEmpty() && inventoryList.get(0).isDisplayed()) {
			System.out.println("Inventory Excel option found");
			filePath = config.getInventoryExcelPath();
		} else {
			// Check for Simple Excel
			List<WebElement> simpleList = driver.findElements(By.xpath("//span[contains(@class,'mr-3')]"));
			if (!simpleList.isEmpty() && simpleList.get(0).isDisplayed()) {
				System.out.println("Simple Excel option found");
				filePath = config.getExcelfilepath();
			} else {
				throw new RuntimeException("Neither Inventory Excel nor Simple Excel option found!");
			}
		}

		// Upload the file using Robot
		if (filePath != null) {
			StringSelection selection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			// Press CTRL + V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// Press Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(4000);

			List<WebElement> ignoreList = driver.findElements(By.xpath(
					"//div[@class='col-xl-12 col-lg-12 col-md-12 mt-0 mt-lg-0 mt-md-3 text-right d-flex align-items-center justify-content-end bottom-save-btn']//span[@class='icon-warning ie_icon_btn yellow-icon']"));

			if (!ignoreList.isEmpty()) {
				WebElement ignoremsg = wait.until(ExpectedConditions.elementToBeClickable(ignoreList.get(0)));
				Thread.sleep(1500);
				js.executeScript("arguments[0].click();", ignoremsg);
				System.out.println("Ignore message clicked.");
			} else {
				System.out.println("Ignore message not found, skipping click.");
			}

			Thread.sleep(6000);

			List<WebElement> yesBtns = driver.findElements(By.xpath("//button[normalize-space()='Yes']"));

			if (!yesBtns.isEmpty()) {
				WebElement yes = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
				Thread.sleep(1000);
				actions.moveToElement(yes).click().perform();
				System.out.println("Clicked for ignore Yes button.");
			} else {
				System.out.println("Ignore - Yes button not found,");
			}

			Thread.sleep(1000);

			driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();

			WebElement finishexcelimport = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='bottom_save_btn']//button[text()='Finish']")));

			WebElement summarypopup = driver.findElement(By.xpath("//button[normalize-space()='Okay']"));
			wait.until(ExpectedConditions.visibilityOf(summarypopup));
			Thread.sleep(2000);
			summarypopup.click();

			js.executeScript("arguments[0].click();", finishexcelimport);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));
			System.out.println("Excel Import successfully uploaded");

		}
	}

	public boolean snapshotquickentry() throws InterruptedException {

		Thread.sleep(4000);
		// Open Snapshot
		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();

		// Open menu
		WebElement menuBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-menu-lines white-icon']")));
		menuBtn.click();

		// Time period selection
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")));
		yearDropdown.click();

		WebElement sinceBeginning = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Since Beginning']")));
		sinceBeginning.click();

		// Open menu
		menuBtn.click();

		// Select snapshot quick entry
//		WebElement quickentry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='shortcut_keys_action' and text()='Q']")));
//		if (quickentry.isDisplayed()) {
//			quickentry.click();
//		} else {
//			System.out.println("Snapshot Quick Entry is not supported to this entry, Please check the entity setting");
//		}

		try {

			WebElement quickentry = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//span[@class='shortcut_keys_action' and text()='Q']")));

			quickentry.click();

			driver.findElement(By.xpath("//button[normalize-space()='Okay']")).click();

			// Select first 3 snapshot for the quick entry
			for (int i = 0; i < 1; i++) {
				WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//label[contains(@for,'checkbox" + i + "')]//div[contains(@class,'check')]")));
				checkbox.click();
				System.out.println("Clicked checkbox" + i);
			}

			// Add entries
			driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();

			WebElement gstledgerpopup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));

			gstledgerpopup.click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

//			List<WebElement> dropdowns = driver
//					.findElements(By.xpath("//div[@class='Select type-select __value-container css-1nol46l']"));
//			

			WebElement partygst = driver.findElement(By.xpath("//div[@col-id='party_gst_in' and @role='gridcell']"));
			partygst.click();

			actions.sendKeys(Keys.TAB).perform(); // focus on party ledger dropdown
			actions.sendKeys(Keys.SPACE).perform(); // open the party ledger dropdown
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform(); // select the first ledger

			actions.sendKeys(Keys.TAB).perform(); // focus on purchase/sales ledger dropdown
			actions.sendKeys(Keys.SPACE).perform(); // open the purchase/sales ledger dropdown
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform(); // select the
																										// second ledger

//			dropdowns.get(0).click();
//			Thread.sleep(1500);
//			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
//			Thread.sleep(1500);
//			dropdowns.get(1).click();
//			Thread.sleep(1500);
//			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
//			Thread.sleep(1500);

			WebElement createentries = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Create Entries']")));
			createentries.click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

			// System.out.println("Snapshot quick entries created successfully");
			return true;
		} catch (Exception e) {

			// System.out.println("Snapshot Quick Entry is not supported for this entity,
			// please check entity setting");
			return false;
		}

	}

	public void bulkaction() throws InterruptedException {

		Thread.sleep(4000);
		// Open Snapshot
		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();

		// Open menu
		WebElement menuBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-menu-lines white-icon']")));
		menuBtn.click();

		// Select bulk action
		WebElement menuOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='shortcut_keys_action' and text()='A']")));
		menuOption.click();

		// Time period selection
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")));
		yearDropdown.click();

		// Select since beginning
		WebElement sinceBeginning = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Since Beginning']")));
		sinceBeginning.click();
		Thread.sleep(2000);

		WebElement draft = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[contains(@class,'wtitle-new')][normalize-space()='Draft']")));
		draft.click();
		Thread.sleep(1000);

		for (int i = 0; i <= 2; i++) {
			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(@for,'checkbox" + i + "')]//div[contains(@class,'check')]")));
			checkbox.click();
			System.out.println("Clicked checkbox" + i);
		}

		// Click Delete button
		WebElement deleteBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Delete']")));
		deleteBtn.click();

		// Confirm Yes
		WebElement yesBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
		yesBtn.click();

		// Information pop up - some snapshot are not deleted as they are linked

		List<WebElement> infopopup = driver
				.findElements(By.xpath("//div[@class='center_apply_btn_new']//button[text() = 'OK']"));

		if (!infopopup.isEmpty()) {
			WebElement yes = wait.until(ExpectedConditions.elementToBeClickable(infopopup.get(0)));
			js.executeScript("arguments[0].click();", yes);
			System.out.println("Clicked on Ok button of Information popup.");
		} else {
			System.out.println("Ok button not found, skipping click.");
		}

		// Wait for success dialog and click OK
		WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Okay']")));
		Thread.sleep(1500);
		okBtn.click();
		System.out.println("Bulk snapshot deleted successfully");

	}

	public void ExportList() throws InterruptedException {

		Thread.sleep(4000);
		// Open Snapshot
		WebElement snapshot = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
		snapshot.click();

		// Open menu
		WebElement menuBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-menu-lines white-icon']")));
		menuBtn.click();

		// Time period selection
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")));
		yearDropdown.click();

		WebElement sinceBeginning = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Since Beginning']")));
		sinceBeginning.click();

		// Example: Click "Export List" option
		menuBtn.click();
		Thread.sleep(2000);
		WebElement menuOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='shortcut_keys_action' and text()='X']")));
		menuOption.click();
		System.out.println("Export list downloaded successfully");

	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")))
				.getText();
	}

}
