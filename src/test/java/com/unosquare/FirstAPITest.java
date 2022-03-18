package com.unosquare;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;


public class FirstAPITest {
  @Test
  public void validateJSONBodyHTTPObjectUser() {
	    RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);	
		Reporter.log("Sucess 200 validation");
		
		response.then().body("data.email", startsWith("janet.weaver"));
		response.then().body("data.email", endsWith("reqres.in"));
		response.then().body("data.first_name", equalTo("Janet"));
		response.then().body("data.last_name", equalTo("Weaver"));
		response.then().body("data.avatar", containsString("https://reqres.in/img/faces"));
		
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		
		Reporter.log(response.body().asString());
  }
  
  
  @Test
  public void validateJSONBodyHTTPObjectColor() {
	    RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/unknown/2");
		
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);	
		Reporter.log("Sucess 200 validation");
		
		response.then().body("data.name", equalTo("fuchsia rose"));
		response.then().body("data.year", equalTo(2001));
		response.then().body("data.color", equalTo("#C74375"));
		response.then().body("data.pantone_value", containsString("17-2031"));
		
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		
		Reporter.log(response.body().asString());
  }
 
  
  @BeforeMethod
  public void beforeMethod() {
  }

}
