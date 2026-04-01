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

public class Dashboard {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;

	// Constructor
	public Dashboard(WebDriver driver) throws FileNotFoundException, AWTException {
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
	
	public void dashboard() throws InterruptedException {
		
		
		  Thread.sleep(2000);

		 // Navigate to dashboard
        driver.findElement(By.xpath("//div[@class='quick-menu']//a[@href='/dashboard']")).click();

        // Give time to load page
        Thread.sleep(2000);

        // Move to element
        actions.moveToElement(driver.findElement(By.xpath("//div[@id='mui-component-select-year_type']")), 200, 0).perform();

        Thread.sleep(2000);

        // Define periods
        String[] periods = {
            "Current Financial Year",
            "Last Financial Year",
            "This Month",
            "Last 3 Months",
            "Since Beginning"
        };

        // For Buktec Summary
        selectPeriods(driver, wait, periods, By.id("mui-component-select-year_type"));

        // For Business Summary
        Thread.sleep(2000);
        WebElement businessummary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@id='2']")));
        businessummary.click();

        selectPeriods(driver, wait, periods, By.id("mui-component-select-year_type"));

    }

    // select multiple periods from dropdown
    public static void selectPeriods(WebDriver driver, WebDriverWait wait, String[] periods, By dropdownLocator) throws InterruptedException {
        WebElement timeperiod = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));

        for (String period : periods) {
            timeperiod.click(); // open dropdown

            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//li[normalize-space()='" + period + "']"))
            );
            element.click();  // select option

            System.out.println("Clicked on: " + period);

            // Re-fetch dropdown except for the last period
            if (!period.equalsIgnoreCase("Since Beginning")) {
                timeperiod = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            }

            Thread.sleep(500); // small pause to let UI update
        }
    }

		
		
	}

