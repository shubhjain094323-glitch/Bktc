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

public class Updatedetails {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData rd;

	public Updatedetails(WebDriver driver) throws FileNotFoundException, AWTException {

		this.driver = driver;

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		rd = new RandomData();
	}

	public void updateaccountdetails() throws InterruptedException {

		WebElement hamburger = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
		hamburger.click();

		driver.findElement(By.xpath("//a[@href='/profile-management/entities']//span[@class='menu-name']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//span[@class='icon-edit']")).click();

		WebElement accountname = driver.findElement(By.xpath("//input[@name='full_name']"));
		accountname.clear();
		accountname.sendKeys(rd.getAccountname());
		Thread.sleep(1500);

		WebElement mobnumber = driver.findElement(By.xpath("//input[@name='phone_number']"));
		mobnumber.clear();
		Thread.sleep(1000);
		mobnumber.sendKeys(rd.getphonenum());
		Thread.sleep(1500);

		WebElement gstnumber = driver.findElement(By.xpath("//input[@name='gst_no']"));
		gstnumber.clear();
		gstnumber.sendKeys(rd.getpartygstin());
		Thread.sleep(1500);

		WebElement updateaccount = driver.findElement(By.xpath("//button[normalize-space()='Update']"));
		updateaccount.click();
		Thread.sleep(5000);

	}

	public void entityupdate() throws InterruptedException {

		// Entity Update

		WebElement hamburger = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
		hamburger.click();

		driver.findElement(By.xpath("//a[@href='/profile-management/entities']//span[@class='menu-name']")).click();
		Thread.sleep(1500);

		String entityselect = config.getEntityselection();
		// String entityselect = rd.getpartyname();
		System.out.println("Selected entity for update = " + entityselect);
		Thread.sleep(2000);

		js.executeScript("window.scrollBy(0, 800)");

		Thread.sleep(1500);
		WebElement clickonentity = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table[@class='table table-bordered']//div[text() ='" + entityselect + "']")));
		clickonentity.click();
		Thread.sleep(1500);

		WebElement entityname = driver.findElement(By.xpath("//input[@name='entity_name']"));
		entityname.clear();
		entityname.sendKeys(config.getEntitynameforupdate());
		System.out.println("Updated entity name is -" + entityname);
		Thread.sleep(1500);

		WebElement entityType = driver
				.findElement(By.xpath("//select[@name='company_type_id']//option[text() = 'Individual']"));
		entityType.click();
		Thread.sleep(1500);

		WebElement updateEntity = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Update']")));
		updateEntity.click();

		System.out.println("Entity updated successfully");

	}

	public void userupdate() throws InterruptedException {

		WebElement hamburger = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[@class='hamburger-menu']//div[@id='dropdown-basic']")));
		hamburger.click();

		driver.findElement(By.xpath("//a[@href='/profile-management/entities']//span[@class='menu-name']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//a[@id='uncontrolled-tab-example-tab-/profile-management/user']")).click();

		js.executeScript("window.scrollBy(0, 500)");

		String userselect = config.getUserselection();
		System.out.println("Selected user is =" + userselect);

		WebElement clickonuser = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table[@class='table table-bordered']//div[text() = '" + userselect + "']")));
		System.out.println(clickonuser);
		clickonuser.click();
		Thread.sleep(1500);

		WebElement updateuser = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
		updateuser.click();

		System.out.println("User updated successfully");
	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__toast-body')]")))
				.getText();
	}

}
