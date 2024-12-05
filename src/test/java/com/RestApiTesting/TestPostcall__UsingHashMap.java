package com.RestApiTesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


import org.testng.annotations.Test;

public class TestPostcall__UsingHashMap {
  @Test
  public void HotelAPI() 
  {
	  
	  //Request payload
	  HashMap<String,Object> map=new HashMap<String ,Object>();
	  map.put("username", "admin");
	  map.put("password", "password123");
	  
	 Response res= given()
	  .header("Content-Type", "application/json")
	  .body(map)
	  
	  .when().post("https://restful-booker.herokuapp.com/auth");
	 
	 System.out.println(res.asPrettyString());
	 System.out.println("***************************");
	 System.out.println("Ststus code is :"+res.getStatusCode());
	 
	 System.out.println("***************************");
	 
	String token= res.jsonPath().getString("token");
	System.out.println("token is :"+token);
	 
	  
  }
  
}
