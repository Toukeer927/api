package assignmentRest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientWrapper {

	public String resource;
	public String baseUrl;
	private RequestSpecification request;
	private Response restResponse;
	
	
	public RestClientWrapper(String baseUrl,RequestSpecification request) {
		

		this.request= request;				
		this.request.baseUri(baseUrl);
		
	}
	
	
	public Response Get(String resource) throws Exception
	{
		
		 restResponse = request.header("Accept" , "application/json")
				               .queryParam("key",ReadingPropertiesFile.getProperty("key"))
				               .queryParam("token",ReadingPropertiesFile.getProperty("token"))
                               .when().get(resource);
			
		return restResponse;	
	}
	
	
	
	public Response post(String resource,String bodyString) throws Exception
	{
	
		restResponse = request.header("Accept" , "application/json")
				              .header("Content-Type" , "application/json")
				              .queryParam("key",ReadingPropertiesFile.getProperty("key"))
				              .queryParam("token",ReadingPropertiesFile.getProperty("token"))
				              .when().body(bodyString).post(resource).then().assertThat().statusCode(200).extract().response();
		
		return restResponse;
		
	}
	
	public Response postForCards(String resource , String bodyString) {
		
		restResponse = request.header("Accept", " application/json")
				              .header("Content-Type" , "application/json")
				              .queryParam("idList",ReadingPropertiesFile.getProperty("idList"))
				              .queryParam("key",ReadingPropertiesFile.getProperty("key"))
							  .queryParam("token",ReadingPropertiesFile.getProperty("token")).body(bodyString)
		                	  .when().post(resource);

		return restResponse;
	
	}
	
	public Response put(String resource , String bodyString) throws Exception
	{
		
		restResponse = request.header("Accept", " application/json")
	                          .header("Content-Type" , "application/json")
	                          .queryParam("key",ReadingPropertiesFile.getProperty("key"))
				              .queryParam("token",ReadingPropertiesFile.getProperty("token")).body(bodyString)
				              .when().put(resource).then().assertThat().statusCode(200).extract().response();

		return restResponse;
	
	}

	public Response delete(String resource ) throws Exception
	{
		
		restResponse = request.queryParam("key",ReadingPropertiesFile.getProperty("key"))
				              .queryParam("token",ReadingPropertiesFile.getProperty("token"))
				              .when().delete(resource);

		return restResponse;
	
	}
	public Response NegativeTest(String resource, String bodyString) throws Exception {

		restResponse = request.header("Accept", "application/json").header("Content-Type", "application/json")
				.queryParam("key", ReadingPropertiesFile.getProperty("key")).queryParam("token", ReadingPropertiesFile.getProperty("token"))
				.when().body(bodyString).post(resource).then().assertThat().statusCode(400).extract().response();

		return restResponse;


	}
}