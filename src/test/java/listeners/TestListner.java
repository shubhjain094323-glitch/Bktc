package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.BaseTest;
import utils.Screenshots;

public class TestListner extends BaseTest implements ITestListener {

	ExtentSparkReporter htmlreporter;
	ExtentReports reports;
	ExtentTest test;

	public void configureReport() {
		htmlreporter = new ExtentSparkReporter("Production Sanity Reports.html");
		htmlreporter.config().setOfflineMode(true);
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);

		// Adding the system information / env information
		reports.setSystemInfo("Performed By", "Shubham");
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("env", "Production");

		// Configuration for the reports look & feel
		htmlreporter.config().setDocumentTitle("Production Sanity Reports");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setTimeStampFormat("dd-MM-yy_hh:mm:ss");

	}

	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("On Start method invoked");
	}

	// When start the test cases execution
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of the Passed Test is " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the pass test case is " + Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the Failed Test is " + Result.getName());
		
		System.out.println("Driver in Listener: " + driver);

		String testname = Result.getName();

		Screenshots.captureScreenshot(driver, testname);

		System.out.println("Screenshot captured for failed test: " + testname);

		test = reports.createTest(Result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case is " + Result.getName(), ExtentColor.RED));
	}

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of the Skipped Test is " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the skipped test case is " + Result.getName(), ExtentColor.GREY));
	}

	public void onFinish(ITestContext context) {
		System.out.println("On Finish method invoked");
		reports.flush();
	}

}
