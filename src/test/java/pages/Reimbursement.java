package pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reimbursement {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebDriverWait longWait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData random;

	public Reimbursement(WebDriver driver) throws Exception {
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

	@FindBy(xpath = "//li[@class='hamburger-menu']//div[@id='dropdown-basic']")
	WebElement ClickOnHamburger;

	@FindBy(xpath = "//div[@class='d-flex menu-gap padding-menu']//a")
	List<WebElement> listofMenus;

	@FindBy(xpath = "//a[@href='/reimbursement']")
	WebElement ClickOnReimbursement;

	@FindBy(xpath = "//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']")
	WebElement selectTimePeriod;

	@FindBy(xpath = "//li[normalize-space()='Since Beginning']")
	WebElement SinceBeginning;

	@FindBy(xpath = "//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")
	WebElement ContextMenu_Reimbursement;

	@FindBy(xpath = "//button[@class='btn btn-success add-new-btn add-btn-height']")
	WebElement ClickOnAddNewReimbursement;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='E']")
	WebElement ClickOnExcelImport;

	@FindBy(xpath = "//label[normalize-space()='Select File']")
	WebElement ClickOnSelectFile_ExcelImport;

	@FindBy(xpath = "//div[@col-id='total_amount']")
	List<WebElement> totalamountclm;

	@FindBy(xpath = "//div[contains(@class,'ag-row')]")
	List<WebElement> rows;

	@FindBy(xpath = ".//span[@class='ie_error_dot ie_red_dot']")
	List<WebElement> errormsg;

	@FindBy(xpath = ".//span[@role='button']")
	List<WebElement> deleteicons;

	@FindBy(xpath = "//div[contains(@class,'ag-body-horizontal-scroll-viewport')]")
	WebElement scrollbar;

	@FindBy(xpath = "//button[normalize-space()='Upload']")
	WebElement ClickonUpload_ExcelImport;

	@FindBy(xpath = "//button[normalize-space()='Okay']")
	WebElement ExcelImportSummary_Okaybutton;

	@FindBy(xpath = "//div[@class='bottom_save_btn']//button[text()='Finish']")
	WebElement Finish_ExcelImport;

	@FindBy(xpath = "//span[@class='shortcut_keys_action' and text()='A']")
	WebElement ClickOnBulkAction;

	@FindBy(xpath = "//label[@for='checkboxHeader']")
	WebElement clickonCheckbox;

	@FindBy(xpath = "//div[@id='mui-component-select-bulk_action_option']")
	WebElement dropdown_delete;

	@FindBy(xpath = "//li[normalize-space()='Delete']")
	WebElement clickon_Delete;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement Clickon_Yes;

	public void reimbursement_ExcelImport() throws InterruptedException {

		boolean found = false;

		ClickOnHamburger.click();
		Thread.sleep(2000);

		for (WebElement menu : listofMenus) {
			String menulist = menu.getText().trim();
			// System.out.println("Menu list " + menulist);

			// System.out.println(menulist);

			if (menulist.contains("ReImbursement")) {
				// ClickOnReimbursement.click();
				wait.until(ExpectedConditions.elementToBeClickable(ClickOnReimbursement)).click();
				found = true;
				break;
			}

		}
		if (!found) {
			System.out
					.println("Reimbursement is not enabled for the entity. Please enable it from Profile Management.");
			return;
		}

		wait.until(ExpectedConditions.elementToBeClickable(ContextMenu_Reimbursement)).click();

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnExcelImport)).click();
		Thread.sleep(1500);

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnSelectFile_ExcelImport)).click();

		Thread.sleep(1500);

		StringSelection ss = new StringSelection(config.getReimbursementExcelImportPath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		robot.delay(1000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		robot.delay(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(1500);

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		Thread.sleep(1500);

		List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class,'ag-row')]"));

		System.out.println("Total rows: " + rows.size());

		for (int i = rows.size() - 1; i >= 0; i--) {

			WebElement row = rows.get(i);

			// check error in row
			List<WebElement> error = row.findElements(By.xpath(".//span[@class='ie_error_dot ie_red_dot']"));

			if (!error.isEmpty()) {
				System.out.println("Error found in row index: " + i);

				// scroll right (if delete icon is on right side)
				js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollbar);
				Thread.sleep(1000);

				// click delete icon inside same row
				WebElement deleteIcon = row.findElement(By.xpath(".//span[@role='button']"));
				deleteIcon.click();

				Thread.sleep(1500);

				// scroll back left
				js.executeScript("arguments[0].scrollLeft = 0;", scrollbar);
				Thread.sleep(1000);
			}
		}

		if (ClickonUpload_ExcelImport.isEnabled()) {
			ClickonUpload_ExcelImport.click();
		}

		wait.until(ExpectedConditions.elementToBeClickable(ExcelImportSummary_Okaybutton)).click();

		wait.until(ExpectedConditions.elementToBeClickable(Finish_ExcelImport)).click();

	}

	public void Delete_Reimbursement() throws InterruptedException {

	

		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(selectTimePeriod)).click();
	//	selectTimePeriod.click();
		SinceBeginning.click();

		wait.until(ExpectedConditions.elementToBeClickable(ContextMenu_Reimbursement)).click();
		// ContextMenu_Reimbursement.click();
		wait.until(ExpectedConditions.elementToBeClickable(ClickOnBulkAction)).click();
		// ClickOnBulkAction.click();
		wait.until(ExpectedConditions.elementToBeClickable(clickonCheckbox)).click();
		// clickonCheckbox.click();
		wait.until(ExpectedConditions.elementToBeClickable(dropdown_delete)).click();
		// dropdown_delete.click();
		wait.until(ExpectedConditions.elementToBeClickable(clickon_Delete)).click();
		// clickon_Delete.click();
		wait.until(ExpectedConditions.elementToBeClickable(Clickon_Yes)).click();
		// Clickon_Yes.click();

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
