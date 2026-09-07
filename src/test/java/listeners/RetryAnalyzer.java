package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int counter = 0;
	private static final int retrycount = 2;

	public boolean retry(ITestResult reult) {

		if (counter < retrycount) {
			counter++;
			return true;
		}
		return false;

	}

}
