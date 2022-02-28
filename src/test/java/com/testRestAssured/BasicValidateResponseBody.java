package com.testRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testRestAssured.Printing_Demo.baseurl;

public class BasicValidateResponseBody {
    @Test
    public void jsonpathMap(){
        Response response = RestAssured.get(baseurl + "rate_limit");
        //System.out.println(response);
        ResponseBody body = response.body();
        //System.out.println(body);

        JsonPath jpath = body.jsonPath();
       // System.out.println(jpath);

        JsonPath jpath2 = response.body().jsonPath();
        //System.out.println(jpath2);

        Object fulljson = jpath2.get();//it give the body of the response
        System.out.println(fulljson);

        Object submap = jpath2.get("resources");//it give the resources
        System.out.println(submap);

        Object val = jpath.get("resources.core.limit");
        System.out.println(val);

        Object submap2 = jpath2.get("resources.core");
        System.out.println(submap2);
    }
    @Test
    public void matcherExample(){
        RestAssured.get(baseurl)
                .then()
                .body("current_user_url", Matchers.equalTo(baseurl+"user"))
                .body(Matchers.containsString("feeds_url"))
                .body(Matchers.containsString("followers_url"),Matchers.containsString("following_url"));

    }
    @Test
    public void complexBodyexample(){
        RestAssured.get(baseurl+"users/NaNi258")
                .then()
                .body("url",response -> Matchers.containsString(response.body().jsonPath().get("login")));//it validate the url contain the same string
    }

    //for the above same function we can do multiple url
    @DataProvider(name="data")
    public Object[][] endpoint(){
        return  new Object[][]{
                {"NaNi258"},
                {"rest-assured"}
        };
    }

    @Test(dataProvider = "data")
    public void complexBodyexample1() throws InterruptedException{
        RestAssured.get(baseurl+"users/"+user)
                .then()
                .body("url",response -> Matchers.containsString(response.body().jsonPath().get("login")));//it validate the url contain the same string
    }

}