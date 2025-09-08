package pinkcloud.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTest {
	
	ExtentReports report;

	@BeforeTest
	public void config() {

		// Create HTML file for reports and configurations
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\reports\\index.html");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Automation Framework Test Results");
		
		// Attach created report to ExtentReports class | Responsible for driving test executions to the report
		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Yrrej Villaflores");
	}

	@Test
	public void extentReport() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");
		
		// Create new test on the reporting file 
		// Monitor result status of test cases and send it to the report file 
		// ExtentReport will create Object from createTest and catch it using ExtentTest
		ExtentTest test = report.createTest("Demo");
		
		System.out.println(driver.getTitle());
		driver.quit();
		
		// Use test object to listen and report status of specific test back to ExtentReport
		test.fail("Fail");
		
		// All test case are done and and report will be generated
		report.flush();

	}

}
