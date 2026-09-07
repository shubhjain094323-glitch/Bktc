package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadConfigFile;

public class Moneylink {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	Logger logger;

	// Constructor
	public Moneylink(WebDriver driver) throws FileNotFoundException, AWTException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		logger = LogManager.getLogger(this.getClass());
		

	}

	@FindBy(xpath = "//span[@class='icon-Link-icon']")
	WebElement gotoMoneylink;

	@FindBy(xpath = "//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")
	WebElement contextMenu;

	@FindBy(xpath = "//div[@id='mui-component-select-financial_institute']")
	WebElement selectbank;

	@FindBy(xpath = "//body/div[@id='root']/div[@id='page-top']/div/main[@class='content-wrapper-new']/div[@class='container-fluid container-padding-new']/div[@class='row mainFilter-new sec-mb']/div[@class='col-12']/div[@class='top-new-filter']/div[@class='d-flex flex-wrap align-items-center gap-10']/div[@class='filter-bar-new filter-bar-wrap dropdown_moneyin']/div[2]/div[1]")
	WebElement TimePeriod;

	@FindBy(xpath = "//li[normalize-space()='Since Beginning']")
	WebElement SinceBeginning;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='S']")
	WebElement ClickOnSingleEntry;

	@FindBy(xpath = "//p[normalize-space()='Total']")
	WebElement ClickonTotalWidgets;

	@FindBy(xpath = "//tbody/tr[1]/td[4]")
	WebElement SelectFirstRecord;

	@FindBy(xpath = "//li[normalize-space()='Search']")
	WebElement ClickonSearchEvidence;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	WebElement EvidenceFilterApply;

	@FindBy(xpath = "//span[@class='evi-card-amount ellips-auto']")
	WebElement dragEvidence;

	@FindBy(xpath = "//div[@class='evi-cards-list']")
	WebElement dropEvidence;

	@FindBy(xpath = "//div[contains(text(),'Select voucher type')]")
	WebElement SelectVoucherType;

	@FindBy(xpath = "//div[@class='Select-Search css-2b097c-container']//div[@class='Select SelectLedger __value-container Select SelectLedger __value-container--has-value css-1hwfws3']")
	WebElement SelectLedger;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement SaveTransaction;

	@FindBy(xpath = "//span[normalize-space()='Edit Allocation']")
	WebElement ClickOnEditAllocation;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='T']")
	WebElement ClickOnTransactionReset;

	@FindBy(xpath = "//label[contains(@for,'checkboxHeader')]//div[@class='check']")
	WebElement ClickonCheckBox;

	@FindBy(xpath = "//button[normalize-space()='Reset Transactions']")
	WebElement ResetTransaction;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement ClickOnYes;

	@FindBy(xpath = "//button[normalize-space()='Okay']")
	WebElement ClickOnOkay;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='C']")
	WebElement ClickOnCR_BulkEntries;

	@FindBy(xpath = "//a[normalize-space()='Add Entries']")
	WebElement CLickonAddEntries;

	@FindBy(css = ".ledger-right-bar")
	WebElement ClickOnRefreshRecommendation;

	@FindBy(xpath = "//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']")
	WebElement ClickonSelectVoucherType;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='D']")
	WebElement ClickOnDR_BulkEntries;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='Q']")
	WebElement ClickOnMoneylink_Quick_Entry;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='X']")
	WebElement ClickOnMoneylink_ExportList;

	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-body')]")
	WebElement toastmsg;

	public void openMoneylinkPage() throws InterruptedException {
		logger.info("Redirecting to MoneyLink Module");

		wait.until(ExpectedConditions.elementToBeClickable(gotoMoneylink));
		gotoMoneylink.click();
		Thread.sleep(1000);
		logger.info("Opened MoneyLink Module");
	}

	// Common method to open Moneylink, select bank, and choose "Since Beginning"
	public void setupMoneylink() throws InterruptedException {

		Thread.sleep(2000);
		
		logger.info("Setup moneylink - Bank and Time period selection ");

		contextMenu.click();

		// Bank selection
		wait.until(ExpectedConditions.elementToBeClickable(selectbank));
		Thread.sleep(1000);
		selectbank.click();

		String bankName = config.getSelectbank();
		WebElement option = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[contains(normalize-space(),'" + bankName + "')]")));
		Thread.sleep(1500);
		option.click();

		Thread.sleep(2000);

		// Time period selection
		wait.until(ExpectedConditions.elementToBeClickable(TimePeriod));
		TimePeriod.click();

		wait.until(ExpectedConditions.elementToBeClickable(SinceBeginning));
		SinceBeginning.click();

		Thread.sleep(1500);

	}

	public void moneylinksingletransaction() throws InterruptedException {

		Thread.sleep(4000);

		contextMenu.click();
		Thread.sleep(1500);

		ClickOnSingleEntry.click();

		ClickonTotalWidgets.click();

		js.executeScript("window.scrollBy(0, 200)");

		js.executeScript("arguments[0].click();", SelectFirstRecord);
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(ClickonSearchEvidence));
		ClickonSearchEvidence.click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(EvidenceFilterApply));
		EvidenceFilterApply.click();
		Thread.sleep(3000);

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dragEvidence);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropEvidence);

		Thread.sleep(1000);

		// Perform drag and drop

		actions.clickAndHold(dragEvidence).moveToElement(dropEvidence).release().perform();

		// System.out.println("Drag and drop executed!");

		wait.until(ExpectedConditions.elementToBeClickable(SelectVoucherType));
		SelectVoucherType.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(SelectLedger));
		SelectLedger.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		// Thread.sleep(4000);

		String toast = getToastMessage();

		System.out.println("Toast msg " + toast);

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

		wait.until(ExpectedConditions.elementToBeClickable(SaveTransaction));
		SaveTransaction.click();

		String toast1 = getToastMessage();

		System.out.println("After save Toast msg " + toast1);

		// Thread.sleep(1500);
		if (toast1.equalsIgnoreCase("Transaction saved")) {
			System.out.println("Transaction saved successfully");
			return;
		}

		System.out.println("Transaction was not saved. Checking Billwise popup...");

		// Use a very short explicit wait just to check presence
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

		try {
			// Try locating the popup within 2 seconds
			WebElement billwiseamount = shortWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Okay']")));

			if (billwiseamount.isDisplayed()) {
				billwiseamount.click();
				System.out.println("Billwise amount popup found, proceeding...");

				Thread.sleep(2000);

				js.executeScript("window.scrollBy(0, -500)");

				wait.until(ExpectedConditions.elementToBeClickable((ClickOnEditAllocation)));
				Thread.sleep(1500);
				ClickOnEditAllocation.click();

				WebElement addnewrow = driver.findElement(By.xpath("//button[normalize-space()='Add New Row']"));
				addnewrow.click();

				WebElement selectref = driver.findElement(By.xpath("(//select[@name='ref_select'])[2]"));
				Select select = new Select(selectref);
				select.selectByVisibleText("On Account");

				driver.findElement(By.xpath(
						"//div[contains(@class,'reset_apply_btn_new mt-2')]//button[contains(@type,'button')][normalize-space()='Save']"))
						.click();

				SaveTransaction.click();
				Thread.sleep(3000);

				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

			}

		} catch (TimeoutException e) {
			// If popup does not appear within 2 sec → skip without error
			System.out.println("Billwise amount popup not found, skipping allocation steps.");
		}

		// System.out.println("Transaction saved successfully");

	}

	public void crbulkentries() throws InterruptedException {

		// Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(contextMenu));
		contextMenu.click();

		Thread.sleep(1500);

		ClickOnCR_BulkEntries.click();

		Thread.sleep(2000);

		ClickonCheckBox.click();

		CLickonAddEntries.click();
		Thread.sleep(1500);

		ClickOnRefreshRecommendation.click();
		Thread.sleep(1500);

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnYes)).click();

		ClickonSelectVoucherType.click();

		WebElement vouchertype = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Receipt']")));
		Thread.sleep(1500);
		vouchertype.click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//div[@class='Select SelectLedger __control css-1cx65n-control']")).click();
		Thread.sleep(2000);
		actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();

		driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).click();

		Thread.sleep(2000);
		ClickOnYes.click();
		Thread.sleep(3000);

		logger.info("CR bulk entries Done");

	}

	public void drbulkentries() throws InterruptedException {

		Thread.sleep(4000);

		wait.until(ExpectedConditions.elementToBeClickable(contextMenu));
		contextMenu.click();
		Thread.sleep(1500);

		ClickOnDR_BulkEntries.click();
		Thread.sleep(2000);

		ClickonCheckBox.click();
		CLickonAddEntries.click();
		Thread.sleep(1500);

		ClickOnRefreshRecommendation.click();
		Thread.sleep(1500);

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnYes)).click();

		ClickonSelectVoucherType.click();

		WebElement vouchertype = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Payment']")));
		Thread.sleep(1500);
		vouchertype.click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//div[@class='Select SelectLedger __control css-1cx65n-control']")).click();
		Thread.sleep(2000);
		actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		Thread.sleep(1500);
		actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();

		driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		Thread.sleep(3000);

		logger.info("DR bulk entries Done");

	}

	public void moneylinkquickentry() throws InterruptedException {
		Thread.sleep(3000);

		contextMenu.click();
		Thread.sleep(1500);

		ClickOnMoneylink_Quick_Entry.click();
		Thread.sleep(1000);
		// Select first moneylink for the quick entry
		for (int i = 0; i < 1; i++) {
			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(@for,'checkbox" + i + "')]//div[contains(@class,'check')]")));
			checkbox.click();
			System.out.println("Clicked checkbox" + i);
		}

		CLickonAddEntries.click();
		Thread.sleep(2500);

		By partyledgerdropdwon = By.xpath("//div[@class='Select type-select __value-container css-1nol46l']");

		WebElement partyledger = wait.until(ExpectedConditions.elementToBeClickable(partyledgerdropdwon));
		partyledger.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).click();
		Thread.sleep(3000);

		logger.info("MoneyLink QuickEntry Done");

	}

	public void transactionreset() throws InterruptedException {

		Thread.sleep(3000);

		contextMenu.click();
		Thread.sleep(1500);

		ClickOnTransactionReset.click();
		Thread.sleep(1000);

		ClickonCheckBox.click();
		Thread.sleep(1000);

		List<WebElement> selectAcross = driver
				.findElements(By.xpath("//button[normalize-space()='Select across all pages']"));

		if (!selectAcross.isEmpty()) {
			selectAcross.get(0).click();
		}

		wait.until(ExpectedConditions.elementToBeClickable(ResetTransaction));

		ResetTransaction.click();

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnYes));
		ClickOnYes.click();

		Thread.sleep(15000);

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

		WebElement msg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pmsg-blue-center']")));

		String actualReset = msg.getText();
		System.out.println("Reset Message: " + actualReset);

		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOf(ClickOnOkay));
		ClickOnOkay.click();

		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Okay']"))).click();

		Thread.sleep(2000);

		// ClickOnOkay.click();

		logger.info("Transaction reseted");

	}

	public void exportlist() throws InterruptedException {
		Thread.sleep(4000);

		contextMenu.click();
		Thread.sleep(1500);

		ClickOnMoneylink_ExportList.click();
		Thread.sleep(1500);

		logger.info("Export Successfully");


	}

	public String getToastMessage() {

		try {

			WebElement toast = wait.until(ExpectedConditions.visibilityOf(toastmsg));

			return toast.getText().trim();

		} catch (Exception e) {

			System.out.println("Toast message not found");
			return "";
		}
	}
}
