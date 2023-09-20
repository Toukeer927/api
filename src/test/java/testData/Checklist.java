package testData;


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

public class Checklist extends BaseClass  {

    private static RestClientWrapper restClient;

	private static String baseUrl;

	
	private static String Resource = ""; 
		
	
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
	@Test(priority=0)
	public void Create_a_checklist() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/CreateCheckList.json"); 

		 Response ServerResponse =restClient.post(Resources.CheckListEndPoint + id , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);    
        String name= Js.get("name");   
        Assert.assertEquals(name,"this Is Create Check List");
     System.out.println("token:" + name);
        
		 
		
	}
	
	
	@Test(priority=2)
	public void Get_a_Checklist() throws Exception 
	{
		
	//	 String bodyString = Utils.generateStringFromResource("/Body/body.json"); 

		 Response ServerResponse =restClient.Get(Resources.CheckListEndPoint + id );
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);    
		 
		 
		
	}
	
	
	@Test(priority=1
			)
	public void Update_a_Checklist() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/UpdateCheckList.json"); 

		 Response ServerResponse =restClient.put(Resources.CheckListEndPoint + id , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);
        id = Js.get("id");
        System.out.println("ID -->" + id);    
        String name= Js.get("name");   
        Assert.assertEquals(name,"This Is Upadeted Check List");
     System.out.println("token:" + name);
        
		 
	}
	
	
	
	@Test(priority=3)
	public void delete_a_Checklist() throws Exception 
	{
		
		 Response ServerResponse =restClient.delete(Resources.CheckListEndPoint + id );
			
		 ServerResponse.prettyPeek();	
		 
	}
	
}