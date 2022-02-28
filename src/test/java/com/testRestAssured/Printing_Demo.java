package com.testRestAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Printing_Demo {
    public static String baseurl="https://api.github.com/";
    @Test
    public void prints(){
        RestAssured.get(baseurl)
                .peek();//both header and body
    }
    @Test
    public void prints1(){
        RestAssured.get(baseurl)
                .prettyPeek();//preety
    }
    @Test
    public void prints2(){
        RestAssured.get(baseurl)
                .print();//only body
    }
    @Test
    public void print3(){
        RestAssured.get(baseurl+"users/NaNi258")
                .prettyPrint();
    }
}
