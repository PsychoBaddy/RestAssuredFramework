package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import api.utilities.ExtentSetup;

public class Listeners extends ExtentSetup implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Case started is "+result.getName());
		Reporter.log("Test Case started is "+result.getName());
		//Creating Node for the report
		test = extent.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {		
		System.out.println("Test case passed is "+result.getName());
		Reporter.log("Test case passed is "+result.getName());
		//Creating log
		test.log(Status.PASS, "Test case: "+result.getMethod().getMethodName()+" is passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {		
		System.out.println("Test case failed is "+result.getName());
		Reporter.log("Test case failed is "+result.getName());
		// Creating log
		test.log(Status.FAIL, "Test case: "+result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {		
		System.out.println("Test case skipped is "+result.getName());;
		Reporter.log("Test case skipped is "+result.getName());
		test.log(Status.SKIP, "Test case skipped is "+result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
		System.out.println("Test case Failed But Within Success Percentage is "+result.getName());
		Reporter.log("Test case Failed But Within Success Percentage is "+result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {		
		System.out.println("Test case Failed With Timeout is "+result.getName());
		Reporter.log("Test case Failed With Timeout is "+result.getName());
		test.log(Status.FAIL, "Test case Failed With Timeout is "+result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution is started");
		Reporter.log("Execution is started");

		//SetUp ExtentReport
		extent = ExtentSetup.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution is completed");
		Reporter.log("Execution is completed");

		//Close Extent
		extent.flush();
	}



}
