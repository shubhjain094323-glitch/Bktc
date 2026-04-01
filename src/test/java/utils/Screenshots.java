package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	public static String captureScreenshot(WebDriver driver, String testName) {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testName + "_" + timeStamp + ".png";

		TakesScreenshot screenshot = ((TakesScreenshot) driver);

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		String destinationPath = "C:\\Users\\DELL\\eclipse-workspace\\TestNG\\TestNG\\Screenshots\\" +screenshotName;

		File dest = new File(destinationPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationPath;
	}
	
}
