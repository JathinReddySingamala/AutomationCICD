package Practice.SeleniumFrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"\\report\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test results");
		
		ExtentReports extent = new ExtentReports(); 
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jathin");
		extent.createTest(path);
		return extent;
	}
}
