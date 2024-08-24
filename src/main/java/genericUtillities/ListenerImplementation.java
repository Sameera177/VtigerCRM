package genericUtillities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener
{
	ExtentReports report;
	ExtentTest test;
	public static ExtentTest stest;
	
	
	@Override
	public void onStart(ITestContext context) 
	{
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report-"+BaseClass.sjutil.getCurrentTime()+".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Extent Reports");
		spark.config().setReportName("vTigerCRM");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("OS Version", System.getProperty("os.version"));
		report.setSystemInfo("JDK Version", System.getProperty("java.specification.version"));
		report.setSystemInfo("Author", System.getProperty("user.name"));	
	}
	
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		Capabilities cap = ((RemoteWebDriver)BaseClass.sdriver).getCapabilities();
		
		report.setSystemInfo("Browser: ", cap.getBrowserName());
		report.setSystemInfo("Browser Version", cap.getBrowserVersion());
		
		test = report.createTest(result.getMethod().getMethodName());
		stest = test;
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName() + " Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+ " Fail");
		test.fail(result.getThrowable());
		test.addScreenCaptureFromPath(new WebDriverUtility()
				.getScreenshot(BaseClass.sdriver, result.getMethod().getMethodName(), 
						BaseClass.sjutil));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+ " Skipped");
		test.skip(result.getThrowable());
	}

	
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
}
