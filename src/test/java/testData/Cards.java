package testData;

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

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Cards extends BaseClass  {

    private static RestClientWrapper restClient;

	private static String baseUrl;

	
	private static String Resource = ""; 
	Logger log = Logger.getLogger(Cards.class);
	
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
	public void Create_a_card() throws Exception 
	{
		 
		 String bodyString = Utils.generateStringFromResource("body/CreateCards.json"); 

		 Response ServerResponse =restClient.postForCards(Resources.CardEndPoint , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 String idList = Js.get("idList");
        System.out.println("ID LIST -->" + idList);
        log.info("Card is Created and ID Print Successfuly");
         id = Js.get("id");
        System.out.println("ID -->" + id);
        String name= Js.get("name");   
        Assert.assertEquals(name,"My New Card");
     System.out.println("token:" + name);
        
	}
	
	@Test (priority = 2)
	public void get_a_card() throws Exception 
	{
		
		// String bodyString = Utils.generateStringFromResource("/ApiAssignment/RestAssured/testData/CreateBoard.json"); 

		 Response ServerResponse =restClient.Get(Resources.CardEndPoint + id);
			
		 ServerResponse.prettyPeek();
		 
		String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	  id = Js.get("id");
        System.out.println("ID -->" + id);
         
	}

	@Test (priority = 1)
	public void update_a_card() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/UpdateCards.json"); 

		 Response ServerResponse =restClient.put(Resources.CardEndPoint + id , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	  id = Js.get("id");
        System.out.println("ID -->" + id);
        id = Js.get("id");
        System.out.println("ID -->" + id);
        String name= Js.get("name");   
        Assert.assertEquals(name,"This Is Updated Card");
     System.out.println("token:" + name);
        
         
	}
	
	@Test (priority = 3)
	public void delete_a_card() throws Exception 
	{
		
		// String bodyString = Utils.generateStringFromResource("/ApiAssignment/RestAssured/testData/CreateBoard.json"); 

		 Response ServerResponse =restClient.delete(Resources.CardEndPoint + id);
			
		 ServerResponse.prettyPeek();
		 
		String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	  id = Js.get("id");
        System.out.println("ID -->" + id);
        log.info("deleted sucessfully");
         
	}
	
	
}