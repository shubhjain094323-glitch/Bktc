package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
		// driver.manage().window().maximize();
		this.driver = driver;

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
	}

	public void reports() throws InterruptedException {

		Thread.sleep(2000);
		WebElement reportsLink = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='quick-menu']//a[@href='/reports']")));
		reportsLink.click();

		// Click somewhere offset (maybe to close popup or focus)
		actions.moveByOffset(200, 400).click().perform();

		String exportdata = config.getExportsfields();
		System.out.println("Export data is = " + exportdata);
		Thread.sleep(1500);

		// Click export option
		driver.findElement(By.xpath("//a[@title='" + exportdata + "']")).click();

		// 👉 Run this part ONLY if exportdata is Bank statement
		if (exportdata.equalsIgnoreCase("Bank statements")) {

			driver.findElement(By.xpath("//div[@id='mui-component-select-financial_institute_id']")).click();

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'MuiList-root')]")));

			String bankName = config.getSelectbank();
			System.out.println("Bank selected: " + bankName);
			WebElement bankOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//li[contains(normalize-space(),'" + bankName + "')]")));
			System.out.println("Selected bank - " + bankOption);
			bankOption.click();

		}

		// Open time period dropdown
		driver.findElement(By.xpath("//div[@id='mui-component-select-year_type']")).click();

		String timeperiod = config.getTimeperiod();
		System.out.println("Selected time period is = " + timeperiod);

		// Select period
		driver.findElement(By.xpath("//li[normalize-space()='" + timeperiod + "']")).click();
		Thread.sleep(3000);

		// Click Export button
		driver.findElement(By.xpath("//button[normalize-space()='Export']")).click();
		Thread.sleep(3000);
	}

}
