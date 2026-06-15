package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
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

public class Reports {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;

	// Constructor
	public Reports(WebDriver driver) throws FileNotFoundException, AWTException {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
	}

	@FindBy(xpath = "//div[@class='quick-menu']//a[@href='/reports']")
	WebElement gotoreportmodule;

	@FindBy(xpath = "//div[@id='mui-component-select-financial_institute_id']")
	WebElement selectBank;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-fhdkmq']/li")
	List<WebElement> listof_bank;

	@FindBy(xpath = "//div[@id='mui-component-select-year_type']")
	WebElement time_period;

	@FindBy(xpath = "//button[normalize-space()='Export']")
	WebElement clickon_Export;

	public void reports() throws InterruptedException {

//		WebElement reportsLink = wait.until(
//				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='quick-menu']//a[@href='/reports']")));
		gotoreportmodule.click();

		Thread.sleep(3000);

		actions.moveByOffset(200, 400).click().perform();

		String exportdata = config.getExportsfields();
		System.out.println("Export data is = " + exportdata);
		Thread.sleep(1500);

		// Click export option
		driver.findElement(By.xpath("//a[@title='" + exportdata + "']")).click();

		// 👉 Run this part ONLY if exportdata is Bank statement
		if (exportdata.equalsIgnoreCase("Bank statements")) {

			selectBank.click();

//			wait.until(
//					ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'MuiList-root')]")));
//
			String bankName = config.getselectbankforexport();
			System.out.println("Bank selected: " + bankName);
//			WebElement bankOption = wait.until(ExpectedConditions
//					.elementToBeClickable(By.xpath("//li[contains(normalize-space(),'" + bankName + "')]")));
//			System.out.println("Selected bank - " + bankOption);
//			bankOption.click();

			for (WebElement banks : listof_bank) {

				String banklist = banks.getText().trim();
				System.out.println("List of banks " + banklist);

				if (banklist.equals(bankName)) {

					banks.click();
				}
			}

		}

		// Open time period dropdown
		time_period.click();

		String timeperiod = config.getTimeperiod();
		System.out.println("Selected time period is = " + timeperiod);

		// Select period
		driver.findElement(By.xpath("//li[normalize-space()='" + timeperiod + "']")).click();
		Thread.sleep(3000);

		// Click Export button
		clickon_Export.click();
		Thread.sleep(3000);
	}

}
