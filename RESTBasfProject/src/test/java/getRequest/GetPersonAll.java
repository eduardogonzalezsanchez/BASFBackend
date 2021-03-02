package getRequest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.*;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetPersonAll extends BaseClass {
	
	
	@Test
	public void testResponseCode(){

		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  Response response=httpRequest.request(Method.GET,"/all");
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
		  
		  String resp= response.getBody().asString();
		  System.out.println("StatusCode: "+resp);
		  
		  String statusline=response.getStatusLine();
		  System.out.println("Statusline: "+ statusline);
		  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");
		  	 	
	}
	
	
	
}
	
	

