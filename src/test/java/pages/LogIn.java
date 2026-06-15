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

public class LogIn {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;

	// Constructor
	public LogIn(WebDriver driver) throws FileNotFoundException, AWTException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();

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

	public void openURL() {
		driver.get(config.getURL());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void performLogin() {

		accountcode_emailid.sendKeys(config.getaccountcode());
		username.sendKeys(config.getusername());
		password.sendKeys(config.getpassword());
		Loginbutton.click();
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

		Thread.sleep(2000);
	}

	// Close browser
	public void closeBrowser() {
		driver.quit();
	}

}
