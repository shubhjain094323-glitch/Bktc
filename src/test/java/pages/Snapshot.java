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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class Snapshot {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebDriverWait longWait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData random;

	public Snapshot(WebDriver driver) throws FileNotFoundException, AWTException {

		this.driver = driver;
		PageFactory.initElements(driver, this);

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		longWait = new WebDriverWait(driver, Duration.ofSeconds(60));

		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		random = new RandomData();
	}

	@FindBy(xpath = "//div[@class='quick-menu']//span[@class='icon-plus']")
	WebElement gotocreatesnapshot;

	@FindBy(xpath = "//input[contains(@name,'date')]")
	WebElement date;

	@FindBy(xpath = "//input[@id='invoice-control']")
	WebElement invoicenum;

	@FindBy(xpath = "//input[@name='total_amount']")
	WebElement totalamount;

	@FindBy(xpath = "//div[@class='col-lg-5 form-group']//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']")
	WebElement partyname_dropdown;

	@FindBy(xpath = "//div[@class='Select type-select __option css-18we6dg-option'][1]")
	WebElement AddNewParty;

	@FindBy(xpath = "//input[@name='merchant']")
	WebElement merchant;

	@FindBy(xpath = "//input[@name='gst_no']")
	WebElement gstNo;

	@FindBy(xpath = "//input[@name='pan_number']")
	WebElement PanNo;

	@FindBy(xpath = "//input[@name='igst_amount']")
	WebElement Igst;

	@FindBy(xpath = "//input[@name='cgst_amount']")
	WebElement Cgst;

	@FindBy(xpath = "//input[@name='sgst_amount']")
	WebElement Sgst;

	@FindBy(css = "#styled-checkbox-11")
	WebElement saveandmovetonext;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement Savebutton;

	@FindBy(xpath = "//p[contains(@class,'pmsg-blue-center')]")
	List<WebElement> duplicatePanMsgList;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement yesoptions;

	@FindBy(xpath = "//p[normalize-space()='Save duplicate snapshot']")
	List<WebElement> duplicatesnapshot;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement yes_Options;

	@FindBy(xpath = "//span[@class='icon-delete blue-icon btn-bar-delete']")
	WebElement deleteicon;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement delete_yes;

	@FindBy(xpath = "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")
	WebElement gotosnapshot;

	@FindBy(xpath = "//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")
	WebElement selectTimePeriod;

	@FindBy(xpath = "//li[normalize-space()='Since Beginning']")
	WebElement SinceBeginning;

	@FindBy(xpath = "//p[contains(@class,'wtitle-new')][normalize-space()='Draft']")
	WebElement ClickOnDraft;

	@FindBy(xpath = "//tbody/tr")
	List<WebElement> snapshotlisting;

	@FindBy(xpath = "//tbody/tr[1]")
	WebElement DraftFirstSnapshot;

	@FindBy(xpath = "//img[contains(@class,'loaderimg')]")
	WebElement loader;

	@FindBy(xpath = "//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")
	WebElement contextmenu;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='B']")
	WebElement clickonBulkUpload;

	@FindBy(xpath = "//div[@class='bulk-popup-table bulk-popup-pluse']//p")
	WebElement AddFilesfor_BulkUpload;

	@FindBy(xpath = "//button[normalize-space()='Start Upload']")
	WebElement clickOnStartUpload;

	@FindBy(xpath = "//button[normalize-space()='Finish']")
	WebElement clickOnFinish;

	@FindBy(xpath = "//div[contains(@class,'loader')]")
	WebElement bulkuploadLoader;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='E']")
	WebElement ClickOnExcelImport;

	@FindBy(xpath = "//label[normalize-space()='Select File']")
	WebElement ClickOnSelectFile_ExcelImport;

	@FindBy(xpath = "//button[normalize-space()='Upload']")
	WebElement ClickonUpload_ExcelImport;

	@FindBy(xpath = "//button[normalize-space()='Okay']")
	WebElement ExcelImportSummary_Okaybutton;

	@FindBy(xpath = "//div[@class='bottom_save_btn']//button[text()='Finish']")
	WebElement Finish_ExcelImport;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='Q']")
	WebElement quickentry;

	@FindBy(xpath = "//button[normalize-space()='Okay']")
	WebElement Okaybutton;

	@FindBy(xpath = "//a[normalize-space()='Add Entries']")
	WebElement AddEntries;

	@FindBy(xpath = "//img[@class='ie_loader_img']")
	WebElement QuickentryLoader;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement quickentrygstledgerSaveButton;

	@FindBy(xpath = "//div[@col-id='party_gst_in' and @role='gridcell']")
	WebElement partygst;

	@FindBy(xpath = "//button[normalize-space()='Create Entries']")
	WebElement CreateEntries;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='A']")
	WebElement ClickOnBulkAction;

	@FindBy(xpath = "//button[normalize-space()='Delete']")
	WebElement ClickOnDelete;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='X']")
	WebElement ClickOnExportList;

	@FindBy(xpath = "//span[@class='icon-sort-amount-down-svgrepo-com sort-both-icon size-xvii cpointer']")
	List<WebElement> Listof_sortingcolumn;

	@FindBy(xpath = "//input[@id='styled-checkbox-is_Reimbursement']")
	WebElement reimbursement_checkbox;

	public void createSnapshot() throws InterruptedException, TimeoutException {

		wait.until(ExpectedConditions.visibilityOf(gotocreatesnapshot)).click();

		date.sendKeys(config.getsnapshotdate());

		date.sendKeys(Keys.ENTER);

		invoicenum.sendKeys(random.getinvoicenum());

		totalamount.sendKeys(random.gettotalamt());

		partyname_dropdown.click();

		Thread.sleep(1000);

		AddNewParty.click();

		Thread.sleep(1000);

		merchant.sendKeys(random.getpartyname());

		gstNo.sendKeys(random.getpartygstin());

		PanNo.click();

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

		if (saveandmovetonext.isSelected()) {
			saveandmovetonext.click();
		}
		Savebutton.click();

		// Check if duplicate PAN message exists

		if (!duplicatePanMsgList.isEmpty() && duplicatePanMsgList.get(0).isDisplayed()) {
			WebElement duplicatePanMsg = duplicatePanMsgList.get(0);

			System.out.println("Duplicate PAN message shown: " + duplicatePanMsg.getText());

			yesoptions.click();

			System.out.println("Clicked on Yes");
		} else {
			System.out.println("No duplicate PAN message, skipping...");
		}
		Thread.sleep(2000);

		if (!duplicatesnapshot.isEmpty() && duplicatesnapshot.get(0).isDisplayed()) {
			System.out.println("Duplicate Snapshot message shown: " + duplicatesnapshot.get(0).getText());

			yes_Options.click();

			System.out.println("Clicked on Yes");
		} else {
			System.out.println("Duplicate Snapshot message not shown");
		}

		Thread.sleep(2000);

		System.out.println("Snapshot created successfully");

	}

	// --------------------------------------------------------------------------------------------------------------

	public void deleteSnapshot() throws InterruptedException {
		Thread.sleep(4000);

		js.executeScript("window.scrollBy(0, 500)");

		deleteicon.click();
		delete_yes.click();
		Thread.sleep(2000);
	}
	// -------------------------------------------------------------------------------------------------------------

	public void deleteselectedsnapshot() throws InterruptedException {

		// Open Snapshot
		gotosnapshot.click();

		Thread.sleep(2000);

		actions.moveToElement(selectTimePeriod).moveByOffset(200, 0).perform();

		// Time period selection
		wait.until(ExpectedConditions.elementToBeClickable(selectTimePeriod));
		selectTimePeriod.click();

		wait.until(ExpectedConditions.elementToBeClickable(SinceBeginning));
		SinceBeginning.click();

		Thread.sleep(2000);

		ClickOnDraft.click();

		js.executeScript("window.scrollBy(0, 200)");
		Thread.sleep(1500);

		wait.until(ExpectedConditions.visibilityOfAllElements(snapshotlisting));

		// Select 1st snapshot row
		wait.until(ExpectedConditions.elementToBeClickable(DraftFirstSnapshot));
		DraftFirstSnapshot.click();

		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(4000);

		wait.until(ExpectedConditions.invisibilityOf(loader));

		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOf(deleteicon));
		deleteicon.click();
		delete_yes.click();
		Thread.sleep(1500);

	}

	public void bulkuploadsnapshot() throws InterruptedException {

		Thread.sleep(4000);
		gotosnapshot.click();

		contextmenu.click();
		Thread.sleep(2000);

		clickonBulkUpload.click();

		wait.until(ExpectedConditions.visibilityOf(AddFilesfor_BulkUpload));
		AddFilesfor_BulkUpload.click();
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

		wait.until(ExpectedConditions.elementToBeClickable(clickOnStartUpload));
		clickOnStartUpload.click();

		longWait.until(ExpectedConditions.elementToBeClickable(clickOnFinish));
		clickOnFinish.click();

		wait.until(ExpectedConditions.invisibilityOf(bulkuploadLoader));

		System.out.println("Bulk upload successfully uploaded");

	}

	public void ExcelImport() throws InterruptedException {
		Thread.sleep(3000);

		gotosnapshot.click();

		contextmenu.click();
		wait.until(ExpectedConditions.visibilityOf(selectTimePeriod));
		selectTimePeriod.click();

		wait.until(ExpectedConditions.visibilityOf(SinceBeginning));
		SinceBeginning.click();

		contextmenu.click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(ClickOnExcelImport));

		ClickOnExcelImport.click();

		Thread.sleep(2000);

		ClickOnSelectFile_ExcelImport.click();

		Thread.sleep(2000);

		// Decide which file path to use based on element availability
		String filePath = null;

		// Check for Inventory Excel
		List<WebElement> inventoryList = driver.findElements(By.xpath("//h2[normalize-space()='Excel Inventory']"));
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

			ClickonUpload_ExcelImport.click();

			wait.until(ExpectedConditions.visibilityOf(ExcelImportSummary_Okaybutton));
			Thread.sleep(2000);
			ExcelImportSummary_Okaybutton.click();

			wait.until(ExpectedConditions.visibilityOf(Finish_ExcelImport));
			Finish_ExcelImport.click();

			wait.until(ExpectedConditions.invisibilityOf(bulkuploadLoader));
			System.out.println("Excel Import successfully uploaded");

		}
	}

	public boolean snapshotquickentry() throws InterruptedException {

		Thread.sleep(2000);
		gotosnapshot.click();

		contextmenu.click();

		selectTimePeriod.click();

		wait.until(ExpectedConditions.visibilityOf(SinceBeginning));
		SinceBeginning.click();

		contextmenu.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(quickentry));

			quickentry.click();

			Okaybutton.click();
			Thread.sleep(1000);

			List<WebElement> checkboxes = driver
					.findElements(By.xpath("//label[contains(@for,'checkbox')]//div[contains(@class,'check')]"));

			int count = Math.min(2, checkboxes.size());

			if (count == 0) {
				System.out.println("No snapshots available to select.");
			} else {
				for (int i = 0; i < count; i++) {
					wait.until(ExpectedConditions.elementToBeClickable(checkboxes.get(i))).click();
					System.out.println("Clicked snapshot checkbox: " + (i + 1));
				}
			}

			// Add entries
			wait.until(ExpectedConditions.elementToBeClickable(AddEntries));
			AddEntries.click();

			Thread.sleep(1000);

			wait.until(ExpectedConditions.elementToBeClickable(quickentrygstledgerSaveButton));
			quickentrygstledgerSaveButton.click();

			Thread.sleep(5000);
			wait.until(ExpectedConditions.invisibilityOf(QuickentryLoader));

			partygst.click();

			actions.sendKeys(Keys.TAB).perform(); // focus on party ledger dropdown
			actions.sendKeys(Keys.SPACE).perform(); // open the party ledger dropdown
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform(); // select the first ledger

			actions.sendKeys(Keys.TAB).perform(); // focus on purchase/sales ledger dropdown
			actions.sendKeys(Keys.SPACE).perform(); // open the purchase/sales ledger dropdown
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform(); // select the
																										// second ledger

			wait.until(ExpectedConditions.elementToBeClickable(CreateEntries));
			Thread.sleep(1000);
			CreateEntries.click();

			wait.until(ExpectedConditions.invisibilityOf(bulkuploadLoader));

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

		gotosnapshot.click();

		contextmenu.click();

		ClickOnBulkAction.click();

		selectTimePeriod.click();

		SinceBeginning.click();

		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnDraft));
		ClickOnDraft.click();

		Thread.sleep(2000);

		// Select first 2 snapshots
		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//label[contains(@for,'checkbox')]//div[contains(@class,'check')]"));

		int count = Math.min(1, checkboxes.size());

		if (count == 0) {
			System.out.println("No snapshots available to select.");
			return;
		}

	//	JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < 3; i++) {

			checkboxes = driver
					.findElements(By.xpath("//label[contains(@for,'checkbox')]//div[contains(@class,'check')]"));

			WebElement checkbox = checkboxes.get(i);

			js.executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);
			Thread.sleep(500);

			js.executeScript("arguments[0].click();", checkbox);

			System.out.println("Clicked snapshot checkbox : " + (i + 1));

			Thread.sleep(1000);
		}

		// Check if any popup appeared before clicking Delete
		List<WebElement> popup = driver
				.findElements(By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]"));

		if (!popup.isEmpty()) {

			System.out.println("Popup detected : " + popup.get(0).getText());

			List<WebElement> popupButtons = driver
					.findElements(By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]//button"));

			if (!popupButtons.isEmpty()) {
				js.executeScript("arguments[0].click();", popupButtons.get(0));
				Thread.sleep(1000);
			}
		}

		// Click Delete
		wait.until(ExpectedConditions.elementToBeClickable(ClickOnDelete));
		js.executeScript("arguments[0].click();", ClickOnDelete);

		System.out.println("Clicked Delete button");

		// Click Yes on confirmation popup
		wait.until(ExpectedConditions.elementToBeClickable(yes_Options));
		js.executeScript("arguments[0].click();", yes_Options);

		System.out.println("Clicked Yes button");

		Thread.sleep(2000);

		// Handle Information popup if present
