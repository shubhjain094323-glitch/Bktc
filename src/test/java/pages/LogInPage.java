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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadConfigFile;

public class LogInPage {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	Logger logger;

	// Constructor
	public LogInPage(WebDriver driver) throws FileNotFoundException, AWTException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		logger = LogManager.getLogger(this.getClass());

	}

	@FindBy(name = "emailId")
	WebElement accountcode_emailid;

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(tagName = "button")
	WebElement Loginbutton;

	@FindBy(xpath = "//li[@class='main-h-profile']//div[@id='dropdown-basic']")
	WebElement myprofile;

	@FindBy(css = ".p-0.h-ul-entities-list.white-bg > li div.drp-entity-name")
	List<WebElement> listofEntities;

	// Method for login

	public void openURL() throws InterruptedException {
		driver.get(config.getURL());
		logger.info("Opening the URL");
		Thread.sleep(2000);

	}

	public void performLogin() throws InterruptedException {

		logger.info("Entering Credentials for the Login");
		Thread.sleep(1500);
		accountcode_emailid.sendKeys(config.getaccountcode());
		Thread.sleep(1000);
		username.sendKeys(config.getusername());
		Thread.sleep(1000);
		password.sendKeys(config.getpassword());
		Thread.sleep(1000);
		Loginbutton.click();

		logger.info("Logged In Successfully");
	}

	public void entityselection() throws InterruptedException {

		Thread.sleep(2500);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loader')]")));

		wait.until(ExpectedConditions.elementToBeClickable(myprofile)).click();

		for (WebElement entity : listofEntities) {
			String entityText = entity.getText().trim();
			// System.out.println(entityText);
			if (entityText.equalsIgnoreCase(config.getentityname().trim())) {
				entity.click();
				break;
			}
		}

		logger.info("Entity Selected");

		Thread.sleep(2500);
	}

	public void clear_data() throws InterruptedException {

		accountcode_emailid.clear();
		Thread.sleep(1000);
		username.clear();
		Thread.sleep(1000);
		password.clear();
		Thread.sleep(1000);

	}

	public String successful_login() {

		return driver.getCurrentUrl();

	}

	// Close browser
	public void closeBrowser() {
		driver.quit();
	}

}
