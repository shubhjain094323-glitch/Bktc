package pages;

import java.awt.Robot;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import utils.RandomData;
import utils.ReadConfigFile;

public class BankingPage {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;

	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData rd;
	Logger logger;

	public BankingPage(WebDriver driver) throws Exception {

		System.out.println("========== BankingPage Constructor ==========");
		System.out.println("Driver received: " + driver);

		if (driver == null) {
			throw new IllegalArgumentException("BankingPage received NULL driver");
		}

		this.driver = driver;
		PageFactory.initElements(driver, this);

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		rd = new RandomData();
		logger = LogManager.getLogger(this.getClass());

		System.out.println("BankingPage initialized successfully");

	}

	@FindBy(xpath = "//a[@href='/financial-institutes']")
	WebElement bankingmodule;

	@FindBy(xpath = "//button[@class='btn ico-refresh-sec blink-green-btn']")
	WebElement refreshbutton;

	@FindBy(xpath = "//div[@class='bank-card add-bank-card addbank_bankin']")
	WebElement addbank;

	@FindBy(xpath = "//div[contains(@class,'type-select') and contains(@class,'__control')]")
	WebElement bankdropdown;

	@FindBy(xpath = "//div[contains(@class,'type-select')]//input[@id='react-select-2-input']")
	WebElement selectbank;

	@FindBy(xpath = "//div[contains(@class,'type-select')]//input[@id='react-select-3-input']")
	WebElement banktype;

	@FindBy(xpath = "//input[@name='account_name']")
	WebElement bankaccountname;

	@FindBy(xpath = "//input[@name='account_number']")
	WebElement bankaccountnumber;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement savebutton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement confirmation_Yes;

	@FindBy(xpath = "(//span[@class='icon-edit cicon editbc_bankin'])[last()]") // span[@class='icon-edit p-1']
																				// //(//span[@class='icon-edit cicon
																				// editbc_bankin'])[last()]
	WebElement bankediticon;

	@FindBy(xpath = "//button[normalize-space()='Delete']")
	WebElement bankdeletebutton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement delete_yes;

	public void gotobaningmodule() {

		try {
			bankingmodule.click();
		} catch (Exception e) {
			logger.error("Unable to click the banking module", e);
			logger.debug("Debug log........");
		}
		refreshbutton.click();
	}

	public void addbank() throws InterruptedException {

		logger.info("Clicking for Add Bank");
		addbank.click();
		logger.info("Entering the Financial Institute Details");
		bankdropdown.click();
		Thread.sleep(1000);
		selectbank.sendKeys(config.getProperty("addbank"));
		Thread.sleep(1000);

		actions.sendKeys(Keys.ENTER).perform();
		banktype.sendKeys(config.getProperty("selectbanktype"));

		Thread.sleep(1000);

		actions.sendKeys(Keys.ENTER).perform();
		bankaccountname.sendKeys(rd.getAccountname());

		bankaccountnumber.sendKeys(rd.getAccountnum());

		logger.info("Clicking on the Save Button for adding bank");
		savebutton.click();
		confirmation_Yes.click();

		logger.info("Finanancial Insitute Added");
		Thread.sleep(1000);

	}

	public void deletebank() throws InterruptedException {

		Thread.sleep(3500);

		bankediticon.click();
		wait.until(ExpectedConditions.visibilityOf(bankdeletebutton));
		bankdeletebutton.click();
		wait.until(ExpectedConditions.visibilityOf(delete_yes));
		delete_yes.click();

		logger.info("Financial Institute Deleted");
		Thread.sleep(2500);

	}

	public void update_Bank() throws InterruptedException {

		Thread.sleep(3000);

		wait.until(ExpectedConditions.elementToBeClickable(bankediticon)).click();

		Thread.sleep(1500);
		bankaccountname.sendKeys(rd.getAccountname());
		savebutton.click();
		confirmation_Yes.click();

		logger.info("Financial Institute Updated");
		Thread.sleep(2000);
	}

	public String getToastmessage() {
		WebElement toastmsg = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")));

		String actualmsg = toastmsg.getText().trim();
		return actualmsg;

	}

}