//		List<WebElement> infoPopup = driver.findElements(By.xpath("//button[normalize-space()='Okay']"));
//
//		if (!infoPopup.isEmpty()) {
//
//			wait.until(ExpectedConditions.elementToBeClickable(infoPopup.get(0)));
//
//			js.executeScript("arguments[0].click();", infoPopup.get(0));
		
		Okaybutton.click();
		

			System.out.println("Clicked Okay button");
		

		System.out.println("Bulk snapshot delete flow completed.");
	}

	/*
	 * public void bulkaction() throws InterruptedException {
	 * 
	 * Thread.sleep(4000);
	 * 
	 * gotosnapshot.click();
	 * 
	 * contextmenu.click();
	 * 
	 * ClickOnBulkAction.click();
	 * 
	 * selectTimePeriod.click();
	 * 
	 * SinceBeginning.click(); Thread.sleep(1000);
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(ClickOnDraft));
	 * ClickOnDraft.click(); Thread.sleep(1000);
	 * 
	 * // for (int i = 0; i <= 2; i++) { // WebElement checkbox =
	 * wait.until(ExpectedConditions.elementToBeClickable( //
	 * By.xpath("//label[contains(@for,'checkbox" + i +
	 * "')]//div[contains(@class,'check')]"))); // checkbox.click(); //
	 * System.out.println("Clicked checkbox" + i); // }
	 * 
	 * // List<WebElement> checkboxes = driver // .findElements(By.xpath(
	 * "//label[contains(@for,'checkbox')]//div[contains(@class,'check')]")); // //
	 * int count = Math.min(2, checkboxes.size()); // // if (count == 0) { //
	 * System.out.println("No snapshots available to select."); // } else { // for
	 * (int i = 0; i < count; i++) { //
	 * wait.until(ExpectedConditions.elementToBeClickable(checkboxes.get(i))).click(
	 * ); // System.out.println("Clicked snapshot checkbox: " + (i + 1)); // } // }
	 * 
	 * // for (int i = 0; i < 2; i++) { // // List<WebElement> checkboxes =
	 * driver.findElements( //
	 * By.xpath("//label[contains(@for,'checkbox')]//div[contains(@class,'check')]")
	 * // ); // // if (checkboxes.isEmpty()) { // //
	 * System.out.println("No snapshots available to select."); // break; // } // //
	 * // Select last snapshot // WebElement checkbox =
	 * checkboxes.get(checkboxes.size() - 1); // //
	 * wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click(); // //
	 * System.out.println("Clicked snapshot checkbox: " + (i + 1));
	 * 
	 * List<WebElement> checkboxes = driver.findElements(
	 * By.xpath("//label[contains(@for,'checkbox')]//div[contains(@class,'check')]")
	 * );
	 * 
	 * int snapshotsToSelect = Math.min(2, checkboxes.size());
	 * 
	 * for (int i = 0; i < snapshotsToSelect; i++) {
	 * 
	 * WebElement checkbox = checkboxes.get(i);
	 * 
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].scrollIntoView({block:'center'});", checkbox);
	 * 
	 * Thread.sleep(1000);
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(checkbox)) .click();
	 * 
	 * System.out.println("Clicked snapshot checkbox: " + (i + 1)); }
	 * 
	 * // Delete button click
	 * driver.findElement(By.xpath("//button[normalize-space()='Delete']")).click();
	 * 
	 * // Confirmation popup if applicable //
	 * driver.findElement(By.xpath("//button[text()='Yes']")).click();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * 
	 * // Click Delete button
	 * wait.until(ExpectedConditions.elementToBeClickable(ClickOnDelete));
	 * ClickOnDelete.click();
	 * 
	 * 
	 * // Confirm Yes
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(yes_Options));
	 * yes_Options.click();
	 * 
	 * // Information pop up - some snapshot are not deleted as they are linked
	 * 
	 * List<WebElement> infopopup = driver .findElements(By.xpath(
	 * "//div[@class='center_apply_btn_new']//button[normalize-space()='Okay']"));
	 * 
	 * ////div[contains(@class,'modal-content')]//p[text()='Successful']
	 * 
	 * if (!infopopup.isEmpty()) { WebElement yes =
	 * wait.until(ExpectedConditions.elementToBeClickable(infopopup.get(0)));
	 * js.executeScript("arguments[0].click();", yes);
	 * System.out.println("Clicked on Ok button of Information popup."); } else {
	 * System.out.println("Ok button not found, skipping click."); }
	 * 
	 * // Wait for success dialog and click OK WebElement okBtn =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[text()='Okay']"))); Thread.sleep(1500); okBtn.click();
	 * System.out.println("Bulk snapshot deleted successfully");
	 * 
	 * }
	 */

	public void ExportList() throws InterruptedException {

		Thread.sleep(2000);

		gotosnapshot.click();

		contextmenu.click();

		selectTimePeriod.click();

		SinceBeginning.click();

		// Example: Click "Export List" option
		contextmenu.click();
		Thread.sleep(1000);

		ClickOnExportList.click();
		System.out.println("Export list downloaded successfully");

	}

	public void Create_reimbursement_Snapshot() throws InterruptedException {

		gotocreatesnapshot.click();

		js.executeScript("window.scrollBy(0,-300)");
		reimbursement_checkbox.click();
		date.sendKeys(config.getsnapshotdate());

		date.sendKeys(Keys.ENTER);

		invoicenum.sendKeys(random.getinvoicenum());

		totalamount.sendKeys(random.gettotalamt());

		partyname_dropdown.click();

		Thread.sleep(1000);

		AddNewParty.click();

		Thread.sleep(1000);

		merchant.sendKeys(random.getpartyname());

		gstNo.sendKeys(random.getpartygstin());

		PanNo.click();

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

		if (saveandmovetonext.isSelected()) {
			saveandmovetonext.click();
		}
		Savebutton.click();

		// Check if duplicate PAN message exists

		if (!duplicatePanMsgList.isEmpty() && duplicatePanMsgList.get(0).isDisplayed()) {
			WebElement duplicatePanMsg = duplicatePanMsgList.get(0);

			System.out.println("Duplicate PAN message shown: " + duplicatePanMsg.getText());

			yesoptions.click();

			System.out.println("Clicked on Yes");
		} else {
			System.out.println("No duplicate PAN message, skipping...");
		}
		Thread.sleep(2000);

		if (!duplicatesnapshot.isEmpty() && duplicatesnapshot.get(0).isDisplayed()) {
			System.out.println("Duplicate Snapshot message shown: " + duplicatesnapshot.get(0).getText());

			yes_Options.click();

			System.out.println("Clicked on Yes");
		} else {
			System.out.println("Duplicate Snapshot message not shown");
		}

		Thread.sleep(2000);

		System.out.println("Snapshot created successfully");

	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")))
				.getText();
	}

	public String getBulkUploadStatus() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement status = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Snapshot bulk upload completed')]")));

		String actual = status.getText().trim();
		// System.out.println(actual);
		return actual;

	}

}
