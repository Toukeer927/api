package base;



import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import assignmentRest.ExtentManager;
import assignmentRest.ReadingPropertiesFile;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	
	protected static ReadingPropertiesFile readingPropertiesFile = new ReadingPropertiesFile();
	
	public static  ExtentReports extent;
	
	public static ExtentTest test;
	
	protected static RequestSpecification httpRequest;
	//public static Utils Utility;
	
	
	@BeforeSuite
	
	public static void init()
	{
		
		
		
		extent = ExtentManager.getInstance("reports/ExtentReports.html");
		
		//Utility = new Utils();
		
		
		
		
		
	}
	
	
	@BeforeMethod
	
	public void startTest(Method method)
	{
		
		
		httpRequest = RestAssured.given();
		
		
		test = extent.startTest(method.getName());
	}
	
	
	@AfterMethod
	
		
		public void reportFlush(ITestResult result) {
			
			
		System.out.println(result.getMethod().getMethodName());	
		if (result.getStatus()==ITestResult.FAILURE)
			
			test.log(LogStatus.FAIL,result.getThrowable());
		else if (result.getStatus()==ITestResult.SKIP)
			test.log(LogStatus.SKIP,result.getThrowable());
		
		
		else
			
			test.log(LogStatus.PASS,"Test passes");
		
		
		extent.flush();
		}
	
}