package com.testRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.testRestAssured.Printing_Demo.baseurl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicResponse {
    @Test
    public void convenienceMethod(){
        Response response = RestAssured.get(baseurl);
       assertEquals( response.getStatusCode(),200);
       assertEquals(response.getContentType(),"application/json; charset=utf-8");
        String x = response.getHeader("X-Frame-Options");
        System.out.println(x);
    }
    @Test
    public void genericHeader() {
        Response response = RestAssured.get(baseurl);
assertEquals(response.getHeader("Server"),"GitHub.com");
assertEquals(response.getHeader("X-RateLimit-Limit"),"60");
assertEquals(Integer.parseInt(response.getHeader("X-RateLimit-Limit")),60);
//or
        String h = response.getHeader("X-RateLimit-Limit");
        Integer i=Integer.parseInt(h);
        assertEquals(i,60);
         }

         @Test
  public void getheaders(){
             Response response = RestAssured.get(baseurl);
             Headers headers = response.getHeaders();//it gives all the header of the particcular response
             System.out.println(headers);
             String val = headers.getValue("X-Frame-Options");//it gives particular header value among all the headers
             System.out.println(val+"*********");
             int size = headers.size();
             System.out.println(size);//it give the how many number of headers topic are present in the response header
             boolean isthere = headers.hasHeaderWithName("Server");//it return true if the mentioned header is present in that header
             System.out.println(isthere+"***");
             assertTrue(isthere);
    }

    @Test
    public void timeexample(){
        Response response = RestAssured.get(baseurl);
        long time = response.time();//it gives the response time in the unit of millisecond
        System.out.println(time);
        long time2 = response.timeIn(TimeUnit.SECONDS);//it return th customize time unit
        System.out.println(time2);
        long time3 = response.getTime();//it is same as time() method
        System.out.println(time3);
    }

}
