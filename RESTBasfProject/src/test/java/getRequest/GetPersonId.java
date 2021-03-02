package getRequest;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPersonId extends BaseClass{
	
	@Test
	public void GetPersonIdOk() 
	{
		
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  JSONObject RequestParams=new JSONObject();
		  
		  RequestParams.put("firstName", "Pedro");
		  RequestParams.put("lastName", "Picapiedra");
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(RequestParams.toJSONString());
		 
		  Response response=httpRequest.request(Method.PUT,"/");
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
		  
		  String resp= response.getBody().asString();
		  
		  String[] a=resp.split(": \"");
		  String id=a[1].replace("\"}", "");
		  
		  String statusline=response.getStatusLine();
		  System.out.println("Statusline :"+ statusline);
		  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");	
		  
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  httpRequest= given();
		  
		  response=httpRequest.request(Method.GET,"/"+id);
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
		  
		  resp= response.getBody().asString();
		  System.out.println("Response: "+resp);
		  		   
	}
	
	@Test
	public void GetPersonIdKONumbers() 
	{
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  String id="456";
		  
		  Response response=httpRequest.request(Method.GET,"/"+id);
		  
		  String resp= response.getBody().asString();
		  
		  Assert.assertEquals(response.getStatusCode(), 404);
 		   
	}
	
	@Test
	public void GetPersonIdKOSpecialCharacters()
	{
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  String id="45%&45";
		  
		  Response response=httpRequest.request(Method.GET,"/"+id);
		  
		  String resp= response.getBody().asString();
		  
		  Assert.assertEquals(response.getStatusCode(), 401);
 		   
	}
	
	@Test
	public void GetPersonIdKONumbers2() 
	{
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  String id="980708979870987980790878907098709";
		  
		  Response response=httpRequest.request(Method.GET,"/"+id);
		  
		  String resp= response.getBody().asString();
		  
		  Assert.assertEquals(response.getStatusCode(), 404);
 		   
	}
	
	@Test
	public void GetPersonIdKOLetters() 
	{
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  String id="fdere";
		  
		  Response response=httpRequest.request(Method.GET,"/"+id);
		  
		  String resp= response.getBody().asString();
		  
		  Assert.assertEquals(response.getStatusCode(), 404);
 		   
	}

}
