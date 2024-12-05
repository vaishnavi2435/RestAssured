package com.RestApiTesting;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class RequestWithBDD {
  @Test
  public void singleOfObject() 
  {
	 Response res= given()
	  .when().get("https://api.restful-api.dev/objects/7");
	 
	 System.out.println("status code is :" +res.getStatusCode());
	 
	 
	 System.out.println("*************************string format*****************");
	 
	 System.out.println(res.asString());
	 
	 
	 System.out.println("*************************json format*****************");
	 System.out.println(res.asPrettyString());
	 
	String id= res.jsonPath().getString("id");
	Assert.assertEquals(id, "7");
	System.out.println("id are matched :"+id);
	 
	
	  
	  
	  /*.then()
	  .statusCode(200)
	  .log().all(); */
	  
	  
  }
  
  @Test
  public void ListOfObject()
  {
	  
	  //  validate the all id ............hastItems using
	  
	  
	  
	  given()
	  .when().get("https://api.restful-api.dev/objects")
	  .then()
	  .statusCode(200)
	  .body("id", hasItems("1","2","11","12"))
	  //.body("id", contains("1","2","11","12"))
	  .body("id", contains("1","2","3","4","5","6","7","8","9","10","11","12","13"))
	  .body("id", hasItems("1","2","3","4","5","6","7","8","9","10","11","12","13"))
	  .log().body();
	  
	  System.out.println("Using hasItems() all ids are matched");
  }
  
  
  
}
