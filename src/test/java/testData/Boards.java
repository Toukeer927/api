package testData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import assignmentRest.ReadingPropertiesFile;
import assignmentRest.Resources;
import assignmentRest.RestClientWrapper;
import assignmentRest.Utils;
import base.BaseClass;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Boards extends BaseClass  {

    private static RestClientWrapper restClient;
	private static String baseUrl;
	private static String Resource = "1/boards/"; 	
	Logger log = Logger.getLogger(Boards.class);
	
	static String id = "";
	
	@BeforeClass
	public void setupTest() throws Exception
	{
		
		baseUrl = ReadingPropertiesFile.getProperty("baseurl");
		
	}
	
	@BeforeMethod
	public void setUpRequest()
	{
		
		restClient = new RestClientWrapper(baseUrl,httpRequest);
	
	}
	@Test (priority = 0)
	public void Create_a_board() throws Exception 
	{
		 
		 String bodyString = Utils.generateStringFromResource("body/CreateBoard.json"); 

		 System.out.println(bodyString);  
		 Response ServerResponse =restClient.post(Resources.BoardEndPoint , bodyString);
		 
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	     JsonPath Js = new JsonPath(strResponse);
	    
	     id = Js.get("id");
	        System.out.println("ID -->" + id);   
	        log.info("ID Print Successfuly");
	        String name= Js.get("name");   
	        Assert.assertEquals(name,"mohd toukeer");
	     System.out.println("token:" + name);
	        String desc = Js.get("desc");
	        Assert.assertEquals(desc,"Every day new day");
	}
	@Test (priority = 1)
	public void Update_board() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/UpdateBoard.json"); 

		 Response ServerResponse =restClient.put(Resources.BoardEndPoint + id , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
		    JsonPath Js = new JsonPath(strResponse);
		    
		 	 id = Js.get("id");
	        System.out.println("ID -->" + id);  
	        
	        id = Js.get("id");
	        System.out.println("ID -->" + id);   
	       
	        String name= Js.get("name");   
	        Assert.assertEquals(name,"mohd aqib");
	     System.out.println("token:" + name);
	        String desc = Js.get("desc");
	        Assert.assertEquals(desc,"Upadte board");
	
}

	
	@Test (priority = 2)
	public void Get_a_board() throws Exception 
	{
		
		// String bodyString = Utils.generateStringFromResource("/ApiAssignment/RestAssured/testData/CreateBoard.json"); 

		 Response ServerResponse =restClient.Get(Resources.BoardEndPoint + id);
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
		    JsonPath Js = new JsonPath(strResponse);
		    
		 	 id = Js.get("id");
	        System.out.println("ID -->" + id);   
	     
	
	}
	
	
	@Test (priority = 3)
	public void Delete_board() throws Exception 
	{
		
		 Response ServerResponse =restClient.delete(Resources.BoardEndPoint + id);
			
		 ServerResponse.prettyPeek();
		 	
}
	@Test(priority = 4)
	public void Negativedata() throws Exception {
		String bodyString = Utils.generateStringFromResource("body/NegativeTest.json");
		Response ServerResponse = restClient.NegativeTest(Resources.BoardEndPoint, bodyString);

		ServerResponse.prettyPeek();
		String strResponse = ServerResponse.asString();
		

		Assert.assertEquals(strResponse, "invalid value for name");
		
		log.info("Failed test case checked Successfully");

	}

}