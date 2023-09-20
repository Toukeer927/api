package assignmentRest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
	
	
	
	
public void onTestStart(ITestResult result) {
		
		
		
		
		
System.out.println("Before test start");
		
	}
	
public void onTestSSuccess(ITestResult result) {
		
		
		
		
		
System.out.println("on test success");
		
	}


public void onTestFailure(ITestResult result) {
	
	
	
	
	
System.out.println("Before test Failure");
	
   }

public void onSkipped(ITestResult result) {
	
	
	
	
	
System.out.println("on test skip");
	
  }


public void onStart(ITestResult result) {
	
	
	
	
	
System.out.println("Before  start");
	
 }


public void onFinish(ITestContext context) {
	
	
	
	
	
System.out.println("on Finish");
	
  }
public void onTestFailedButWithSuccessPercentage(ITestResult result) {
	
	//TODO Auto generated method stub
	
  }

	
		
		
	
	}
