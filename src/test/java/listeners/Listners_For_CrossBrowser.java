package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;


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

import base.DriverFactory;
import utils.Screenshots;


public class Listners_For_CrossBrowser implements ITestListener {
	
	private static ExtentSparkReporter sparkreporter;
    private static ExtentReports extent;

    // ThreadLocal is important for parallel cross-browser execution
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private String repName;
    
 // INITIALIZE REPORT
    // =========================================================

    private static synchronized void initializeReport() {

        // Prevent duplicate initialization
        if (extent != null) {
            return;
        }

        String timestamp = new SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss"
        ).format(new Date());

        String reportName =
                "Test-Report-" + timestamp + ".html";


        // Create report
        sparkreporter = new ExtentSparkReporter(
                ".\\reports\\" + reportName
        );


        // Report configuration
        sparkreporter.config().setDocumentTitle(
                "Buktec Automation Report"
        );

        sparkreporter.config().setReportName(
                "Buktec Sanity Testing"
        );

        sparkreporter.config().setTheme(
                Theme.DARK
        );


        extent = new ExtentReports();

        extent.attachReporter(sparkreporter);


        // =====================================================
        // SYSTEM INFORMATION
        // THESE WILL BE DISPLAYED ONLY ONCE
        // =====================================================

        extent.setSystemInfo(
                "Application",
                "Buktec"
        );

        extent.setSystemInfo(
                "User Name",
                System.getProperty("user.name")
        );

        extent.setSystemInfo(
                "Environment",
                "Production"
        );

        extent.setSystemInfo(
                "Operating System",
                System.getProperty("os.name")
        );
    }


    // =========================================================
    // SUITE START
    // =========================================================

    @Override
    public void onStart(ITestContext testContext) {

    	initializeReport();
    }

    // =========================================================
    // TEST START
    // =========================================================

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        /*
         * Get the browser from DriverFactory.
         *
         * This is better than directly reading browser
         * from testng.xml because DriverFactory is the
         * component actually creating the browser.
         */

        String browser = DriverFactory.getBrowser();

        if (browser == null || browser.trim().isEmpty()) {

            browser = result
                    .getTestContext()
                    .getCurrentXmlTest()
                    .getParameter("browser");
        }

        if (browser == null || browser.trim().isEmpty()) {
            browser = "Unknown";
        }

        // Create Extent test
        ExtentTest extentTest =
                extent.createTest(testName);

        // Store ExtentTest for current thread
        test.set(extentTest);

        // Add browser as category
        test.get().assignCategory(
                browser.toUpperCase()
        );

        // Add browser information
        test.get().info(
                "<b>Browser:</b> "
                        + browser.toUpperCase()
        );

        // Add OS information
        String os = result
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter("os");

        if (os != null) {

            test.get().info(
                    "<b>Operating System:</b> "
                            + os.toUpperCase()
            );
        }

        System.out.println(
                "Test Started: "
                        + testName
                        + " | Browser: "
                        + browser
        );
    }

    // =========================================================
    // TEST SUCCESS
    // =========================================================

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName =
                result.getName();

        System.out.println(
                "Name of the Passed Test is "
                        + testName
        );

        test.get().log(
                Status.PASS,
                MarkupHelper.createLabel(
                        "Test Passed: " + testName,
                        ExtentColor.GREEN
                )
        );
    }

    // =========================================================
    // TEST FAILURE
    // =========================================================

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =
                result.getName();

        System.out.println(
                "Name of the Failed Test is "
                        + testName
        );

        // Get current driver
        org.openqa.selenium.WebDriver driver =
                DriverFactory.getDriver();

        System.out.println(
                "Driver in Listener: "
                        + driver
        );

        // Get browser
        String browser =
                DriverFactory.getBrowser();

        System.out.println(
                "Browser in Listener: "
                        + browser
        );

        // -----------------------------------------------------
        // Failure reason
        // -----------------------------------------------------

        Throwable throwable =
                result.getThrowable();

        if (throwable != null) {

            System.out.println(
                    "========== FAILURE REASON =========="
            );

            System.out.println(
                    "Exception Type: "
                            + throwable
                            .getClass()
                            .getSimpleName()
            );

            System.out.println(
                    "Error Message: "
                            + throwable.getMessage()
            );

            System.out.println(
                    "===================================="
            );
        }

        // -----------------------------------------------------
        // Extent failure log
        // -----------------------------------------------------

        test.get().log(
                Status.FAIL,
                MarkupHelper.createLabel(
                        "Test Failed: " + testName,
                        ExtentColor.RED
                )
        );

        // Browser information
        test.get().info(
                "<b>Browser:</b> "
                        + browser.toUpperCase()
        );

        // -----------------------------------------------------
        // Add failure reason
        // -----------------------------------------------------

        if (throwable != null) {

            test.get().fail(
                    "<b>Failure Reason:</b> "
                            + throwable
                            .getClass()
                            .getSimpleName()
                            + "<br>"
                            + "<b>Message:</b> "
                            + throwable.getMessage()
            );

            // Complete stack trace
            test.get().fail(throwable);
        }

        // -----------------------------------------------------
        // Screenshot
        // -----------------------------------------------------

        if (driver != null) {

            try {

                String imagePath =
                        Screenshots.captureScreenshot(
                                driver,
                                testName
                        );

                if (imagePath != null) {

                    test.get()
                            .addScreenCaptureFromPath(
                                    imagePath
                            );

                    System.out.println(
                            "Screenshot attached for: "
                                    + testName
                    );
                }

            } catch (Exception e) {

                System.out.println(
                        "Unable to attach screenshot: "
                                + e.getMessage()
                );
            }

        } else {

            System.out.println(
                    "Driver is null. Screenshot skipped."
            );
        }
    }

    // =========================================================
    // TEST SKIPPED
    // =========================================================

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName =
                result.getName();

        System.out.println(
                "Name of the Skipped Test is "
                        + testName
        );

        String browser =
                DriverFactory.getBrowser();

        if (browser == null) {
            browser = "Unknown";
        }

        test.get().log(
                Status.SKIP,
                MarkupHelper.createLabel(
                        "Test Skipped: " + testName,
                        ExtentColor.GREY
                )
        );

        test.get().info(
                "<b>Browser:</b> "
                        + browser.toUpperCase()
        );

        // Add skip reason if available
        Throwable throwable =
                result.getThrowable();

        if (throwable != null) {

            test.get().skip(
                    "<b>Skip Reason:</b> "
                            + throwable.getMessage()
            );
        }
    }

    // =========================================================
    // SUITE FINISH
    // =========================================================

    @Override
    public void onFinish(ITestContext context) {

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Extent Report Finished"
        );

        System.out.println(
                "Report: " + repName
        );

        System.out.println(
                "=============================================="
        );

        if (extent != null) {
            extent.flush();
        }

        // Clean ThreadLocal
        test.remove();
    }

    // =========================================================
    // GET EXTENT TEST
    // =========================================================

    public static ExtentTest getTest() {
        return test.get();
    }


}
