package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Moneylink {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;

	// Constructor
	public Moneylink(WebDriver driver) throws FileNotFoundException, AWTException {
		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
		// driver.manage().window().maximize();
		this.driver = driver;

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();

	}

	public void openMoneylinkPage() throws InterruptedException {
		WebElement moneylink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='icon-Link-icon']")));
		moneylink.click();
		// actions.moveByOffset(200, 400).click().perform();
		Thread.sleep(1000);
		System.out.println("Opened Moneylink page");
	}

	// Common method to open Moneylink, select bank, and choose "Since Beginning"
	public void setupMoneylink() throws InterruptedException {

		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='icon-menu-lines white-icon']")).click();

		// Bank selection
		WebElement bankselection = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='mui-component-select-financial_institute']")));
		Thread.sleep(1500);
		bankselection.click();

		String bankName = config.getSelectbank();
		WebElement option = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[contains(normalize-space(),'" + bankName + "')]")));
		Thread.sleep(1500);
		option.click();

		Thread.sleep(2000);

		// Time period selection
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary global_new_dd_date css-fvipm8']//div[@id='mui-component-select-year_type']")));
		yearDropdown.click();

		WebElement sinceBeginning = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Since Beginning']")));
		sinceBeginning.click();

		Thread.sleep(1500);

	}

	public void moneylinksingletransaction() throws InterruptedException {

		Thread.sleep(4000);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);

		// select single Entry option from the contex menu
		driver.findElement(By.xpath("//div[@class='dropdown-menu show']//button[1]")).click();

		driver.findElement(By.xpath("//p[normalize-space()='Total']")).click();

		js.executeScript("window.scrollBy(0, 200)");

		WebElement transaction = driver.findElement(By.xpath("//tbody/tr[1]/td[4]"));
		js.executeScript("arguments[0].click();", transaction);
		Thread.sleep(2000);

		WebElement searchevidence = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Search']")));
		searchevidence.click();
		Thread.sleep(2000);

		WebElement filterapply = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']")));
		filterapply.click();
		Thread.sleep(3000);

		WebElement dragevidence = driver.findElement(By.xpath("//span[@class='evi-card-amount ellips-auto']"));
		Thread.sleep(1000);

		WebElement dropevidence = driver.findElement(By.xpath("//div[@class='evi-cards-list']"));
		Thread.sleep(1000);

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dragevidence);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropevidence);

		Thread.sleep(1000);

		// Perform drag and drop

		actions.clickAndHold(dragevidence).moveToElement(dropevidence).release().perform();

		// System.out.println("Drag and drop executed!");

		WebElement selectvouchertype = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Select voucher type')]")));
		selectvouchertype.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		WebElement selectledger = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='Select-Search css-2b097c-container']//div[@class='Select SelectLedger __value-container Select SelectLedger __value-container--has-value css-1hwfws3']")));
		selectledger.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		WebElement savetransaction = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
		savetransaction.click();
		Thread.sleep(1500);

		// Use a very short explicit wait just to check presence
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

		try {
			// Try locating the popup within 2 seconds
			WebElement billwiseamount = shortWait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Okay']")));

			if (billwiseamount.isDisplayed()) {
				billwiseamount.click();
				System.out.println("Billwise amount popup found, proceeding...");

				Thread.sleep(2000);

				js.executeScript("window.scrollBy(0, -500)");

				WebElement editallocation = wait.until(ExpectedConditions
						.elementToBeClickable((By.xpath("//span[normalize-space()='Edit Allocation']"))));
				Thread.sleep(1500);
				editallocation.click();

				// actions.moveToElement(editallocation).perform();

				WebElement addnewrow = driver.findElement(By.xpath("//button[normalize-space()='Add New Row']"));
				addnewrow.click();

				WebElement selectref = driver.findElement(By.xpath("(//select[@name='ref_select'])[2]"));
				Select select = new Select(selectref);
				select.selectByVisibleText("On Account");

				driver.findElement(By.xpath(
						"//div[contains(@class,'reset_apply_btn_new mt-2')]//button[contains(@type,'button')][normalize-space()='Save']"))
						.click();

				savetransaction.click();
				Thread.sleep(3000);

				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

			}

		} catch (TimeoutException e) {
			// If popup does not appear within 2 sec → skip without error
			System.out.println("Billwise amount popup not found, skipping allocation steps.");
		}

		System.out.println("Transaction saved successfully");

	}

	public void crbulkentries() throws InterruptedException {

		Thread.sleep(2500);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);

		// CR Bulk entries selection
		driver.findElement(By.xpath("//span[@class='shortcut_keys_action' and text()='C']")).click();
		actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='check']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();
		Thread.sleep(1500);

		driver.findElement(By.cssSelector(".ledger-right-bar")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		driver.findElement(By.xpath(
				"//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']"))
				.click();

		// driver.findElement(By.xpath("//span[@class='path10']")).click();

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

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		Thread.sleep(3000);

		System.out.println("Bulk CR entries done");

	}

	public void drbulkentries() throws InterruptedException {

		Thread.sleep(4000);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//span[@class='shortcut_keys_action' and text()='D']")).click();
		// actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='check']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();
		Thread.sleep(1500);

		driver.findElement(By.cssSelector(".ledger-right-bar")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		driver.findElement(By.xpath(
				"//div[@class='Select type-select __value-container Select type-select __value-container--has-value css-1hwfws3']"))
				.click();

		// driver.findElement(By.xpath("//span[@class='path10']")).click();

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

		System.out.println("DR Bulk entries done");
	}

	public void moneylinkquickentry() throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[@class='shortcut_keys_action' and text()='Q']")).click();
		Thread.sleep(1000);
		// Select first moneylink for the quick entry
		for (int i = 0; i < 1; i++) {
			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[contains(@for,'checkbox" + i + "')]//div[contains(@class,'check')]")));
			checkbox.click();
			System.out.println("Clicked checkbox" + i);
		}
		// click on add entries button

		driver.findElement(By.xpath("//a[normalize-space()='Add Entries']")).click();
		Thread.sleep(2500);

		By partyledgerdropdwon = By.xpath("//div[@class='Select type-select __value-container css-1nol46l']");

		WebElement partyledger = wait.until(ExpectedConditions.elementToBeClickable(partyledgerdropdwon));
		partyledger.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		driver.findElement(By.xpath("//button[normalize-space()='Create Entries']")).click();
		Thread.sleep(3000);

		System.out.println("Moneylink Quick Entry Successfully Saved");

	}

	public void transactionreset() throws InterruptedException {

		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//span[@class='shortcut_keys_action' and text()='T']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//label[contains(@for,'checkboxHeader')]//div[@class='check']")).click();
		Thread.sleep(1000);

		List<WebElement> selectAcross = driver
				.findElements(By.xpath("//button[normalize-space()='Select across all pages']"));

		if (!selectAcross.isEmpty()) {
			selectAcross.get(0).click();
		}

		WebElement resetbutton = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Reset Transactions']")));

		resetbutton.click();

		WebElement yesbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
		yesbutton.click();

		Thread.sleep(15000);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

		Thread.sleep(2000);
		WebElement okbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Okay']")));
		Thread.sleep(2000);
		okbutton.click();

		System.out.println("Transaction reseted successfully");

	}

	public void exportlist() throws InterruptedException {
		Thread.sleep(4000);

		driver.findElement(By.xpath("//div[@class='action-new-menu dropdown']//button[@id='dropdown-basic']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//div[@class='row mainFilter-new sec-mb']//button[7]")).click();
		Thread.sleep(1500);

		System.out.println("Export successfully done");

	}

	public String getToastMessage() {

		try {

			WebElement toast = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")));

			return toast.getText().trim();

		} catch (Exception e) {

			System.out.println("Toast message not found");
			return "";
		}
	}
}
