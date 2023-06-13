package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSetup {
	
	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentReports setupExtentReport() {
		//Getting Current Date
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date); 
		//Extent Objects
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Extent Report/ExecutionReport_"+actualDate+".html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		//Configuration Of Report
		spark.config().setDocumentTitle("API Automation Testing");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("User Module API Testing");

		return extent;
	}






}
