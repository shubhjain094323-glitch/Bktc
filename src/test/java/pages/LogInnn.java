package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LogInnn {

	public static void main(String[] args)
			throws FileNotFoundException, InterruptedException, TimeoutException, AWTException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		ReadConfigFile config = new ReadConfigFile();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Robot robot = new Robot();
		Properties properties = new Properties();

		// driver.get("https://qafe.buktec.co.in/login");

		driver.get(config.getURL());

		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@Class = 'btn btn-success apply_btn_new']")).click();

		Thread.sleep(3000);

		driver.findElement(By.name("emailId")).sendKeys(config.getaccountcode());

		driver.findElement(By.name("username")).sendKeys(config.getusername());

		driver.findElement(By.name("password")).sendKeys(config.getpassword());

		driver.findElement(By.tagName("button")).click();

// -------------------------------------------------------------------------------------------------------
		WebElement banking = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/financial-institutes']")));
		banking.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn ico-refresh-sec blink-green-btn']")).click();

		WebElement addbank = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='bank-card add-bank-card addbank_bankin']")));
		addbank.click();
		Thread.sleep(1000);

		WebElement bankdropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'type-select') and contains(@class,'__control')]")));
		bankdropdown.click();

		WebElement selectbank = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'type-select')]//input[@id='react-select-2-input']")));

		selectbank.sendKeys(config.getaddbank());
		actions.sendKeys(Keys.ENTER).perform();

		WebElement banktype = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'type-select')]//input[@id='react-select-3-input']")));

		banktype.sendKeys(config.getselectbanktype());
		actions.sendKeys(Keys.ENTER).perform();

		WebElement bankaccountname = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='account_name']")));

		bankaccountname.sendKeys(config.getbankaccountname());

		WebElement bankaccountnumber = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='account_number']")));

		bankaccountnumber.sendKeys(config.getbankaccountnumber());

		
	
	}

	/*
	 * WebElement snapshot = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click(); Thread.sleep(2000);
	 * 
	 * driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).
	 * click();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.
	 * ENTER).build().perform(); Thread.sleep(2000);
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='ie_upload_file']//label[normalize-space()='Select File']"
	 * )).click();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * 
	 * // Decide which file path to use based on element availability String
	 * filePath = null;
	 * 
	 * // Check for Inventory Excel List<WebElement> inventoryList =
	 * driver.findElements(By.xpath("//h2[normalize-space()='Excel inventory']"));
	 * if (!inventoryList.isEmpty() && inventoryList.get(0).isDisplayed()) {
	 * System.out.println("Inventory Excel option found"); filePath =
	 * config.getInventoryExcelPath(); } else { // Check for Simple Excel
	 * List<WebElement> simpleList =
	 * driver.findElements(By.xpath("//span[contains(@class,'mr-3')]")); if
	 * (!simpleList.isEmpty() && simpleList.get(0).isDisplayed()) {
	 * System.out.println("Simple Excel option found"); filePath =
	 * config.getExcelfilepath(); } else { throw new
	 * RuntimeException("Neither Inventory Excel nor Simple Excel option found!"); }
	 * }
	 * 
	 * // Upload the file using Robot if (filePath != null) { StringSelection
	 * selection = new StringSelection(filePath);
	 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,
	 * null);
	 * 
	 * // Press CTRL + V robot.keyPress(KeyEvent.VK_CONTROL);
	 * robot.keyPress(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_CONTROL);
	 * 
	 * // Press Enter robot.keyPress(KeyEvent.VK_ENTER);
	 * robot.keyRelease(KeyEvent.VK_ENTER);
	 * 
	 * Thread.sleep(4000);
	 * 
	 * 
	 * 
	 * List<WebElement> ignoreList = driver.findElements(By.xpath(
	 * "//div[@class='col-xl-12 col-lg-12 col-md-12 mt-0 mt-lg-0 mt-md-3 text-right d-flex align-items-center justify-content-end bottom-save-btn']//span[@class='icon-warning ie_icon_btn yellow-icon']"
	 * ));
	 * 
	 * if (!ignoreList.isEmpty()) { WebElement ignoremsg =
	 * wait.until(ExpectedConditions.elementToBeClickable(ignoreList.get(0)));
	 * Thread.sleep(1500); js.executeScript("arguments[0].click();", ignoremsg);
	 * System.out.println("Ignore message clicked."); } else {
	 * System.out.println("Ignore message not found, skipping click."); }
	 * 
	 * Thread.sleep(6000);
	 * 
	 * List<WebElement> yesBtns = driver
	 * .findElements(By.xpath("//button[normalize-space()='Yes']"));
	 * 
	 * if (!yesBtns.isEmpty()) { WebElement yes =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Yes']"))); Thread.sleep(1000);
	 * //js.executeScript("arguments[0].click();", yes); //Actions actions = new
	 * Actions(driver); actions.moveToElement(yes).click().perform();
	 * System.out.println("Clicked on Yes button."); } else {
	 * System.out.println("Yes button not found, skipping click."); }
	 * 
	 * Thread.sleep(1000);
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
	 * 
	 * WebElement finish = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@class='bottom_save_btn']//button[text()='Finish']")));
	 * js.executeScript("arguments[0].click();", finish);
	 * 
	 * System.out.println("Excel Import successfully uploaded");
	 * 
	 * }}
	 */

	// For Reports module

	/*
	 * WebElement reportsLink = wait.until(
	 * ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//a[@href='/reports']")) ); reportsLink.click();
	 * 
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * String exportdata = config.getExportsfields();
	 * System.out.println("Export data is = " +exportdata);
	 * 
	 * driver.findElement(By.xpath("//a[@title='" +exportdata+ "']")).click();
	 * 
	 * driver.findElement(By.xpath("//div[@id='mui-component-select-year_type']")).
	 * click();
	 * 
	 * WebElement bankselect = driver.findElement(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute_id']"));
	 * bankselect.sendKeys(config.getSelectbank()); bankselect.click();
	 * 
	 * 
	 * String timeperiod = config.getTimeperiod();
	 * System.out.println("Selected time period is =" +timeperiod);
	 * 
	 * driver.findElement(By.xpath("//li[normalize-space()='" +timeperiod+
	 * "']")).click();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Export']")).click();
	 */

	/*
	 * WebElement reportsLink = wait.until(
	 * ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//a[@href='/reports']")) ); reportsLink.click();
	 * 
	 * // Click somewhere offset (maybe to close popup or focus)
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * String exportdata = config.getExportsfields();
	 * System.out.println("Export data is = " + exportdata); Thread.sleep(1500);
	 * 
	 * // Click export option driver.findElement(By.xpath("//a[@title='" +
	 * exportdata + "']")).click();
	 * 
	 * 
	 * 
	 * // 👉 Run this part ONLY if exportdata is Bank statement if
	 * (exportdata.equalsIgnoreCase("Bank statements")) {
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute_id']")).click();
	 * 
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(
	 * By.xpath("//ul[contains(@class,'MuiList-root')]") ));
	 * 
	 * String bankName = config.getSelectbank();
	 * System.out.println("Bank selected: " + bankName); WebElement bankOption =
	 * wait.until( ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[contains(normalize-space(),'" + bankName + "')]")));
	 * System.out.println("Selected bank - "+bankOption); bankOption.click();
	 * 
	 * 
	 * }
	 * 
	 * // Open time period dropdown
	 * driver.findElement(By.xpath("//div[@id='mui-component-select-year_type']")).
	 * click();
	 * 
	 * String timeperiod = config.getTimeperiod();
	 * System.out.println("Selected time period is = " + timeperiod);
	 * 
	 * // Select period driver.findElement(By.xpath("//li[normalize-space()='" +
	 * timeperiod + "']")).click();
	 * 
	 * // Click Export button
	 * //driver.findElement(By.xpath("//button[normalize-space()='Export']")).click(
	 * );
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //Dashboard //================ // Navigate to dashboard /*
	 * driver.findElement(By.xpath(
	 * "//div[@class='quick-menu']//a[@href='/dashboard']")).click();
	 * 
	 * // Give time to load page Thread.sleep(2000);
	 * 
	 * // Move to element actions.moveToElement(driver.findElement(By.xpath(
	 * "//div[@id='mui-component-select-year_type']")), 200, 0).perform();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * // Define periods String[] periods = { "Current Financial Year",
	 * "Last Financial Year", "This Month", "Last 3 Months", "Since Beginning" };
	 * 
	 * // For Buktec Summary selectPeriods(driver, wait, periods,
	 * By.id("mui-component-select-year_type"));
	 * 
	 * // For Business Summary Thread.sleep(2000); WebElement businessummary =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//label[@id='2']"))); businessummary.click();
	 * 
	 * selectPeriods(driver, wait, periods,
	 * By.id("mui-component-select-year_type"));
	 * 
	 * // Close browser driver.quit(); }
	 * 
	 * // select multiple periods from dropdown public static void
	 * selectPeriods(WebDriver driver, WebDriverWait wait, String[] periods, By
	 * dropdownLocator) throws InterruptedException { WebElement timeperiod =
	 * wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
	 * 
	 * for (String period : periods) { timeperiod.click(); // open dropdown
	 * 
	 * WebElement element = wait.until( ExpectedConditions.elementToBeClickable(
	 * By.xpath("//li[normalize-space()='" + period + "']")) ); element.click(); //
	 * select option
	 * 
	 * System.out.println("Clicked on: " + period);
	 * 
	 * // Re-fetch dropdown except for the last period if
	 * (!period.equalsIgnoreCase("Since Beginning")) { timeperiod =
	 * wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator)); }
	 * 
	 * Thread.sleep(500); // small pause to let UI update } }
	 */

	// --------------------------------------------------------------------------------------------------------------------------------------

	// For entity selection
	// ============================
	/*
	 * Thread.sleep(2000);
	 * 
	 * driver.findElement(By.xpath(
	 * "//li[@class='main-h-profile']//div[@id='dropdown-basic']")).click();
	 * 
	 * WebElement searchentity =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//input[@placeholder='Search Entity']"))); searchentity.click();
	 * searchentity.sendKeys(config.getentityname()); Thread.sleep(1500);
	 * driver.findElement(By.xpath("//li[@class='main-h-profile']//li[1]")).click();
	 */
	// ---------------------------------------------------------------------------------------------------------------------------------------------

	// ----------------------------------------------------------------------------------------------
	// Profile Management

	// Account details update
	// ==========================

	/*
	 * WebElement hamburger =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
	 * hamburger.click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//a[@href='/profile-management/entities']//span[@class='menu-name']")).click
	 * (); Thread.sleep(1500);
	 * 
	 * driver.findElement(By.xpath("//span[@class='icon-edit']")).click();
	 * 
	 * WebElement accountname =
	 * driver.findElement(By.xpath("//input[@name='full_name']"));
	 * accountname.clear(); accountname.sendKeys(config.AccountName());
	 * 
	 * WebElement mobnumber=
	 * driver.findElement(By.xpath("//input[@name='phone_number']"));
	 * mobnumber.clear(); mobnumber.sendKeys(config.MobileNumber());
	 * 
	 * WebElement gstnumber =
	 * driver.findElement(By.xpath("//input[@name='gst_no']")); gstnumber.clear();
	 * gstnumber.sendKeys(config.GSTNumber());
	 * 
	 * WebElement updateaccount =
	 * driver.findElement(By.xpath("//button[normalize-space()='Update']"));
	 * updateaccount.click();
	 */
	// --------------------------------------------------------------------------------------------------------------------

	// Entity Update
	// ==========================

	/*
	 * WebElement hamburger =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
	 * hamburger.click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//a[@href='/profile-management/entities']//span[@class='menu-name']")).click
	 * (); Thread.sleep(1500);
	 * 
	 * String entityselect = config.Entityselection();
	 * System.out.println("Selected entity for update = " +entityselect);
	 * 
	 * driver.findElement(By.
	 * xpath("//table[@class='table table-bordered']//div[text() ='" +entityselect+
	 * "']")).click(); Thread.sleep(1500);
	 * 
	 * WebElement entityname =
	 * driver.findElement(By.xpath("//input[@name='entity_name']"));
	 * entityname.clear(); entityname.sendKeys(config.Entityname());
	 * Thread.sleep(1500);
	 * 
	 * WebElement entityType = driver.findElement(By.
	 * xpath("//select[@name='company_type_id']//option[text() = 'Individual']"));
	 * entityType.click(); Thread.sleep(1500);
	 * 
	 * WebElement updateEntity =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Update']"))); updateEntity.click();
	 */
	// --------------------------------------------------------------------------------------------------------------------

	// User update
	// ==========================

	/*
	 * WebElement hamburger =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
	 * hamburger.click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//a[@href='/profile-management/entities']//span[@class='menu-name']")).click
	 * (); Thread.sleep(1500);
	 * 
	 * driver.findElement(By.xpath(
	 * "//a[@id='uncontrolled-tab-example-tab-/profile-management/user']")).click();
	 * 
	 * js.executeScript("window.scrollBy(0, 500)");
	 * 
	 * String userselect = config.Userselection();
	 * System.out.println("Selected user is ="+userselect);
	 * 
	 * WebElement clickonuser =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//table[@class='table table-bordered']//div[text() = '" +userselect+
	 * "']"))); System.out.println(clickonuser); clickonuser.click();
	 * Thread.sleep(1500);
	 * 
	 * WebElement updateuser =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Save']"))); updateuser.click();
	 * 
	 * System.out.println("User updated successfully");
	 */

	// -------------------------------------------------------------------------------------------------------------------------
	// Moneylink Export List
	// ========================

	/*
	 * WebElement moneylink = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//span[@class='icon-Link-icon']"))); moneylink.click();
	 * 
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * WebElement bankselection = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute']")));
	 * bankselection.click();
	 * 
	 * // Select option dynamically from config String bankName =
	 * config.Selectbank(); WebElement option = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[normalize-space()='" + bankName + "']"))); option.click();
	 * 
	 * System.out.println("Selected bank: " + bankName); Thread.sleep(2000);
	 * 
	 * // Time period selection WebElement yearDropdown =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary global_new_dd_date css-fvipm8']//div[@id='mui-component-select-year_type']"
	 * ))); yearDropdown.click();
	 * 
	 * WebElement sinceBeginning = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//li[normalize-space()='Since Beginning']"))); sinceBeginning.click();
	 * Thread.sleep(1500);
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']"
	 * )).click(); Thread.sleep(1500);
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='row mainFilter-new sec-mb']//button[7]")).click();
	 * Thread.sleep(1500);
	 * 
	 * System.out.println("Export successfully done");
	 * 
	 * //---------------------------------------------------------------------------
	 * ----------------------------------------
	 * 
	 * // Transaction reset //=====================
	 * 
	 * WebElement moneylink = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//span[@class='icon-Link-icon']"))); moneylink.click();
	 * 
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * WebElement bankselection = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute']")));
	 * bankselection.click();
	 * 
	 * // Select option dynamically from config String bankName =
	 * config.Selectbank(); WebElement option = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[normalize-space()='" + bankName + "']"))); option.click();
	 * 
	 * System.out.println("Selected bank: " + bankName); Thread.sleep(2000);
	 * 
	 * // Time period selection WebElement yearDropdown =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary global_new_dd_date css-fvipm8']//div[@id='mui-component-select-year_type']"
	 * ))); yearDropdown.click();
	 * 
	 * WebElement sinceBeginning = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//li[normalize-space()='Since Beginning']"))); sinceBeginning.click();
	 * Thread.sleep(1500);
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']"
	 * )).click(); Thread.sleep(1500);
	 * 
	 * driver.findElement(By.xpath("//button[5]//span[2]")).click();
	 * Thread.sleep(1000);
	 * 
	 * driver.findElement(By.xpath(
	 * "//label[contains(@for,'checkboxHeader')]//div[contains(@class,'check')]")).
	 * click(); Thread.sleep(1000);
	 * 
	 * driver.findElement(By.
	 * xpath("//button[normalize-space()='Select across all pages']")).click();
	 * 
	 * WebElement resetbutton = wait.until(
	 * ExpectedConditions.elementToBeClickable(By.
	 * xpath("//button[normalize-space()='Reset Transactions']")));
	 * resetbutton.click();
	 * 
	 * WebElement yesbutton = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Yes']"))); yesbutton.click();
	 * 
	 * WebElement okbutton =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[text()='OK']"))); okbutton.click();
	 * 
	 * driver.findElement(By.xpath("//a[normalize-space()='money link']")).click();
	 * 
	 * System.out.println("Transaction reseted successfully");
	 */

	// ---------------------------------------------------------------------------------------------------------------------

	// DR Bulk Entries
	// =====================
	/*
	 * WebElement moneylink = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//span[@class='icon-Link-icon']"))); moneylink.click();
	 * 
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * WebElement bankselection = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute']")));
	 * bankselection.click();
	 * 
	 * // Select option dynamically from config String bankName =
	 * config.Selectbank(); WebElement option = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[normalize-space()='" + bankName + "']"))); option.click();
	 * 
	 * System.out.println("Selected bank: " + bankName); Thread.sleep(2000);
	 * 
	 * // Time period selection WebElement yearDropdown =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary global_new_dd_date css-fvipm8']//div[@id='mui-component-select-year_type']"
	 * ))); yearDropdown.click();
	 * 
	 * WebElement sinceBeginning = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//li[normalize-space()='Since Beginning']"))); sinceBeginning.click();
	 * Thread.sleep(1500);
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']"
	 * )).click(); Thread.sleep(1500);
	 * 
	 * driver.findElement(By.xpath("//div[@class='dropdown-menu show']//button[3]"))
	 * .click(); // actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
	 * Thread.sleep(2000);
	 * 
	 * driver.findElement(By.xpath("//div[@class='check']")).click();
	 * 
	 * driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();
	 * 
	 * driver.findElement(By.xpath("//select[@name='voucher_type_guid']")).click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//select[@name='voucher_type_guid']//option[text()='Payment']")).click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@class='Select SelectLedger __value-container Select SelectLedger __value-container--has-value css-1hwfws3']"
	 * )) .click(); Thread.sleep(2000); actions.sendKeys(Keys.ARROW_DOWN,
	 * Keys.ENTER).perform();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).
	 * click();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
	 * 
	 * System.out.println("DR Bulk entries done");
	 */

	// -------------------------------------------------------------------------------------------------------------------------------
	/*
	 * // CR Bulk entries selection //==============================
	 * driver.findElement(By.xpath("//div[@class='dropdown-menu show']//button[1]"))
	 * .click(); actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
	 * Thread.sleep(2000);
	 * 
	 * driver.findElement(By.xpath("//div[@class='check']")).click();
	 * 
	 * driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();
	 * 
	 * driver.findElement(By.xpath("//select[@name='voucher_type_guid']")).click();
	 * 
	 * driver.findElement(By.xpath(
	 * "//select[@name='voucher_type_guid']//option[text()='Receipt']")).click();
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='Select SelectLedger __value-container Select SelectLedger __value-container--has-value css-1hwfws3']"
	 * )).click(); Thread.sleep(2000);
	 * actions.sendKeys(Keys.ARROW_DOWN,Keys.ENTER).perform();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).
	 * click();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
	 * 
	 * System.out.println("Bulk CR entries done");
	 */

	// -----------------------------------------------------------------------------

	// Moneylink single transaction
	// =====================================

	// Open Moneylink /* WebElement moneylink = wait.until(ExpectedConditions
	/*
	 * .elementToBeClickable(By.xpath("//span[@class='icon-Link-icon']")));
	 * moneylink.click();
	 * 
	 * actions.moveByOffset(200, 400).click().perform();
	 * 
	 * WebElement bankselection = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@id='mui-component-select-financial_institute']")));
	 * bankselection.click();
	 * 
	 * // Select option dynamically from config String bankName =
	 * config.Selectbank(); WebElement option = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[normalize-space()='" + bankName + "']"))); option.click();
	 * 
	 * System.out.println("Selected bank: " + bankName); Thread.sleep(2000);
	 * 
	 * // Time period selection WebElement yearDropdown =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary global_new_dd_date css-fvipm8']//div[@id='mui-component-select-year_type']"
	 * ))); yearDropdown.click();
	 * 
	 * WebElement sinceBeginning = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//li[normalize-space()='Since Beginning']"))); sinceBeginning.click();
	 * Thread.sleep(1500);
	 * 
	 * WebElement transaction = driver.findElement(By.xpath(
	 * "//div[@class='react-bs-container-body']//tbody/tr[2]/td[4]"));
	 * js.executeScript("arguments[0].click();", transaction);
	 * 
	 * WebElement searchevidence =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//li[normalize-space()='Search']"))); searchevidence.click();
	 * 
	 * WebElement filterapply =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Apply']"))); filterapply.click();
	 * Thread.sleep(3000);
	 */

	/*
	 * List<WebElement> dragList =
	 * driver.findElements(By.xpath("//span[@class='evi-card-amount ellips-auto']"))
	 * ;
	 * 
	 * if (!dragList.isEmpty()) { WebElement dragevidence = dragList.get(0);
	 * WebElement dropevidence =
	 * driver.findElement(By.xpath("//div[@class='evi-cards-list']"));
	 * 
	 * // Scroll both into view ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", dragevidence);
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", dropevidence);
	 * 
	 * Thread.sleep(1000);
	 * 
	 * // Drag and drop actions.clickAndHold(dragevidence)
	 * .moveToElement(dropevidence) .release() .perform();
	 * 
	 * System.out.println(" Drag and drop executed!"); } else {
	 * System.out.println(" No draggable element found, skipping drag and drop.");
	 * 
	 * driver.findElement(By.xpath("//select[@id='exampleForm.ControlSelect1']")).
	 * click();
	 * 
	 * driver.findElement(By.
	 * xpath("//select[@class ='form-control']//option[text() = 'Payment']")).click(
	 * );
	 * 
	 * driver.findElement(By.xpath("//button[text() = 'Add']")).click();
	 * 
	 * }
	 */

	/*
	 * WebElement dragevidence =
	 * driver.findElement(By.xpath("//span[@class='evi-card-amount ellips-auto']"));
	 * Thread.sleep(1000);
	 * 
	 * WebElement dropevidence =
	 * driver.findElement(By.xpath("//div[@class='evi-cards-list']"));
	 * Thread.sleep(1000);
	 * 
	 * // Scroll into view ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", dragevidence);
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", dropevidence);
	 * 
	 * Thread.sleep(1000);
	 * 
	 * // Perform drag and drop
	 * 
	 * actions.clickAndHold(dragevidence) .moveToElement(dropevidence) .release()
	 * .perform();
	 * 
	 * System.out.println("Drag and drop executed!");
	 * 
	 * 
	 * 
	 * WebElement selectvouchertype =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//div[contains(text(),'Select voucher type')]")));
	 * selectvouchertype.click();
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 * 
	 * 
	 * WebElement selectledger =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//div[@class='Select-Search css-2b097c-container']//div[@class='Select SelectLedger __value-container Select SelectLedger __value-container--has-value css-1hwfws3']"
	 * ))); selectledger.click();
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * WebElement savetransaction =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//button[normalize-space()='Save']"))); savetransaction.click();
	 * 
	 * System.out.println("Transaction saved successfully");
	 */
