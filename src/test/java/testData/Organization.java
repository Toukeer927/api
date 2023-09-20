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

public class Organization extends BaseClass  {

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
	public void Create_an_Organization() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/CreateOrganization.json"); 

		 Response ServerResponse =restClient.post(Resources.OrganizationEndPoint + id , bodyString);
	
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);  
        
        String desc = Js.get("desc");
        Assert.assertEquals(desc,"New Organization");
	}
	@Test(priority=2)
	public void Get_an_Organization() throws Exception 
	{
		


		 Response ServerResponse =restClient.Get(Resources.OrganizationEndPoint + id );
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);    
      
		 
		
	}
	
	
	@Test(priority=1)
	public void Update_an_Organization() throws Exception 
	{
		
		 String bodyString = Utils.generateStringFromResource("body/UpdateOrganization.json"); 

		 Response ServerResponse =restClient.put(Resources.OrganizationEndPoint + id , bodyString);
			
		 ServerResponse.prettyPeek();
		 
		 String strResponse  = ServerResponse.asString();
	    	
	    JsonPath Js = new JsonPath(strResponse);
	    
	 	 id = Js.get("id");
        System.out.println("ID -->" + id);    
        
        String desc = Js.get("desc");
        Assert.assertEquals(desc,"This organization is upadated");
	}
	
	
	@Test(priority=3)
	public void delete_an_Organization() throws Exception 
	{
		
		 Response ServerResponse =restClient.delete(Resources.OrganizationEndPoint + id );
			
		 ServerResponse.prettyPeek();		 
	
	}
	
}
