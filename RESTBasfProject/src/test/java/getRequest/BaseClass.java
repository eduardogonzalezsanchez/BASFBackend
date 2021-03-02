package getRequest;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseClass {
	
	@BeforeClass
	public void setup() {
		
		RestAssured.authentication=RestAssured.preemptive().basic("user", "pwd!");
		RestAssured.baseURI="http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config/";
	}

}
