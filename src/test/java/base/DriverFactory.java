package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ReadConfigFile;

public class DriverFactory {

	public static ReadConfigFile config;
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static ThreadLocal<String> browserName = new ThreadLocal<String>();

	public static void initDriver(String br, String os, boolean headless) throws MalformedURLException, Exception {

		config = new ReadConfigFile();

		browserName.set(br);

		if (config.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No default platform");
				return;
			}

			switch (br.toLowerCase()) {

			case "chrome":
				cap.setBrowserName("chrome");
				break;

			case "firefox":
				cap.setBrowserName("Firefox");
				break;

			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;

			default:
				System.out.println("No matching browser");
				return;

			}

			driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), cap));

		}

		if (config.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {

			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();

				if (headless) {
					chromeOptions.addArguments("--headless=new");
					chromeOptions.addArguments("--window-size=1920,1080");
					chromeOptions.addArguments("--disable-gpu");
					chromeOptions.addArguments("--no-sandbox");
					chromeOptions.addArguments("--disable-dev-shm-usage");
				}

				driver.set(new ChromeDriver(chromeOptions));

				break;

			// driver.set(new ChromeDriver());
			// break;

			case "edge":

				EdgeOptions edgeOptions = new EdgeOptions();

				if (headless) {
					edgeOptions.addArguments("--headless=new");
					edgeOptions.addArguments("--window-size=1920,1080");
					edgeOptions.addArguments("--disable-gpu");
					edgeOptions.addArguments("--no-sandbox");
					edgeOptions.addArguments("--disable-dev-shm-usage");
				}

				driver.set(new EdgeDriver(edgeOptions));
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();

				if (headless) {
					firefoxOptions.addArguments("--headless=new");
					firefoxOptions.addArguments("--window-size=1920,1080");
					firefoxOptions.addArguments("--disable-gpu");
					firefoxOptions.addArguments("--no-sandbox");
					firefoxOptions.addArguments("--disable-dev-shm-usage");
				}

				driver.set(new FirefoxDriver(firefoxOptions));
				break;

			default:
				throw new RuntimeException("Invalid Browser : " + br);
			}

		}

		if (!headless) {
			getDriver().manage().window().maximize();
		}

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	public static String getBrowserLoopWise() {

		// This works same on Local and Jenkins - no parameter needed
		LocalDate today = LocalDate.now();
		int dayOfYear = LocalDate.now().getDayOfYear(); // 1 to 366
		int day = dayOfYear % 3;

		String browser;
		if (day == 0)
			browser = "chrome";
		else if (day == 1)
			browser = "edge";
		else
			browser = "firefox";
		
		System.out.println("Today's Date: " + today);
		System.out.println("DayOfYear: " + dayOfYear);
		System.out.println("AUTO SELECTED BROWSER FOR TODAY: " + browser.toUpperCase());
		System.out.println("==================================");
		return browser;
	}

	public static WebDriver getDriver() {

		return driver.get();

	}

	public static String getBrowser() {
		return browserName.get();
	}

	public static void quitDriver() {

		if (driver.get() != null) {

			driver.get().quit();

			driver.remove();

		}
		browserName.remove();

	}
}
