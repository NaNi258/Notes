package com.testRestAssured;

import com.unmarshalling.User;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testRestAssured.Printing_Demo.*;

public class ObjectmappingDemo {
    @Test
    public void objectmapping1(){
        User user = RestAssured.get(baseurl + "users/rest-assured")
                .as(User.class);//unmarshall the json response as user class
        Assert.assertEquals(user.getlogin(),"rest-assured");
        Assert.assertEquals(user.getpublicRepos(),2);

    }
    @Test
    public void objectmapping2(){
        User user = RestAssured.get(baseurl + "users/rest-assured")
                .as(User.class, ObjectMapperType.JACKSON_2);//unmarshall the json response as user class
        Assert.assertEquals(user.getlogin(),"rest-assured");
        Assert.assertEquals(user.getpublicRepos(),2);

    }
}
