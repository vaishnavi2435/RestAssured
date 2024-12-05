package com.RestApiTesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;


//import io.restassured.response.Response;

public class TestHotel_APIs {
  @Test
  public void GetBookingIds()
 {
	   
	  
	    Response res= given()
	    		  .when().get("https://restful-booker.herokuapp.com/booking");
	               
	     //to get Response in console
	    System.out.println("status code is :"+res.statusCode());
	    
	    System.out.println("***********************json format******************");
	    System.out.println(res.asPrettyString());
	    
	    System.out.println("***********************json format******************");
	    
	    //when you say all()- all in print in console
	  //  res.then().log().all();  //this is for all response
	    
	  //  res.then().log().headers();  //this is for all headers
	    
	   // res.then().log().cookies();  //this is for all cookies
	    
	    res.then().log().body();   //This is for all body (Response payload)
 }   
	    
	 @Test
	 public void CreatBooking()
	 {
		 
		
		 
		 
		 
		Response res= given()
		
		 .header("Content-Type", "application/json")
		 .body("\n"
		 		+ "{\n"
		 		+ "    \"firstname\": \"vaishnavi\",\n"
		 		+ "    \"lastname\": \"Birajdar\",\n"
		 		+ "    \"totalprice\": 890,\n"
		 		+ "    \"depositpaid\": true,\n"
		 		+ "    \"bookingdates\": {\n"
		 		+ "        \"checkin\": \"2024-01-6\",\n"
		 		+ "        \"checkout\": \"2024-1-10\"\n"
		 		+ "    },\n"
		 		+ "    \"additionalneeds\": \"Dinner\"\n"
		 		+ "}")
		 .when().post("https://restful-booker.herokuapp.com/booking");
		
		res.then().log().all();
		System.out.println("********************************");
		
		
		res.then().log().body();
		System.out.println("********************************");
		
		
		res.then().log().headers();
		System.out.println("********************************");
		
		int code=res.getStatusCode();
		System.out.println("Status code is :"+code);
		
		System.out.println("*********************json******************");
		System.out.println(res.asPrettyString());
		
		System.out.println("********************************");
		int id=res.jsonPath().getInt("bookingid");
		System.out.println("id is :"+id);
		
	 }
	    
	    
	   
	    
	    
  
}
