package com.RestApiTesting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestWithNonBDD {
  @Test
  public void SingleObject() 
  {
	  
	 Response res= RestAssured.get("https://api.restful-api.dev/objects/7");
	 
	 System.out.println("status code :"+res.statusCode());
	 System.out.println("status massage :" +res.getStatusLine());
	 System.out.println("response time :" +res.getTimeIn(TimeUnit.SECONDS));
	 System.out.println("************string format******************");
	 
	 System.out.println(res.asString());
	 
	 System.out.println("************string format******************");
	 
	 System.out.println(res.asPrettyString());
	 
	 
	 //validation of id=7 
	 
	String id= res.jsonPath().getString("id");
	Assert.assertEquals(id, "7", "id are not matched");
	System.out.println("id are matched !!!!!!!!!!!!!");
	
	
	//"year": 2019
	
	int year=res.jsonPath().getInt(" data.year");
	Assert.assertEquals(year, 2019 ,"year are not matched");
	System.out.println("year are matched !!!!!!!");
	
	
	//  "Hard disk size": ""
	String harddisk=res.jsonPath().getString("data['Hard disk size']");
	Assert.assertEquals(harddisk, "1 TB" ,"harddisk are not matched");
	System.out.println("hardDisk are  matched");
	
	 
  }
  
  
  @Test()
  public void ListOfObject() 
  {
  
      Response res=RestAssured.get("https://api.restful-api.dev/objects");
      
      System.out.println("Status code :"+res.statusCode());
      System.out.println("status massage :"+res.statusLine());
      
      System.out.println("***************String format*********************");
      System.out.println(res.asString());
      
      System.out.println("***************Json format*********************");
      
      System.out.println(res.asPrettyString());
      
      
      //validate status code
      Assert.assertEquals(res.getStatusCode(), 200);
      System.out.println("Status code are matched");
      
      
      //validate  "color": "Cloudy White"
     String colour= res.jsonPath().getString("data[2].color");
      Assert.assertEquals(colour, "Cloudy White");
      System.out.println("colour are matched............."+colour);
      
      
      //validate id
   String id=  res.jsonPath().getString("id[2]");
   Assert.assertEquals(id ,"3");
   System.out.println("id are matched :"+id);
   
   
   //all id on List of object
 List<String> allid= res.jsonPath().getList("id");
 System.out.println("total is are :"+allid.size());
   
          
      for(String a:allid)
      {
    	  
    	  System.out.println(a);
      }
      
      
      //all name
      
      System.out.println("************************************************");
      
   List<String>  allname=res.jsonPath().getList("name");
   System.out.println("total name is :" +allname.size());
   
   for(String i:allname)
   {
	   System.out.println(i);
   }
   
   System.out.println("************************************************");
   //specific colour
  List<String>allcolour=res.jsonPath().getList("data.color");
  
  System.out.println("all colour are :" +allcolour.size());
   
  
  for(String v:allcolour)
  {
	  System.out.println(v);
  }
  
  //color=red
 String color= res.jsonPath().getString("data[8].Color");
 Assert.assertEquals(color, "Red");
 System.out.println("colur are matchd......."+color);
  
}
}
