package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankingPage {

	WebDriver driver;
	ReadConfigFile config;
	Robot robot;
	Actions actions;
	RandomData rd;
	WebDriverWait wait;

	public BankingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			config = new ReadConfigFile();
			robot = new Robot();
			actions = new Actions(driver);
			rd = new RandomData();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	@FindBy(xpath = "(//span[@class='icon-edit cicon editbc_bankin'])[last()]") // span[@class='icon-edit p-1']  //(//span[@class='icon-edit cicon editbc_bankin'])[last()]
	WebElement bankediticon;

	@FindBy(xpath = "//button[normalize-space()='Delete']")
	WebElement bankdeletebutton;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement delete_yes;

	public void gotobaningmodule() {

		bankingmodule.click();
		refreshbutton.click();
	}

	public void addbank() {

		addbank.click();
		bankdropdown.click();
		selectbank.sendKeys(config.getProperty("addbank"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		actions.sendKeys(Keys.ENTER).perform();
		banktype.sendKeys(config.getProperty("selectbanktype"));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions.sendKeys(Keys.ENTER).perform();
		bankaccountname.sendKeys(rd.getAccountname());

		bankaccountnumber.sendKeys(rd.getAccountnum());
		savebutton.click();
		confirmation_Yes.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletebank() {

		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		robot.keyPress(KeyEvent.VK_SHIFT);
//		robot.keyPress(KeyEvent.VK_TAB);
//
//		robot.keyRelease(KeyEvent.VK_SHIFT);
//		robot.keyRelease(KeyEvent.VK_TAB);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		actions.sendKeys(Keys.ENTER).perform();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		bankediticon.click();
		wait.until(ExpectedConditions.visibilityOf(bankdeletebutton));
		bankdeletebutton.click();
		wait.until(ExpectedConditions.visibilityOf(delete_yes));
		delete_yes.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update_Bank() {

		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		robot.keyPress(KeyEvent.VK_SHIFT);
//		robot.keyPress(KeyEvent.VK_TAB);
//
//		robot.keyRelease(KeyEvent.VK_SHIFT);
//		robot.keyRelease(KeyEvent.VK_TAB);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		actions.sendKeys(Keys.ENTER).perform();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		bankediticon.click();
		bankaccountname.sendKeys(rd.getAccountname());
		savebutton.click();
		confirmation_Yes.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getToastmessage() {
		WebElement toastmsg = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")));

		String actualmsg = toastmsg.getText().trim();
		return actualmsg;

	}

}
