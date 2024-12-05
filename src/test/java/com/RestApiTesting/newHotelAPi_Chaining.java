package com.RestApiTesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.POJO_files.AuthenticationPOJO;
import com.POJO_files.CreateBooking;
import com.POJO_files.CreateBooking_bookingdates_nestedJson;
import com.POJO_files.PartialUpdateBooking;

import io.restassured.response.Response;

public class newHotelAPi_Chaining {
	 int id;
	 String token;
  @Test(priority=1)
  public void CBooking()
{
	  
	  CreateBooking_bookingdates_nestedJson dete= new CreateBooking_bookingdates_nestedJson ();  //nested json
	  dete.setCheckin("2024-01-6");
	  dete.setCheckout("2024-01=10");
	  
	  
	  //use the method same order that given in body like.....
	  
	  

		/*
		 * '{
	    "firstname" : "Jim",
	    "lastname" : "Brown",
	    "totalprice" : 111,
	    "depositpaid" : true,
	    "bookingdates" : {
	        "checkin" : "2018-01-01",
	        "checkout" : "2019-01-01"
	    },
	    "additionalneeds" : "Breakfast"
	}'
		
		*/

	  
	  
	 // com.POJO_files.CreateBooking  data= new com.POJO_files.CreateBooking ();  //maim json
	  
	  CreateBooking  data=new CreateBooking ();
	  data.setFirstname("vaishnavi");
	  data.setLastname("habare");
	  data.setTotalprice(999);
	  data.setDepositpaid(true);
	  data.setBookingdates(dete);
	  data.setAdditionalneeds("Dinner");
	  
	  
	Response res=given()
			.header("Content-Type", "application/json")
			.body(data)
			.when().post("https://restful-booker.herokuapp.com/booking");
	
	
	//all response on console
	
	res.then().log().all();
	
	System.out.println("***************************************");
	
	System.out.println("Status code is :"+res.statusCode());
	
	System.out.println("***************json format************************");
	
	id=res.jsonPath().getInt("bookingid");
	
	
	System.out.println("Id is :" +res.jsonPath().getInt("bookingid"));
	
	System.out.println("***************body format************************");
	
	  res.then().log().body();
  }
  
  @Test(priority=2)
  public void getBookingsameId()
  {
	  
	  System.out.println("**********************getbooking id ************"+id);
	Response res=  given()
	 . when().get("https://restful-booker.herokuapp.com/booking/"+id);
	  
	  res.then().log().body();
	  System.out.println("**********************************");
	  res.then().log().all();
	  
	  
  }
  
  
  @Test(priority=3)
  public void createtoken()
  {
	  
	  System.out.println("**********create token****************");
	  
	  AuthenticationPOJO data= new AuthenticationPOJO ();
	  data.setUsername("admin");
	  data.setPassword("password123");
	  
	  
Response res=	  given()
	  .header("Content-Type", "application/json")
	  .body(data)
	  
	  .when().post("https://restful-booker.herokuapp.com/auth");
	  
	 res.then().log().all();
	 
	 token= res.jsonPath().getString("token");
	 
	  System.out.println("token are :"+token);
  }
  
  @Test(priority=4)
  public void UpdateBooking()
  {  
  System.out.println("***********update booking****************");
  
	  
	  CreateBooking_bookingdates_nestedJson dete= new CreateBooking_bookingdates_nestedJson ();  //nested json
	  dete.setCheckin("2024-01-6");
	  dete.setCheckout("2024-01=10");
	  
	  
	  CreateBooking  data=new CreateBooking ();
	  data.setFirstname("vaishnavi");
	  data.setLastname("habare");
	  data.setTotalprice(999);
	  data.setDepositpaid(true);
	  data.setBookingdates(dete);
	  data.setAdditionalneeds("Dinner");
	  
	  
	  
	  
	Response res=  given()
	  .header("Content-Type", "application/json")
	  .header("Accept", "application/json" )
	  .header("Cookie" ,"token="+token)
	  .body(data)
	  .when().put("https://restful-booker.herokuapp.com/booking/"+id);
	  
	
	res.then().log().all();
	
	System.out.println("******************body***********");
	res.then().log().body();
	  
  }
	  
	  
  @Test(priority=5)
  public void pUpdateBooking()
  { 
  System.out.println("****************partial update booking**************");
  

  CreateBooking_bookingdates_nestedJson dete= new CreateBooking_bookingdates_nestedJson ();  //nested json
  dete.setCheckin("2024-01-6");
  dete.setCheckout("2024-01=10");
  
  
  
  
  CreateBooking  data=new CreateBooking ();
  data.setFirstname("sidddd");
  data.setLastname("Birajdar");
  data.setBookingdates(dete);
	  
Response res=given()
	   .header("Content-Type", "application/json")
		  .header("Accept", "application/json" )
		  .header("Cookie" ,"token="+token)
		  .body(data)
	   .when().patch("https://restful-booker.herokuapp.com/booking/"+id);

     res.then().log().all();
     
     
	  res.then().log().body();
	  
	  
  }
  
  
  @Test(priority=6)
  public void DeleteBooking()
  {
	  
	  System.out.println("*********Delete Booking******************");
	Response res=  given()
	  .header("Content-Type", "application/json")
	  .header("Cookie" ,"token="+token)
	  .when().delete("https://restful-booker.herokuapp.com/booking/"+id);
	
	
	res.then().log().all();
	
	
	System.out.println("msg is :"+res.getStatusLine());
	  
	  
	  
	  
	  
	  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
