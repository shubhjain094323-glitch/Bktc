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
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();

	}

	// Method for login

	public void performLogin() throws InterruptedException {
		driver.get(config.getURL());
		Thread.sleep(2000);

		driver.findElement(By.name("emailId")).sendKeys(config.getaccountcode());
		driver.findElement(By.name("username")).sendKeys(config.getusername());
		driver.findElement(By.name("password")).sendKeys(config.getpassword());
		driver.findElement(By.tagName("button")).click();
		System.out.println("Login Successful");
	}

	public void entityselection() throws InterruptedException {

		// For entity selection
		Thread.sleep(2500);

		WebElement profile = driver.findElement(By.xpath("//li[@class='main-h-profile']//div[@id='dropdown-basic']"));
		wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
		List<WebElement> entities = driver
				.findElements(By.cssSelector(".p-0.h-ul-entities-list.white-bg > li div.drp-entity-name"));

		for (WebElement entity : entities) {
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
