package getRequest;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutPerson extends BaseClass{

	@Test
	public void PutPersonOk() 
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
		  
		  System.out.println("Id :"+id);
		  
		  String statusline=response.getStatusLine();
		  System.out.println("Statusline :"+ statusline);
		  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");		   
	}
	
	@Test
	public void PutPersonKoSpecialCharacters() 
	{
		
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  JSONObject RequestParams=new JSONObject();
		  
		  RequestParams.put("firstName", "$%");
		  RequestParams.put("lastName", "Picapiedra");
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(RequestParams.toJSONString());
		 
		  Response response=httpRequest.request(Method.PUT,"/");
		  
		  Assert.assertEquals(response.getStatusCode(), 404);

	}
	
	@Test
	public void PutPersonKoBlank() 
	{
		
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  JSONObject RequestParams=new JSONObject();
		  
		  RequestParams.put("firstName", "");
		  RequestParams.put("lastName", "");
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(RequestParams.toJSONString());
		 
		  Response response=httpRequest.request(Method.PUT,"/");
		  
		  Assert.assertEquals(response.getStatusCode(), 404);

	}
	
	@Test
	public void PutPersonKoLong() 
	{
		
		  RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person";
		  
		  RequestSpecification httpRequest= given();
		  
		  JSONObject RequestParams=new JSONObject();
		  
		  RequestParams.put("firstName", "sdagasgasgasgasgasgasgasgasfgfagdfsgdsfgsdfgfdsgdsfgsdfgfdsgsdfgdfsgds");
		  RequestParams.put("lastName", "sdfgdfsgsdfgsdgfdsgsdfgsdfgsdfgsdfgsdfgsdfgsdgsdgsdfgsdfgsdgsdgsdfgsdgsd");
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(RequestParams.toJSONString());
		 
		  Response response=httpRequest.request(Method.PUT,"/");
		  
		  Assert.assertEquals(response.getStatusCode(), 404);

	}
}
