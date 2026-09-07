package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import base.BaseTestUpdated;
import base.DriverFactory;
import utils.Screenshots;

public class TestListner extends BaseTestUpdated implements ITestListener {

	ExtentSparkReporter sparkreporter;
	ExtentReports extent;
	ExtentTest test;

	String repName;

	public void onStart(ITestContext textcontext) {

		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

		repName = "Test-Report" + timestamp + ".html";

		sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify location of reports
		sparkreporter.config().setDocumentTitle("Buktec Automation Report");
		sparkreporter.config().setReportName("Buktec Sanity Testing");
		sparkreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Buktec");
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "Production");

		String os = textcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = textcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> groups = textcontext.getCurrentXmlTest().getIncludedGroups();
		if (!groups.isEmpty()) {
			extent.setSystemInfo("Groups", groups.toString());
		}

		System.out.println("On Start method invoked");
	}

	// When start the test cases execution
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of the Passed Test is " + Result.getName());
		test = extent.createTest(Result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the pass test case is " + Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the Failed Test is " + Result.getName());

		driver = DriverFactory.getDriver();

		System.out.println("Driver in Listener: " + driver);

		// Get failure reason
		Throwable throwable = Result.getThrowable();

		if (throwable != null) {

			System.out.println("========== FAILURE REASON ==========");
			System.out.println("Exception Type: " + throwable.getClass().getSimpleName());

			System.out.println("Error Message: " + throwable.getMessage());

			System.out.println("====================================");
		}

		String testname = Result.getName();

		Screenshots.captureScreenshot(driver, testname);

		System.out.println("Screenshot captured for failed test: " + testname);

		test = extent.createTest(Result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case is " + Result.getName(), ExtentColor.RED));

		// Add failure reason to Extent Report
		if (throwable != null) {

			test.fail("<b>Failure Reason:</b> " + throwable.getClass().getSimpleName() + "<br><b>Message:</b> "
					+ throwable.getMessage());

			// Add complete stack trace
			test.fail(throwable);
		}

		try {
			String imgpath = new Screenshots().captureScreenshot(driver, testname);
			test.addScreenCaptureFromPath(imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of the Skipped Test is " + Result.getName());
		test = extent.createTest(Result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the skipped test case is " + Result.getName(), ExtentColor.GREY));
	}

	public void onFinish(ITestContext context) {
		System.out.println("On Finish method invoked");
		extent.flush();
	}

}