// -----------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * // Export list //==================== // Open Snapshot WebElement snapshot =
	 * wait.until(ExpectedConditions .elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click();
	 * 
	 * // Open menu WebElement menuBtn = wait.until(
	 * ExpectedConditions.elementToBeClickable(By.
	 * xpath("//span[@class='icon-menu-lines white-icon']"))); menuBtn.click();
	 * 
	 * // Time period selection WebElement yearDropdown =
	 * wait.until(ExpectedConditions.elementToBeClickable( By.xpath(
	 * "//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']"
	 * ))); yearDropdown.click();
	 * 
	 * WebElement sinceBeginning = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.
	 * xpath("//li[normalize-space()='Since Beginning']"))); sinceBeginning.click();
	 * 
	 * // Instead of ARROW_DOWN loop → directly select the menu option (better way)
	 * // Example: Click "Export List" option menuBtn.click(); Thread.sleep(2000);
	 * WebElement menuOption =
	 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[6]")));
	 * menuOption.click();
	 * System.out.println("Snapshot export downloaded successfully");
	 */

	// ------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * //Snapshot Bulk Action //===========================
	 * 
	 * // Open Snapshot WebElement snapshot =
	 * wait.until(ExpectedConditions.elementToBeClickable( By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click();
	 * 
	 * // Open menu
	 * driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).
	 * click(); Thread.sleep(2000);
	 * 
	 * // Keyboard selection int steps = 5; // number of times to press DOWN for
	 * (int i = 0; i < steps; i++) { actions.sendKeys(Keys.ARROW_DOWN).perform();
	 * Thread.sleep(300); // small pause between keys }
	 * actions.sendKeys(Keys.ENTER).perform(); Thread.sleep(2000);
	 * 
	 * //Time period selection
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@class='global-ddown-new']//div[@id='mui-component-select-year_type']"
	 * )).click(); Thread.sleep(2000);
	 * driver.findElement(By.xpath("//li[normalize-space()='Since Beginning']")).
	 * click(); Thread.sleep(2000);
	 * 
	 * // Click on checkbox wait.until(ExpectedConditions.elementToBeClickable( By.
	 * xpath("//table[@class='table table-hover table-bordered table-condensed']//thead//tr//th//div[@class='checkbox-personalized']"
	 * ))).click();
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Delete']")).click();
	 * 
	 * Thread.sleep(2000);
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
	 * 
	 * Thread.sleep(5000);
	 * 
	 * driver.findElement(By.xpath("//button[text() ='OK']")).click();
	 * 
	 * System.out.println("Bulk snapshot deleted successfully");
	 */

	// --------------------------------------------------------------------------------------------------------------------------------------------

	// For Excel Import
	// ===========================

	/*
	 * WebElement snapshot = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click(); Thread.sleep(2000);
	 * 
	 * driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).
	 * click();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.
	 * ENTER).build().perform();
	 * 
	 * // driver.findElement(By.xpath("//label[normalize-space()='Select //
	 * File']")).click();
	 * 
	 * driver.findElement(By.
	 * xpath("//div[@class='ie_upload_file']//label[normalize-space()='Select File']"
	 * )).click();
	 * 
	 * // Small wait for dialog to open Thread.sleep(2000);
	 * 
	 * // Use Robot to paste path from config and press Enter
	 * 
	 * robot.delay(1000);
	 * 
	 * // Copy file path into clipboard StringSelection selection = new
	 * StringSelection(config.Excelfilepath());
	 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,
	 * null);
	 * 
	 * // Press CTRL + V robot.keyPress(KeyEvent.VK_CONTROL);
	 * robot.keyPress(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_CONTROL);
	 * 
	 * // Press Enter robot.keyPress(KeyEvent.VK_ENTER);
	 * robot.keyRelease(KeyEvent.VK_ENTER);
	 * 
	 * Thread.sleep(4000);
	 * 
	 * List<WebElement> ignoreList = driver.findElements(By.xpath(
	 * "//div[@class='col-xl-12 col-lg-12 col-md-12 mt-0 mt-lg-0 mt-md-3 text-right d-flex align-items-center justify-content-end bottom-save-btn']//span[@class='icon-warning ie_icon_btn yellow-icon']"
	 * ));
	 * 
	 * if (!ignoreList.isEmpty()) { WebElement ignoremsg =
	 * wait.until(ExpectedConditions.elementToBeClickable(ignoreList.get(0)));
	 * js.executeScript("arguments[0].click();", ignoremsg);
	 * System.out.println("Ignore message clicked."); } else {
	 * System.out.println("Ignore message not found, skipping click."); }
	 * 
	 * Thread.sleep(6000);
	 * 
	 * List<WebElement> yesBtns = driver .findElements(By.xpath(
	 * "//div[contains(@class,'modal-body')]//button[normalize-space(text())='Yes']"
	 * ));
	 * 
	 * if (!yesBtns.isEmpty()) { WebElement yes =
	 * wait.until(ExpectedConditions.elementToBeClickable(yesBtns.get(0)));
	 * js.executeScript("arguments[0].click();", yes);
	 * System.out.println("Clicked on Yes button."); } else {
	 * System.out.println("Yes button not found, skipping click."); }
	 * 
	 * Thread.sleep(1000);
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
	 * 
	 * WebElement finish = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@class='bottom_save_btn']//button[text()='Finish']")));
	 * js.executeScript("arguments[0].click();", finish);
	 * 
	 * System.out.println("Excel Import successfully uploaded");
	 */

	// ------------------------------------------------------------------------------------------------------------------------------

	// For bulk upload
	// ====================
	/*
	 * // Open Snapshot WebElement snapshot =
	 * wait.until(ExpectedConditions.elementToBeClickable( By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click();
	 * 
	 * // Open menu
	 * driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).
	 * click();
	 * 
	 * // Keyboard selection
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 * Thread.sleep(2000);
	 * 
	 * // Click on upload placeholder
	 * wait.until(ExpectedConditions.elementToBeClickable(
	 * By.xpath("//div[@class='bulk-popup-table bulk-popup-pluse']//p"))).click();
	 * 
	 * // Upload file via Robot robot.delay(1000); StringSelection selection = new
	 * StringSelection(config.Bulkuploadfilepath());
	 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,
	 * null); robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
	 * robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
	 * 
	 * // Start upload wait.until(ExpectedConditions.elementToBeClickable(
	 * By.xpath("//button[normalize-space()='Start Upload']"))).click();
	 * 
	 * // Wait for upload to complete WebDriverWait longWait = new
	 * WebDriverWait(driver, Duration.ofSeconds(60));
	 * longWait.until(ExpectedConditions.elementToBeClickable(
	 * By.xpath("//button[normalize-space()='Finish']"))).click();
	 * 
	 * System.out.println("Bulk upload successfully uploaded");
	 */

	// -------------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * // Selected snapshot delete //========================== // Open Snapshot
	 * WebElement snapshot = wait.until(ExpectedConditions
	 * .elementToBeClickable(By.xpath(
	 * "//div[@class='quick-menu']//span[contains(@class,'snap-side-icon')]")));
	 * snapshot.click(); Thread.sleep(2000);
	 * 
	 * js.executeScript("window.scrollBy(0, 500)"); Thread.sleep(1500); String
	 * snapshotpartyname = config.Snapshotselection();
	 * 
	 * System.out.println(snapshotpartyname);
	 * 
	 * //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 * WebElement element = wait.until( ExpectedConditions.elementToBeClickable(
	 * By.xpath("//tbody//tr[td[4]//span[contains(text(), '" + snapshotpartyname +
	 * "')]]"))); System.out.println(element); element.click();
	 * 
	 * 
	 * js.executeScript("window.scrollBy(0, 500)"); Thread.sleep(2000); WebElement
	 * deletesnapshot = driver
	 * .findElement(By.xpath("//span[@class='icon-delete blue-icon btn-bar-delete']"
	 * )); deletesnapshot.click();
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
	 * System.out.println("Selected Snapshot deleted successfully"); }
	 */

	// --------------------------------------------------------------------------------------------------------------------------------------

	// Snapshot create
	// ==================

	// driver.findElement(By.xpath("//div[@class='quick-menu']//span[@class='icon-plus']")).click();

	/*
	 * WebElement createsnapshot = wait.until(ExpectedConditions
	 * .visibilityOfElementLocated(By.xpath(
	 * "//div[@class='quick-menu']//span[@class='icon-plus']")));
	 * createsnapshot.click();
	 * 
	 * WebElement dateinput =
	 * driver.findElement(By.xpath("//input[contains(@name,'date')]"));
	 * dateinput.sendKeys(config.getsnapshotdate());
	 * 
	 * dateinput.sendKeys(Keys.ENTER);
	 * 
	 * driver.findElement(By.xpath("//input[@id='invoice-control']")).sendKeys(
	 * config.getInvoiceNum());
	 * 
	 * driver.findElement(By.xpath("//input[@name='total_amount']")).sendKeys(config
	 * .getTotalAmount());
	 * 
	 * WebElement party = driver.findElement(By.xpath(
	 * "//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']"
	 * )); party.click(); Thread.sleep(1000);
	 * 
	 * actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	 * driver.findElement(By.xpath("//input[@name='merchant']")).sendKeys(config.
	 * getPartyName());
	 * 
	 * driver.findElement(By.xpath("//input[@name='gst_no']")).sendKeys(config.
	 * getPartyGST());
	 * 
	 * driver.findElement(By.xpath("//input[@name='pan_number']")).click();
	 * 
	 * WebElement Igst =
	 * driver.findElement(By.xpath("//input[@name='igst_amount']")); WebElement Cgst
	 * = driver.findElement(By.xpath("//input[@name='cgst_amount']")); WebElement
	 * Sgst = driver.findElement(By.xpath("//input[@name='sgst_amount']"));
	 * 
	 * if (Igst.isDisplayed() && Igst.isEnabled()) { // Fill IGST Igst.clear();
	 * Igst.sendKeys(config.getGSTAmt()); System.out.println("IGST filled"); } else
	 * if (Cgst.isDisplayed() && Cgst.isEnabled() && Sgst.isDisplayed() &&
	 * Sgst.isEnabled()) { // Fill CGST & SGST Cgst.clear();
	 * Cgst.sendKeys(config.getGSTAmt()); Sgst.clear();
	 * Sgst.sendKeys(config.getGSTAmt()); System.out.println("CGST & SGST filled");
	 * } else { // No GST fields enabled → skip
	 * System.out.println("No GST applicable, skipping GST fields"); }
	 * 
	 * driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	 * 
	 * // WebElement duplicatePanMsg = //
	 * driver.findElement(By.xpath("//p[contains(@class,'pmsg-blue-center')]")); //
	 * WebElement yesoption = //
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")); //
	 * WebElement nooption = //
	 * driver.findElement(By.xpath("//button[normalize-space()='No']"));
	 * 
	 * // Duplicate PAN confirmation message
	 * 
	 * // Wait for duplicate PAN message, if it appears within 5 sec WebElement
	 * duplicatePanMsg = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//p[contains(@class,'pmsg-blue-center')]")));
	 * 
	 * if (duplicatePanMsg.isDisplayed()) {
	 * System.out.println("Duplicate PAN message shown: " +
	 * duplicatePanMsg.getText());
	 * 
	 * // Click Yes button WebElement yesOption =
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
	 * yesOption.click(); System.out.println("Clicked on Yes"); }
	 * 
	 * Thread.sleep(2000); List<WebElement> duplicatesnapshot = driver
	 * .findElements(By.xpath("//p[normalize-space()='Save duplicate snapshot']"));
	 * 
	 * if (!duplicatesnapshot.isEmpty() && duplicatesnapshot.get(0).isDisplayed()) {
	 * System.out.println("Duplicate Snapshot message shown: " +
	 * duplicatesnapshot.get(0).getText());
	 * 
	 * WebElement yesoptions =
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
	 * yesoptions.click();
	 * 
	 * System.out.println("Clicked on Yes"); } else {
	 * System.out.println("Duplicate Snapshot message not shown"); }
	 * 
	 * Thread.sleep(5000); js.executeScript("window.scrollBy(0, 500)"); WebElement
	 * deletesnapshot = driver
	 * .findElement(By.xpath("//span[@class='icon-delete blue-icon btn-bar-delete']"
	 * )); // js.executeScript("arguments[0].scrollIntoView(true);",
	 * deletesnapshot); deletesnapshot.click();
	 * driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click(); //
	 * Delete snapshot
	 * 
	 * System.out.println("Snapshot deleted successfully");
	 */

	// -------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------

}
