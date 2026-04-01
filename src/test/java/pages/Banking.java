package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Banking {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData rd;

	// Constructor
	public Banking(WebDriver driver) throws FileNotFoundException, AWTException {
		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
		// driver.manage().window().maximize();
		this.driver = driver;

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		rd = new RandomData();

	}

	public void openbankingmodule() throws InterruptedException {
		WebElement banking = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/financial-institutes']")));
		banking.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn ico-refresh-sec blink-green-btn']")).click();
		System.out.println("Opened Banking Module");

	}

	public void addbank() throws InterruptedException {

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
		Thread.sleep(1000);
		actions.sendKeys(Keys.ENTER).perform();

		WebElement banktype = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'type-select')]//input[@id='react-select-3-input']")));

		banktype.sendKeys(config.getselectbanktype());
		Thread.sleep(1000);
		actions.sendKeys(Keys.ENTER).perform();

		WebElement bankaccountname = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='account_name']")));

		bankaccountname.sendKeys(rd.getAccountname());
		Thread.sleep(1000);

		WebElement bankaccountnumber = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='account_number']")));

		bankaccountnumber.sendKeys(rd.getAccountnum());
		Thread.sleep(1000);

		/*
		 * WebElement bankledger =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(
		 * "//div[contains(@class,'type-select')]//input[@id='react-select-13-input']"))
		 * ); bankledger.click();
		 * 
		 * bankledger.sendKeys(config.getbankledger()); Thread.sleep(1500);
		 * actions.sendKeys(Keys.ENTER).perform();
		 */

		actions.sendKeys(Keys.TAB).perform();

		WebElement savebank = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
		// savebank.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", savebank);

		actions.sendKeys(Keys.ENTER).perform();

		// driver.findElement(By.xpath("//button[normalize-space()='Yes']"));

		Thread.sleep(1500);
		System.out.println("Bank Added successfully");

	}

	public void deletebank() throws InterruptedException {

		Thread.sleep(4000);

		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);

		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='edit-icon-account cpointer']")).click();

		Thread.sleep(2500);

		driver.findElement(By.xpath("//button[normalize-space()='Delete']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		Thread.sleep(3000);

		System.out.println("Financial Institute Deleted");
	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")))
				.getText();
	}

}
